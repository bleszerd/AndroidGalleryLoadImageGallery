package com.github.bleszerd.loadfromgallery.main.view

import android.content.Intent
import android.net.Uri
import com.github.bleszerd.loadfromgallery.databinding.ActivityMainBinding
import com.github.bleszerd.loadfromgallery.main.common.view.BaseActivity
import com.github.bleszerd.loadfromgallery.main.contract.MainContract.MainView
import com.github.bleszerd.loadfromgallery.main.presenter.MainPresenter

class MainActivity : BaseActivity<ActivityMainBinding>(), MainView {
    private val presenter = MainPresenter(this)

    override fun onPostInflate() {
        binding.button.setOnClickListener {
            presenter.loadImageFromGallery()

            //Load image
            //binding.imageView.setImageURI(Uri.parse("/data/data/com.github.bleszerd.loadfromgallery/app_imageDir/image.jpg"))
        }
    }

    override fun onLayoutInflate() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun startGalleryForPickImage(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    override fun updateImage(imageUrl: Uri?) {
        binding.imageView.setImageURI(imageUrl)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        presenter.handleActivityResult(this, requestCode, resultCode, data)
    }
}