package com.alvaro.civitatis.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443"
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "79c8ca8d55135c916f8dad8abeb71aa4"
        const val PRIVATE_KEY = "ad2424de62b865d07245704621ca0680bbd9dc32"
        const val limit = 20
        fun hash(): String {
            val input = "$timestamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
        const val NAME = "name"
        const val MODIFIED = "modified"
    }
}