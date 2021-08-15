package com.github.bleszerd.loadfromgallery.main.contract

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
LoadFromGallery
15/08/2021 - 16:13
Created by bleszerd.
@author alive2k@programmer.net
 */
interface MainContract {
    interface MainView {
        fun startGalleryForPickImage(intent: Intent, requestCode: Int)
        fun updateImage(imageUrl: Uri?)
    }

    interface MainPresenter {
        fun loadImageFromGallery()
        fun handleActivityResult(context: Context, requestCode: Int, resultCode: Int, data: Intent?)
    }
}