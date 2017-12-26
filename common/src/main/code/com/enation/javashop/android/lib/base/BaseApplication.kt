package com.enation.javashop.android.lib.base

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.multidex.MultiDexApplication
import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.android.lib.core.framework.Framework
import com.enation.javashop.android.lib.core.hack.AndroidHack
import com.enation.javashop.android.lib.core.runtime.ClassNotFoundInterceptor
import com.enation.javashop.android.lib.di.ApplicationComponent
import com.enation.javashop.android.lib.di.DaggerApplicationComponent
import com.enation.javashop.android.lib.di.MainModule

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午9:51
 * @From   com.enation.javashop.android.lib.base
 * @Note   Application基类
 */
open class BaseApplication : MultiDexApplication() {

    /**
     * 伴生对象
     */
    companion object {

        /**
         * @Name  appContext
         * @Type  android.support.multidex.MultiDexApplication()
         * @Note  应用Application对象（单例）
         */
        lateinit var appContext: Application

        /**
         * @Name  applicationComponent
         * @Type  com.enation.javashop.android.lib.di.ApplicationComponent
         * @Note  基础Application注入模块
         */
        lateinit var applicationComponent: ApplicationComponent
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseApplication
     * @Data   2017/12/26 上午10:06
     * @Note   Application创建时调用
     * @param  ...
     */
    override fun onCreate() {
        super.onCreate()
        /**初始化Application对象*/
        appContext = this
        /**初始化 基础Application注入模块*/
        applicationComponent = DaggerApplicationComponent.builder().mainModule(MainModule(ApiService::class.java, "http://192.168.2.91:8080/")).build()
        /**开始Activity生命周期监听，并启动AutoClearHelper*/
        Framework.initActivityLifeController(this)
        /**注入ClassNotFound异常拦截处理器*/
        Framework.classNotFoundInterceptor = object : ClassNotFoundInterceptor {
            override fun call(context: Context, intent: Intent?): Intent? {

                return intent
            }
        }
        /**对Instrumentation进行Hook替换成自定义Instrumentation*/
        AndroidHack.hookInstrumentation()
    }

}