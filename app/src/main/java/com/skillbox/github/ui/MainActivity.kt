package com.skillbox.github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.skillbox.github.BuildConfig
import com.skillbox.github.R
import com.skillbox.github.network.Networking

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SoLoader.init(applicationContext, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(applicationContext)) {
            val client = AndroidFlipperClient.getInstance(applicationContext)
            client.addPlugin(InspectorFlipperPlugin(applicationContext, DescriptorMapping.withDefaults()))
            client.addPlugin(Networking.networkFlipperPlugin)
            client.start()
        }
    }
}
