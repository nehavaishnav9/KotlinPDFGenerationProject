
package com.example.demo.model

data class Authorization(
    val authorizationId: String,
    val memberId: String,
    val providerName: String,
    val serviceName: String,
    val status: String
)
