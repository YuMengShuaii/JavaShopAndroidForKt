package com.enation.javashop.android.lib.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.enation.javashop.imagepluin.R
import com.enation.javashop.imagepluin.utils.GlideUtils

/**
 * @author  LDD
 * @Data    2017/12/26 上午9:22
 * @From    com.enation.javashop.android.lib.binding
 * @Note    DataBinding辅助类
 */
object BindingHelper {

            /**
             * @Coder  LDD
             * @From   com.enation.javashop.android.lib.binding.BindingHelper
             * @Data   2017/12/26 上午9:23
             * @Note   加载图片
             * @param  imageView 需要加载图片的ImageView
             * @param  url       图片Url
             */
            @BindingAdapter(value = "imageurl",requireAll = false)
            @JvmStatic fun andImage(imageView: ImageView, url: String) {
                Glide.with(imageView.context).load(url).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).error(R.drawable.image_error).into(imageView)
            }

    }

