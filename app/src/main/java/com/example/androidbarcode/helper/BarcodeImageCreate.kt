package com.example.androidbarcode.helper

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import androidx.annotation.ColorInt
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.king.zxing.util.LogUtils
import java.lang.Exception

object BarcodeImageCreate {
    fun createBarCode(
        content: String?,
        format: BarcodeFormat?,
        desiredWidth: Int,
        desiredHeight: Int,
        hints: Map<EncodeHintType?, *>?,
        isShowText: Boolean,
        textSize: Int,
        @ColorInt codeColor: Int
    ): Bitmap? {
        if (TextUtils.isEmpty(content)) {
            return null
        }
        val writer = MultiFormatWriter()
        try {
            val result = writer.encode(
                content, format, desiredWidth,
                desiredHeight, hints
            )
            val width = result.width
            val height = result.height
            val pixels = IntArray(width * height)
            // All are 0, or black, by default
            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] = if (result[x, y]) codeColor else Color.TRANSPARENT
                }
            }
            val bitmap = Bitmap.createBitmap(
                width, height,
                Bitmap.Config.ARGB_8888
            )
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
            return if (isShowText) {
                addCode(bitmap, content, textSize, codeColor, textSize / 2)
            } else bitmap
        } catch (e: WriterException) {
            LogUtils.w(e.message)
        }
        return null
    }

    /**
     * TogiSoft
     * @param src
     * @param code
     * @param textSize
     * @param textColor
     * @return
     */
    private fun addCode(
        src: Bitmap?,
        code: String?,
        textSize: Int,
        @ColorInt textColor: Int,
        offset: Int
    ): Bitmap? {
        if (src == null) {
            return null
        }
        if (TextUtils.isEmpty(code)) {
            return src
        }

        //获取图片的宽高
        val srcWidth = src.width
        val srcHeight = src.height
        if (srcWidth <= 0 || srcHeight <= 0) {
            return null
        }
        var bitmap: Bitmap?
        try {
            bitmap = Bitmap.createBitmap(
                srcWidth,
                srcHeight + textSize + offset * 2,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            canvas.drawBitmap(src, 0f, 0f, null)
            val paint = TextPaint()
            paint.textSize = textSize.toFloat()
            paint.color = textColor
            paint.textAlign = Paint.Align.CENTER
            canvas.drawText(
                code!!,
                (srcWidth / 2).toFloat(),
                (srcHeight + textSize / 2 + offset).toFloat(),
                paint
            )
            canvas.save()
            canvas.restore()
        } catch (e: Exception) {
            bitmap = null
            LogUtils.w(e.message)
        }
        return bitmap
    }
}