package com.github.bleszerd.loadfromgallery.main.presenter

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.github.bleszerd.loadfromgallery.main.contract.MainContract.MainPresenter
import com.github.bleszerd.loadfromgallery.main.contract.MainContract.MainView
import java.io.File
import java.io.FileOutputStream

/**
LoadFromGallery
15/08/2021 - 16:13
Created by bleszerd.
@author alive2k@programmer.net
 */
class MainPresenter(private val view: MainView) : MainPresenter {
    companion object {
        const val REQUEST_IMAGE_GALLERY = 1
    }

    override fun loadImageFromGallery() {
        val i = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }

        view.startGalleryForPickImage(i, REQUEST_IMAGE_GALLERY)
    }

    override fun handleActivityResult(
        context: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        when (requestCode) {
            REQUEST_IMAGE_GALLERY -> {
                if (resultCode == Activity.RESULT_OK) {
                    handleSelectedGalleryImage(context, data)
                }
            }
        }
    }

    private fun handleSelectedGalleryImage(context: Context, data: Intent?) {
        val imageUrl = data?.data

        view.updateImage(imageUrl)
        saveImageOnDevice(context, imageUrl)
    }

    private fun saveImageOnDevice(context: Context, uri: Uri?): String? {
        if (uri != null) {
            val fileName = "${System.currentTimeMillis()}.jpg"
            var bitmapImage: Bitmap? = null

            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                bitmapImage = BitmapFactory.decodeStream(inputStream)
            } catch (e: Error) {
                println("ERROR: ${e.message}")
            }

            val contextWrapper = ContextWrapper(context)
            val directory = contextWrapper.getDir("selectedImages", Context.MODE_PRIVATE)
            val path = File(directory, fileName)
            val fileOutputStream = FileOutputStream(path)
            try {
                bitmapImage?.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            } catch (e: Error) {
                println(e.message)
                return null
            } finally {
                fileOutputStream.close()
            }

            println(directory.absolutePath)
            return directory.absolutePath
        }

        return null
    }
}