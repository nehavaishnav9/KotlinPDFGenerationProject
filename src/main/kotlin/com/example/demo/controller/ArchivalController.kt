
package com.example.demo.controller

import com.example.demo.service.ArchivalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.demo.pdf.PdfGenerator
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@RestController
@RequestMapping("/api")
class ArchivalController(
    private val archivalService: ArchivalService,
    private val pdfGenerator: PdfGenerator
) {

    @GetMapping("/members/{memberId}")
    fun getMember(@PathVariable memberId: String): ResponseEntity<Any> {

        val member = archivalService.getMember(memberId)

        return if (member != null) {
            ResponseEntity.ok(member)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/authorizations/{authorizationId}")
    fun getAuthorization(
        @PathVariable authorizationId: String
    ): ResponseEntity<Any> {

        val authorization =
            archivalService.getAuthorization(authorizationId)

        return if (authorization != null) {
            ResponseEntity.ok(authorization)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping(
        "/members/{memberId}/authorizations/{authorizationId}/pdf"
    )
    fun downloadPdf(
        @PathVariable memberId: String,
        @PathVariable authorizationId: String
    ): ResponseEntity<Any> {

        val member = archivalService.getMember(memberId)
            ?: return ResponseEntity.notFound().build()

        val authorization =
            archivalService.getAuthorization(authorizationId)
                ?: return ResponseEntity.notFound().build()

        if (authorization.memberId != memberId) {
            return ResponseEntity.notFound().build()
        }

        val filePath = pdfGenerator.generate(member, authorization)

        val resource = FileSystemResource(filePath)

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=authorization.pdf"
            )
            .body(resource)
    }
}
