package com.example.webview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.webview2.databinding.ActivityMainBinding
import java.net.URL

// Modificar el gradle para que funcione el binding
// Modificar el el manifest para los permisos de internet

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var URL = "https://www.raserscp.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (binding.search.text.isEmpty()) {
                    binding.search.setText("")
                    binding.search.clearFocus()
                } else {
                    if (URLUtil.isValidUrl(binding.search.text.toString())) {
                        binding.webView.loadUrl(binding.search.text.toString())
                    } else {
                        binding.webView.loadUrl("https://duckduckgo.com/?q=${binding.search.text}")
                    }
                }
            }
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
        }

        var options = binding.webView.settings
        options.javaScriptEnabled = true

        binding.webView.loadUrl(URL)


    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
