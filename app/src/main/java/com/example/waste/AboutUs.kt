package com.example.waste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.waste.databinding.ActivityAboutUsBinding
import com.google.android.material.tabs.TabLayout

class AboutUs : AppCompatActivity() {
    private lateinit var binder: ActivityAboutUsBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binder.root)

        binder.layoutTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                loadWebViewContent(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        webView = binder.webview

        // Enable JavaScript (if required)
        webView.settings.javaScriptEnabled = true

        // Load the initial WebView content for the selected tab
        loadWebViewContent(binder.layoutTab.selectedTabPosition)

        webView.webViewClient = object : WebViewClient() {

        }

        webView.webChromeClient = object : WebChromeClient() {
        }
    }

    private fun loadWebViewContent(tabPosition: Int) {
        val htmlContent: String = when (tabPosition) {
            0 -> {
                "<html><body><h1>Java Point Tutorial</h1><p>Here is the <a href=\"https://www.javatpoint.com/java-tutorial\">Java Point tutorial</a>.</p></body></html>"
            }
            1 -> {
                "<html><body><h1>Tutorial Point Tutorial</h1><p>Here is the <a href=\"https://www.tutorialspoint.com/index.htm\">Tutorial Point tutorial</a>.</p></body></html>"
            }
            else -> ""
        }

        if (htmlContent.isNotEmpty()) {
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        }
    }

}