package com.hsn.marvelheroes.utils

import java.security.MessageDigest

object Utils {
    fun calculateMd5Sum(ts : String,pubKey : String, privKey : String) : String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update((ts+privKey+pubKey).toByteArray())
        val digest = messageDigest.digest().toHexString()
        return digest
    }
}