package com.example.marvelapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.security.MessageDigest

fun generateHash(ts: String, privateKey: String, publicKey: String): String {
    val input = "$ts$privateKey$publicKey"
    return MessageDigest.getInstance("MD5").digest(input.toByteArray())
        .joinToString("") { "%02x".format(it) }
}


fun Context.showImgGlide(imageView: ImageView, progressBar: ProgressBar?,url:String,type:String){
    val  imgUrl = if(type==""){
        url.replace("http", "https")
    } else
        url.replace("http", "https") + "." +type

    progressBar?.visibility = View.VISIBLE

    Glide.with(this)
        .load(imgUrl)
        .listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }
        })
        .into(imageView)

}

fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = requireActivity().currentFocus ?: View(requireActivity())
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


