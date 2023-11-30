package com.rentalhive.stockManagement.generatePDF;

import com.rentalhive.stockManagement.entities.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class GenerateContract {
    public void GenerateContractPDF(String existingPDFPath,String newPDFPath ) {
        try (PDDocument document = PDDocument.load(new File(existingPDFPath))) {
            PDPage lastPage = document.getPage(document.getNumberOfPages() - 1);
            PDPageContentStream contentStream = new PDPageContentStream(document, lastPage, PDPageContentStream.AppendMode.APPEND, true);

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            float startX = 50;
            float startY = 100;
            float maxWidth = lastPage.getMediaBox().getWidth() - 100;
            String additionalTerms = """
                    1. Delivery and Return:

                    The Owner shall deliver the equipment to the specified location [Delivery Address]. The Renter shall return the equipment to the same location on or before the termination date in the same condition as received, subject to reasonable wear and tear.

                    2. Use of Equipment:

                    The Renter shall use the equipment in a careful and proper manner and comply with all applicable laws and regulations. Any unauthorized use or modification of the equipment is prohibited.

                    3. Maintenance and Repairs:

                    The Owner shall provide properly maintained equipment in good working condition. The Renter shall not undertake any repairs or alterations without prior written consent from the Owner. The Renter shall bear the cost of repairs due to negligence or misuse.

                    4. Liability and Insurance:

                    The Renter shall be liable for any loss, damage, or theft of the equipment during the rental period. The Renter must maintain adequate insurance coverage for the rented equipment and name the Owner as an additional insured party.\s
                    
                    5. Indemnification:

                    The Renter agrees to indemnify and hold harmless the Owner from any claims, damages, liabilities, and expenses arising out of the Renter's use or possession of the rented equipment.
                    
                    6. Signatures:

                    Both parties acknowledge that they have read, understood, and agreed to the terms and conditions outlined in this Agreement""";
            // Split the terms into multiple lines if needed
            String[] lines = additionalTerms.split("\\r?\\n");
            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText(line);
                contentStream.endText();
                startY -= 15;
            }

            contentStream.close();

            document.save(newPDFPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}