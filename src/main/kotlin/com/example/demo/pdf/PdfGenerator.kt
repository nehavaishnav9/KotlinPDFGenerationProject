
package com.example.demo.pdf

import com.aspose.pdf.*
import com.example.demo.model.Member
import com.example.demo.model.Authorization
import org.springframework.stereotype.Component
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class PdfGenerator {

    fun generate(
        member: Member,
        authorization: Authorization
    ): String {

        val document = Document()

        try {
            val page = document.pages.add()

            // Document heading
            val heading = TextFragment(
                "MEMBER AUTHORIZATION RECORD"
            )

            heading.textState.fontSize = 18f
            heading.textState.fontStyle = FontStyles.Bold

            page.paragraphs.add(heading)

            // Member details
            page.paragraphs.add(
                TextFragment("\nMEMBER DETAILS")
            )

            val memberTable = Table()
            memberTable.columnWidths = "150 250"
            memberTable.defaultCellPadding =
                MarginInfo(5.0, 5.0, 5.0, 5.0)

            memberTable.rows.add().cells.apply {
                add("Member ID")
                add(member.memberId)
            }

            memberTable.rows.add().cells.apply {
                add("Member Name")
                add(member.memberName)
            }

            memberTable.rows.add().cells.apply {
                add("Date of Birth")
                add(member.dateOfBirth)
            }

            memberTable.rows.add().cells.apply {
                add("Email")
                add(member.email)
            }

            page.paragraphs.add(memberTable)

            // Authorization details
            page.paragraphs.add(
                TextFragment("\nAUTHORIZATION DETAILS")
            )

            val authorizationTable = Table()
            authorizationTable.columnWidths = "150 250"
            authorizationTable.defaultCellPadding =
                MarginInfo(5.0, 5.0, 5.0, 5.0)

            authorizationTable.rows.add().cells.apply {
                add("Authorization ID")
                add(authorization.authorizationId)
            }

            authorizationTable.rows.add().cells.apply {
                add("Provider")
                add(authorization.providerName)
            }

            authorizationTable.rows.add().cells.apply {
                add("Service")
                add(authorization.serviceName)
            }

            authorizationTable.rows.add().cells.apply {
                add("Status")
                add(authorization.status)
            }

            page.paragraphs.add(authorizationTable)

            // Save PDF
            val archiveDirectory = File("authorization-archive")

            if (!archiveDirectory.exists()) {
                archiveDirectory.mkdirs()
            }

            val timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))

            val fileName = "authorization_${authorization.authorizationId}_$timestamp.pdf"

            val filePath = File(archiveDirectory, fileName).absolutePath

            document.save(filePath)

            return filePath

        } finally {
            document.close()
        }
    }
}
