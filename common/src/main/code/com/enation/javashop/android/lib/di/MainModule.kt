package com.enation.javashop.android.lib.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.android.lib.core.runtime.JavaShopActivityTask
import com.enation.javashop.net.engine.core.NetEngineFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午7:30
 * @From   com.enation.javashop.android.lib.di
 * @Note   MainModlue模块提供者
 */
@Module
class MainModule(private val s: Class<ApiService>, private val BaseUrl: String) {

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.di MainModule
     * @Data   2017/12/26 上午7:31
     * @Note   当前界面上下文提供者
     * @return 上下文
     */
    @Named("BaseConext")
    @Provides
    fun provideContext(): Context {
        return JavaShopActivityTask.instance.peekTopActivity()!!.baseContext
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.di MainModule
     * @Data   2017/12/26 上午7:34
     * @Note   Application上下文提供者
     * @param  Application上下文
     */
    @Named("Application")
    @Provides
    fun provideApplication(): Context {
        return BaseApplication.appContext.applicationContext
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.di MainModule
     * @Data   2017/12/26 上午7:39
     * @Note   Activity 对象提供者
     * @return Activity
     */
    @Named("Activity")
    @Provides
    fun provideTopActivity():Activity{
        return JavaShopActivityTask.instance.peekTopActivity()!!
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.di ApplicationComponent
     * @Data   2017/12/26 上午7:30
     * @Note   API对象提供者
     * @return 全局API
     */
    @Provides
    fun provideApi(): ApiService {
        return NetEngineFactory.getInstance().createService(s, BaseUrl)
    }


}
