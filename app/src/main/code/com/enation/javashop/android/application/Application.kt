package com.enation.javashop.android.application

import com.enation.javashop.android.di.AppComponent
import com.enation.javashop.android.di.DaggerAppComponent
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.android.lib.jni.CommonJNI
import com.enation.javashop.net.engine.config.NetEngineConfig
import com.enation.javashop.net.engine.plugin.exception.RestfulExceptionInterceptor
import com.enation.javashop.utils.base.config.BaseConfig
import com.squareup.leakcanary.LeakCanary
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers

/**
 * Application
 */
  class Application : BaseApplication() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Observable.zip(initContext().subscribeOn(Schedulers.newThread()),
                       initLeaks().subscribeOn(Schedulers.newThread()),
                       initFrame().subscribeOn(Schedulers.newThread()),
                       initDagger().subscribeOn(Schedulers.newThread()),
                       Function4<String,String,String,String,String> { _, _, _, _ -> ""}).
                       observeOn(AndroidSchedulers.mainThread()).subscribe({})
    }
    fun initContext(): Observable<String>{
        return Observable.create {
            JRouter.init(this)
            JRouter.openDebug()
            JRouter.openLog()
            JRouter.prepare().create("/welcome/launch").seek()
            JRouter.prepare().create("/home/launch").seek()
        }
    }

    fun initLeaks(): Observable<String>{
        return Observable.create {
            LeakCanary.install(this)
        }
    }

    fun initDagger():Observable<String>{
        return Observable.create {
            component = DaggerAppComponent.builder()
                    .applicationComponent(applicationComponent)
                    .build()
        }
    }

    fun initFrame(): Observable<String>{
        return Observable.create {
            if (android.os.Build.VERSION.SDK_INT> android.os.Build.VERSION_CODES.LOLLIPOP){
                BaseConfig.openScrollBack()
                BaseConfig.HomeDisableScrollBack("MainActivity")
            }
            NetEngineConfig.init(baseContext)
                           .openLogger()
                           .addNetInterceptor(RestfulExceptionInterceptor())
        }
    }

}