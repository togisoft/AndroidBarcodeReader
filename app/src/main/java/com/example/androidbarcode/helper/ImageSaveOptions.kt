package com.example.androidbarcode.helper

import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import androidx.print.PrintHelper
import com.example.androidbarcode.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

object ImageSaveOptions {
    //Print Function
    fun printImage(activity: Activity, drawable: BitmapDrawable) {
        activity.also { context ->
            PrintHelper(context).apply {
                scaleMode = PrintHelper.SCALE_MODE_FIT
            }.also { printHelper ->
                val bitmap: Bitmap = drawable.bitmap
                printHelper.printBitmap("$drawable - print", bitmap)
            }
        }
    }

    //Cache Image Save
    fun saveImageCache(context: Context, bitmapDrawable: BitmapDrawable) {
        try {
            val cachePatch = File(context.cacheDir, "images")
            cachePatch.mkdirs()
            val stream = FileOutputStream(cachePatch.absolutePath + "/image.png")
            val bitmap: Bitmap = bitmapDrawable.bitmap
            val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
            val canvas = Canvas(newBitmap)
            canvas.drawColor(Color.WHITE)
            canvas.drawBitmap(bitmap, 0F, 0F, null)

            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun share(context: Context) {
        val imagePath = File(context.cacheDir, "images")
        val newFile = File(imagePath, "image.png")
        val contentUri =
            FileProvider.getUriForFile(context, "com.example.androidbarcode.fileprovider", newFile)

        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.setDataAndType(contentUri, context.contentResolver.getType(contentUri))
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            context.startActivity(Intent.createChooser(shareIntent, "Choose an App"))
        }
    }


    fun saveImagePngGallery(context: Context,view:View, bitmapDrawable: BitmapDrawable) {
        val bitmap: Bitmap = bitmapDrawable.bitmap
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0F, 0F, null)

        val fos: OutputStream
        val name = getRandomName(13)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver: ContentResolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/togisoft/images")
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = resolver.openOutputStream(imageUri!!)!!
        } else {
            @SuppressWarnings("deprecation")
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() +
                        File.separator + "/togisoft"

            val file = File(imagesDir)
            if (!file.exists()) {
                file.mkdir()
            }
            val image = File(imagesDir, "$name.png")
            fos = FileOutputStream(image)
        }
        newBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()
        Snackbar.make(view, R.string.saved_success, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(context.getColor(R.color.dark_green))
            .show()
    }

}