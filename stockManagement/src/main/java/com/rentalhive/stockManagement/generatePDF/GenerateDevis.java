package com.rentalhive.stockManagement.generatePDF;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateDevis{

    public  void fillDevisTemplate(String templatePath, String outputPath, List<TableRow> tableData , User renter , Demande demande) throws IOException {

        try (PDDocument document = PDDocument.load(new File(templatePath))) {

            PDDocumentCatalog catalog = document.getDocumentCatalog();
            PDAcroForm acroForm = catalog.getAcroForm();

            // Renter information
            setValue(acroForm, "firstName", renter.getFull_name().getFirstName());
            setValue(acroForm, "middleName", renter.getFull_name().getMiddleName());
            setValue(acroForm, "lastName", renter.getFull_name().getLastName());
            setValue(acroForm, "email", renter.getEmail().getAddressEmail());

            // Demand information
            setValue(acroForm, "dateDemand", demande.getDate_demande().toString());
            setValue(acroForm, "dateReservation", demande.getDate_reservation().toString());
            setValue(acroForm, "dateExpiration", demande.getDate_expiration().toString());


            // Add dynamic table
            addDynamicTable(document, tableData);

            // Flatten the form fields to make them non-editable
            acroForm.flatten();

            document.save(outputPath);
        }
    }

    private  void setValue(PDAcroForm acroForm, String fieldName, String value) throws IOException {
        PDField field = acroForm.getField(fieldName);

        if (field != null) {
            field.setValue(value);
        } else {
            System.out.println("Field not found: " + fieldName);
        }
    }

    // Inside the addDynamicTable method
    private void addDynamicTable(PDDocument document, List<TableRow> tableData) throws IOException {
        int rowHeight = 20; // Set the height of each row
        int tableWidth = 500; // Set the width of the table
        int yStart = 400; // Set the starting y-coordinate for the table
        float yPosition = yStart;
        int margin = 50; // Set left margin

        PDPageTree allPages = document.getPages();
        PDPage page = allPages.get(0);
        PDResources resources = page.getResources();

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            float tableWidthMargin = page.getMediaBox().getWidth() - 2 * margin;

            // Draw header
            drawTableHeader(contentStream, margin, yPosition, tableWidth);

            yPosition -= 20; // Move down for the next line

            // Draw rows
            for (TableRow row : tableData) {
                drawTableRow(contentStream, margin, yPosition, tableWidth, row);

                yPosition -= rowHeight; // Move down for the next line

                // Check if a new page is needed
                if (yPosition <= margin) {
                    contentStream.close();
                    page = new PDPage();
                    allPages.add(page);
                    contentStream.addRect(margin, 0, tableWidthMargin, page.getMediaBox().getHeight() - margin);
                    contentStream.clip();
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    yPosition = page.getMediaBox().getHeight() - margin - rowHeight;
                }
            }
        }
    }

    private void drawTableHeader(PDPageContentStream contentStream, float xStart, float yStart, float tableWidth) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setLineWidth(1f);
        contentStream.moveTo(xStart, yStart);
        contentStream.lineTo(xStart + tableWidth, yStart);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.newLineAtOffset(xStart, yStart - 15);
        contentStream.showText("Equipment");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("Quantity");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("Price/Day");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("Price Total");
        contentStream.endText();
    }

    private void drawTableRow(PDPageContentStream contentStream, float xStart, float yStart, float tableWidth, TableRow row) throws IOException {

        contentStream.setLineWidth(1f);
        contentStream.moveTo(xStart, yStart);
        contentStream.lineTo(xStart + tableWidth, yStart);
        contentStream.stroke();

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(xStart, yStart - 15);
        contentStream.showText(row.getEquipment());
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText(row.getQuantity());
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText(row.getPricePerDay());
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText(row.getPriceTotal());
        contentStream.endText();
    }

//    fillPdfTemplate("src/main/resources/DevisTemplateV1.pdf", "src/main/resources/devises/devis.pdf", tableData);
}


