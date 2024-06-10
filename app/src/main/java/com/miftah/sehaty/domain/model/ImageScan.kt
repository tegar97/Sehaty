package com.miftah.sehaty.domain.model

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

data class ImageScan(
    val photo : MultipartBody.Part
)

fun File.convertToImageScan() : ImageScan {
    val requestFile = this.asRequestBody("image/*".toMediaTypeOrNull())
    val photoPart = okhttp3.MultipartBody.Part.createFormData("photo", this.name, requestFile)
    return ImageScan(
        photo = photoPart
    )
}