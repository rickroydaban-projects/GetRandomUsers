package com.rickaban.android.getrandomusers.app.ui.file

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class FileDownloader(
    private val context: Context
){
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var instance: FileDownloader

        fun init(context: Context){
            instance = FileDownloader(context)
        }
    }

    suspend fun downloadAndSave(
        source: String,
        destinationFileName: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = URL(source).openStream()
                val destination = File(context.filesDir, destinationFileName)

                if(!destination.exists()){
                    val outputStream = FileOutputStream(destination)
                    inputStream.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }
                }

                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}