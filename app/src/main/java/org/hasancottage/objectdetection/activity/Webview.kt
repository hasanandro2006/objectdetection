package org.hasancottage.objectdetection.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import org.hasancottage.objectdetection.R

class Webview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView: WebView =findViewById(R.id.webView)

        webView.loadUrl("https://sites.google.com/view/object-detection-ai/home")


    }
}