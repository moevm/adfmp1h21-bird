package com.example.adfmp1h21_bird.util

import android.content.Context

class ImageManager private constructor(context: Context) {
    companion object : SingletonHolder<ImageManager, Context>(::ImageManager)
}