package com.bignerdranch.android.photogallery

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class PhotoPageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_page)

        val fm = supportFragmentManager
        val currentFragment = fm.findFragmentById(R.id.fragmentContainer)

        if(currentFragment == null){
            val fragment = intent.data?.let { PhotoPageFragment.newInstance(it) }
            if (fragment != null) {
                fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
            }
        }

    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.web_view)
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed()
        }
    }


    companion object{
        fun newIntent(context: Context, photoPageUri: Uri): Intent {
            return Intent(context, PhotoPageActivity::class.java).apply {
                data = photoPageUri
            }
        }
    }
}