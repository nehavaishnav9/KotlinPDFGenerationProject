
package com.example.demo.repository

import com.example.demo.model.Member
import com.example.demo.model.Authorization

interface ArchivalRepository {

    fun getMember(memberId: String): Member?

    fun getAuthorization(authorizationId: String): Authorization?
}
