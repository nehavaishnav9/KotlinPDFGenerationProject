
package com.example.demo.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ArchivalServiceTest {

    private val archivalService = ArchivalService()

    @Test
    fun `should return member for valid member id`() {

        // Arrange
        val memberId = "M101"

        // Act
        val result = archivalService.getMember(memberId)

        // Assert
        assertNotNull(result)
        assertEquals("M101", result?.memberId)
        assertEquals("Rahul Sharma", result?.memberName)
    }

    @Test
    fun `should return null for invalid member id`() {

        // Arrange
        val memberId = "M999"

        // Act
        val result = archivalService.getMember(memberId)

        // Assert
        assertNull(result)
    }
}
