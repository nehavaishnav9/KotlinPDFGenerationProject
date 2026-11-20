
package com.example.demo.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArchivalControllerIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `should return member details`() {
        val response = restTemplate.getForEntity(
            "/api/members/M101",
            String::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertTrue(response.body!!.contains("Rahul Sharma"))
    }

    @Test
    fun `should return 404 for invalid member`() {
        val response = restTemplate.getForEntity(
            "/api/members/INVALID",
            String::class.java
        )

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `should generate authorization PDF`() {
        val response = restTemplate.getForEntity(
            "/api/members/M101/authorizations/AUTH101/pdf",
            ByteArray::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertTrue(response.body!!.isNotEmpty())
    }
}
