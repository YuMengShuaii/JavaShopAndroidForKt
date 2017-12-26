package com.enation.javashop.android.lib.base

import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.android.lib.utils.getEventCenter
import com.enation.javashop.android.lib.utils.to
import com.enation.javashop.android.lib.vo.NetStateEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @Coder  LDD
 * @Data   2017/12/26 下午12:22
 * @From   com.enation.javashop.android.lib.base
 * @Note   Presenter基类
 */
open class RxPresenter<T : BaseContract.BaseView> :BaseContract.BasePresenter {

     /**
      * @Name  mView
      * @Type  T : BaseContract.BaseView
      * @Note  View接口
      */
     protected var mView: T? = null

     /**
      * @Name  api
      * @Type  com.enation.javashop.android.lib.api.ApiService
      * @Note  API
      */
     protected lateinit var api: ApiService

     /**
      * @Name  compositeDisposable
      * @Type  io.reactivex.disposables.CompositeDisposable
      * @Note  Disposable管理器
      */
     protected var compositeDisposable: CompositeDisposable? = null


    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.RxPresenter
     * @Data   2017/12/26 下午12:28
     * @Note   移除监听
     */
     protected fun unDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
        }

     }

     /**
      * @Coder  LDD
      * @From   com.enation.javashop.android.lib.base.RxPresenter
      * @Data   2017/12/26 下午12:29
      * @Note   添加Disposable
      * @param  disposable 监听引用
      */
     fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(disposable)
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.RxPresenter
     * @Data   2017/12/26 下午12:31
     * @Note   注入View接口和API 注册网络状态Event
     * @param  view View接口
     * @param  api  API
     */
    override fun attachView(view: Any, api: ApiService) {
        /**注入View接口*/
        this.mView = view.to()
        /**注入API*/
        this.api = api
        /**注册网络状态事件*/
        var disposable = getEventCenter().register(NetStateEvent::class.java,{
            result ->
            this.mView!!.networkMonitor(result.state)
        })
        addDisposable(disposable)
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.RxPresenter
     * @Data   2017/12/26 下午12:35
     * @Note   销毁操作
     */
    override fun detachView() {
        this.mView = null
        unDisposable()
    }

}