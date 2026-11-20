
package com.example.demo.pdf

import com.example.demo.model.Member
import com.example.demo.model.Authorization
import com.aspose.pdf.Document
import com.aspose.pdf.TextAbsorber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class PdfGeneratorTest {

    @Test
    fun `should generate pdf with correct member details`() {

        // Arrange
        val member = Member(
            memberId = "M101",
            memberName = "Rahul Sharma",
            dateOfBirth = "15-05-1990",
            email = "rahul@gmail.com"
        )

        val authorization = Authorization(
            authorizationId = "AUTH101",
            memberId = "M101",
            providerName = "City Hospital",
            serviceName = "MRI Scan",
            status = "APPROVED"
        )

        val pdfGenerator = PdfGenerator()

        // Act
        val filePath = pdfGenerator.generate(
            member,
            authorization
        )

        // Assert
        val file = File(filePath)

        try {
            assertTrue(file.exists())
            assertTrue(file.length() > 0)

            val document = Document(filePath)

            try {
                val absorber = TextAbsorber()

                document.pages.get_Item(1).accept(absorber)

                val pdfText = absorber.text

                assertTrue(pdfText.contains("Rahul Sharma"))
                assertTrue(pdfText.contains("M101"))
                assertTrue(pdfText.contains("AUTH101"))
                assertTrue(pdfText.contains("City Hospital"))
                assertTrue(pdfText.contains("APPROVED"))

            } finally {
                document.close()
            }

        } finally {
            file.delete()
        }
    }
}
