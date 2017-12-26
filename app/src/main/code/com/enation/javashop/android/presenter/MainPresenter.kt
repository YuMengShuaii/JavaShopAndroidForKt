package com.enation.javashop.android.presenter

import android.content.Context
import com.enation.javashop.android.contract.MainContract
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.android.lib.base.RxPresenter
import com.enation.javashop.android.lib.utils.ConnectionObserver
import com.enation.javashop.android.lib.utils.debugLog
import com.enation.javashop.android.lib.vo.BlogTagData
import com.enation.javashop.net.engine.plugin.connection.ConnectionQuality
import com.enation.javashop.net.engine.plugin.exception.ExceptionHandle
import com.enation.javashop.net.engine.plugin.rxbus.RxBindEvent
import com.enation.javashop.net.engine.plugin.rxbus.RxBus
import com.enation.javashop.net.engine.utils.ThreadFromUtils
import com.tmall.wireless.tangram.dataparser.concrete.Card
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by LDD on 17/8/9.
 */
 class MainPresenter @Inject constructor() : RxPresenter<MainContract.View>(),MainContract.Presenter, RxBindEvent {

    var context :Context = BaseApplication.appContext;

    override fun loadData(url: String) {
//                    Observable.merge(api.getData(),api.getBlogData())
//                            .compose(ThreadFromUtils.defaultSchedulers())
//                            .subscribe(object : ConnectionObserver<Any>() {
//                                override fun onStartWithConnection() {
//                                    debugLog("ConectionStart","Conection")
//                                }
//
//                                override fun attachSubscribe(p0: Disposable) {
//                                    debugLog("ConectionAttachSubscribe","AttachSubscribe ${p0.isDisposed}")
//                                    addDisposable(p0)
//                                }
//
//                                override fun onNextWithConnection(result: Any, connectionQuality: ConnectionQuality) {
//                                    debugLog("ConectionNext","$connectionQuality")
//                                }
//
//                                override fun onErrorWithConnection(error: ExceptionHandle.ResponeThrowable, connectionQuality: ConnectionQuality) {
//                                    debugLog("ConectionError",error.customMessage +"$error $connectionQuality")
//                                }
//
//                                override fun connectionBitsOfSecond(bits: Double) {
//                                    debugLog("ConectionBits","$bits kbps")
//                                }
//                            })
                    api.getData().compose(ThreadFromUtils.defaultSchedulers())
                            .flatMap({ result ->
                                debugLog("ConectionStar11111t","111111")
                                return@flatMap api.getBlogData().compose(ThreadFromUtils.defaultSchedulers())
                            })
                            .flatMap({ result ->
                                debugLog("ConectionStar11111t","222222")
                                return@flatMap api.getData().compose(ThreadFromUtils.defaultSchedulers())
                            })
                               .subscribe(object : ConnectionObserver<Any>() {
                                override fun onStartWithConnection() {
                                    debugLog("ConectionStart","Conection")
                                }

                                override fun attachSubscribe(p0: Disposable) {
                                    debugLog("ConectionAttachSubscribe","AttachSubscribe ${p0.isDisposed}")
                                    addDisposable(p0)
                                }

                                override fun onNextWithConnection(result: Any, connectionQuality: ConnectionQuality) {
                                    debugLog("ConectionNext","$connectionQuality")
                                }

                                override fun onErrorWithConnection(error: ExceptionHandle.ResponeThrowable, connectionQuality: ConnectionQuality) {
                                    debugLog("ConectionError",error.customMessage +"$error $connectionQuality")
                                }

                                override fun connectionBitsOfSecond(bits: Double) {
                                    debugLog("ConectionBits","$bits kbps")
                                }
                            })
    }

    override fun registerBusEvent() {
        RxBus.getDefault().register(BlogTagData::class.java, {
            mView!!.complete(it.name)
        })
    }
}