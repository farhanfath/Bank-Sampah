package project.collab.banksampah.presentation.utils

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.toBitmap
import java.io.File

object ActionExtensions {

    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun downloadImageFromUrl(context: Context, imageUrl: String, fileName: String) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            try {
                val bitmap = result.image.toBitmap()

                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

                uri?.let {
                    resolver.openOutputStream(it).use { output ->
                        output?.let { stream -> bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream) }
                    }
                    Toast.makeText(context, "Gambar berhasil disimpan ke Downloads", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Toast.makeText(context, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Gagal mengunduh gambar dari URL", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun shareImage(context: Context, imageUrl: String) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            val bitmap = result.image.toBitmap()

            // simpan ke file cache
            val file = File(context.cacheDir, "shared_image.png")
            file.outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }

            // ambil URI dari FileProvider
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

            // share pakai Intent
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
        }
    }


//    suspend fun Context.downloadImageFromUrl(
//        imageUrl: String,
//        fileName: String = "downloaded_image_${System.currentTimeMillis()}.jpg"
//    ): Boolean = withContext(Dispatchers.IO) {
//        try {
//            val imageLoader = ImageLoader(this@downloadImageFromUrl)
//            val imageRequest = ImageRequest.Builder(this@downloadImageFromUrl)
//                .data(imageUrl)
//                .allowHardware(false)
//                .build()
//
//            val drawable = (imageLoader.execute(imageRequest) as SuccessResult).image
//            val bitmap = (drawable as? BitmapDrawable)?.bitmap
//                ?: return@withContext false
//
//            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            val request = DownloadManager.Request(Uri.parse(imageUrl))
//                .setTitle("Bank Sampah - Download Image")
//                .setDescription("Mengunduh gambar...")
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
//                .setAllowedOverMetered(true)
//                .setAllowedOverRoaming(true)
//
//            downloadManager.enqueue(request)
//
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@downloadImageFromUrl, "Download dimulai...", Toast.LENGTH_SHORT).show()
//            }
//
//            true
//        } catch (e: Exception) {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@downloadImageFromUrl, "Gagal mengunduh gambar: ${e.message}", Toast.LENGTH_SHORT).show()
//            }
//            false
//        }
//    }
//
//    suspend fun Context.shareImageFromUrl(
//        imageUrl: String,
//        shareText: String = "Lihat gambar ini dari Bank Sampah"
//    ): Boolean = withContext(Dispatchers.IO) {
//        try {
//            val imageLoader = ImageLoader(this@shareImageFromUrl)
//            val request = ImageRequest.Builder(this@shareImageFromUrl)
//                .data(imageUrl)
//                .allowHardware(false)
//                .build()
//
//            val drawable = (imageLoader.execute(request) as SuccessResult).image
//            val bitmap = (drawable as? BitmapDrawable)?.bitmap
//                ?: return@withContext false
//
//            val cachePath = File(cacheDir, "images")
//            cachePath.mkdirs()
//
//            val fileName = "shared_image_${System.currentTimeMillis()}.jpg"
//            val file = File(cachePath, fileName)
//
//            val fileOutputStream = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
//            fileOutputStream.close()
//
//            val contentUri = FileProvider.getUriForFile(
//                this@shareImageFromUrl,
//                "${packageName}.provider",
//                file
//            )
//
//            val shareIntent = Intent().apply {
//                action = Intent.ACTION_SEND
//                type = "image/jpeg"
//                putExtra(Intent.EXTRA_STREAM, contentUri)
//                putExtra(Intent.EXTRA_TEXT, shareText)
//                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
//
//            withContext(Dispatchers.Main) {
//                val chooserIntent = Intent.createChooser(shareIntent, "Bagikan gambar")
//                chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(chooserIntent)
//            }
//
//            true
//        } catch (e: Exception) {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@shareImageFromUrl, "Gagal membagikan gambar: ${e.message}", Toast.LENGTH_SHORT).show()
//            }
//            false
//        }
//    }
//
//    fun Context.shareUrlOnly(
//        url: String,
//        shareText: String = "Lihat gambar ini dari Bank Sampah"
//    ) {
//        try {
//            val shareIntent = Intent().apply {
//                action = Intent.ACTION_SEND
//                type = "text/plain"
//                putExtra(Intent.EXTRA_TEXT, "$shareText\n$url")
//            }
//
//            val chooserIntent = Intent.createChooser(shareIntent, "Bagikan link")
//            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(chooserIntent)
//        } catch (e: Exception) {
//            Toast.makeText(this, "Gagal membagikan link: ${e.message}", Toast.LENGTH_SHORT).show()
//        }
//    }

}