package org.hasancottage.objectdetection.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import org.hasancottage.objectdetection.R
import org.hasancottage.objectdetection.databinding.ActivityAboutThisAppBinding

class AboutThisApp : AppCompatActivity() {

    private lateinit var binding : ActivityAboutThisAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutThisAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.t.setOnClickListener {
            startActivity(Intent(Intent(this,Webview::class.java)))
        }
        binding.textView9.setOnClickListener {
            startActivity(Intent(Intent(this,Webview::class.java)))
        }


    }
}