package br.com.loadti.feedreader.downloads

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL
import java.util.logging.Level.parse

class DownloadTask {


    fun getBitmap(imageView: ImageView, url: String, context: Context) {

        val result: Deferred<Bitmap?> = GlobalScope.async {

            BitmapFactory.decodeStream(URL(url).openStream())
        }

        GlobalScope.launch(Dispatchers.Main) {

            val bitmap: Bitmap? = result.await()

            bitmap?.apply {


                imageView.setImageBitmap(bitmap)
            }
        }
    }


}