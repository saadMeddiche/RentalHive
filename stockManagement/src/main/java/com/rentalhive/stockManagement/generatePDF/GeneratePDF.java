package com.rentalhive.stockManagement.generatePDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class GeneratePDF {

    public static void fillPdfTemplate(String templatePath, String outputPath, String nom, String prenom) throws IOException {

        try (PDDocument document = PDDocument.load(new File(templatePath))) {

            /*
             PDDocumentCatalog help you to perform a lot of thing on
             the document like adding and removing pages, modifying metadata,
             extracting text or images, and working with interactive forms.
             */
            PDDocumentCatalog catalog = document.getDocumentCatalog();

            PDPage page = catalog.getPages().get(0);

            PDResources resources = page.getResources();

            PDAcroForm acroForm = catalog.getAcroForm();

            setValue(acroForm, "Nom", nom);
            setValue(acroForm, "Prenom", prenom);

            acroForm.flatten();

            document.save(outputPath);
        }
    }

    private static void setValue(PDAcroForm acroForm, String fieldName, String value) throws IOException {

        PDField field = acroForm.getField(fieldName);

        if (field != null) {
            field.setValue(value);
        } else {
            System.out.println("Field not found: " + fieldName);
        }
    }

    public static void main(String[] args) {
        try {
                fillPdfTemplate("src/main/resources/TemplateV2.pdf", "src/main/resources/devises/test.pdf", "Meddiche", "Saad");
            System.out.println("PDF filled successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
