package com.enation.javashop.android.lib.api

import com.enation.javashop.android.lib.vo.BlogData
import com.tmall.wireless.tangram.dataparser.concrete.Card
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Coder  LDD
 * @Data   2017/12/26 下午12:39
 * @From   com.enation.javashop.android.lib.api
 * @Note   全局API
 */
interface ApiService {

    @GET("static/file/daaaata.json")
    fun getBlogData() : Observable<ResponseBody>

    @GET("static/file/data.json")
    fun getData() : Observable<ResponseBody>

    @GET("asda")
    fun getEveryThing():Call<BlogData>

}