package com.github.bleszerd.loadfromgallery.main.common.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.bleszerd.loadfromgallery.databinding.ActivityMainBinding

/**
LoadFromGallery
15/08/2021 - 16:14
Created by bleszerd.
@author alive2k@programmer.net
 */
abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onLayoutInflate()
        onPostInflate()

        setContentView(binding.root)
    }

    abstract fun onLayoutInflate()

    open fun onPostInflate(){

    }

}