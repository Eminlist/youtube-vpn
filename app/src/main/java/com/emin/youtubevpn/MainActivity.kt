package com.emin.youtubevpn

import android.app.Activity
import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var webView: WebView
    private lateinit var statusText: TextView

    companion object {
        private const val VPN_REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        webView = findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        startFlow()
    }

    private fun startFlow() {
        val prepareIntent = VpnService.prepare(this)

        if (prepareIntent != null) {
            statusText.text = "İlk dəfə VPN icazəsi tələb olunur..."
            startActivityForResult(prepareIntent, VPN_REQUEST)
        } else {
            openYouTube()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VPN_REQUEST && resultCode == RESULT_OK) {
            // NOTE:
            // This sample does not contain a real upstream tunnel.
            // A real implementation must embed a VPN engine such as libbox
            // and use a valid remote proxy/VPN server configuration.
            openYouTube()
        } else if (requestCode == VPN_REQUEST) {
            statusText.text = "VPN icazəsi verilmədi"
            openYouTube()
        }
    }

    private fun openYouTube() {
        statusText.text = "YouTube açılır..."
        webView.loadUrl("https://www.youtube.com/")
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}
