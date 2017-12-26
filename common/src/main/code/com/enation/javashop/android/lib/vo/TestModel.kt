package com.enation.javashop.android.lib.vo

/**
 * Created by LDD on 17/8/8.
 */

  data class BlogData(var title    : String,
                    var date     : String,
                    var path     : String,
                    var text     : String,
                    var content  : String,
                    var comments : Boolean,
                    var link     : String,
                    var tags     : List<BlogTagData>)

data class BlogTagData(var name      : String,
                         var slug      : String,
                         var permalink : String)

