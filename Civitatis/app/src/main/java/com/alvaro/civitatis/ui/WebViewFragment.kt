package com.alvaro.civitatis.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.alvaro.civitatis.databinding.FragmentWebviewBinding

class WebViewFragment: Fragment() {

    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = requireArguments().getString("url")
        val webviewSettings = binding.webview.settings
        webviewSettings.domStorageEnabled = true
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(url.toString())
    }

}