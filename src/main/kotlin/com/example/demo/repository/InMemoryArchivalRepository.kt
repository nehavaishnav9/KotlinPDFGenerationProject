
package com.example.demo.repository

import com.example.demo.model.Member
import com.example.demo.model.Authorization
import org.springframework.stereotype.Repository

@Repository
class InMemoryArchivalRepository : ArchivalRepository {

    override fun getMember(memberId: String): Member? {
        val member = Member(
            memberId = "M101",
            memberName = "Rahul Sharma",
            dateOfBirth = "15-05-1990",
            email = "rahul@gmail.com"
        )

        return if (member.memberId == memberId) member else null
    }

    override fun getAuthorization(authorizationId: String): Authorization? {
        val authorization = Authorization(
            authorizationId = "AUTH101",
            memberId = "M101",
            providerName = "City Hospital",
            serviceName = "MRI Scan",
            status = "APPROVED"
        )

        return if (authorization.authorizationId == authorizationId) {
            authorization
        } else {
            null
        }
    }
}
