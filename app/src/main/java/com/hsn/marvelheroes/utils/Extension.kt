/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.utils

fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }