
package com.example.demo.service

import com.example.demo.model.Member
import com.example.demo.model.Authorization
import com.example.demo.repository.ArchivalRepository
import org.springframework.stereotype.Service

@Service
class ArchivalService(
    private val archivalRepository: ArchivalRepository
) {

    fun getMember(memberId: String): Member? {
        return archivalRepository.getMember(memberId)
    }

    fun getAuthorization(authorizationId: String): Authorization? {
        return archivalRepository.getAuthorization(authorizationId)
    }
}
