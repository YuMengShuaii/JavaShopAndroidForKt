package com.enation.javashop.android.lib.base

import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.net.engine.model.NetState

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午10:08
 * @From   com.enation.javashop.android.lib.base
 * @Note   基础Mvp控制接口
 */
interface BaseContract {

    /**
     * @Coder  LDD
     * @Data   2017/12/26 上午10:08
     * @From   com.enation.javashop.android.lib.base.BaseContract
     * @Note   基础Presenter接口
     */
    interface BasePresenter {

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BasePresenter
         * @Data   2017/12/26 上午10:23
         * @Note   绑定View
         * @param  view View
         * @param  api  Api
         */
        fun attachView(view: Any, api: ApiService)

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BasePresenter
         * @Data   2017/12/26 上午10:25
         * @Note   销毁操作
         */
        fun detachView()
    }

    /**
     * @Coder  LDD
     * @Data   2017/12/26 上午10:26
     * @From   com.enation.javashop.android.lib.base.BaseContract
     * @Note   View控制类基类
     */
    interface BaseView {

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BaseView
         * @Data   2017/12/26 上午10:27
         * @Note   展示错误信息
         * @param  message 错误信息
         */
        fun showError(messgae: String)

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BaseView
         * @Data   2017/12/26 上午10:28
         * @Note   网络请求完成
         * @param  message  提示信息
         */
        fun complete(message: String)

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BaseView
         * @Data   2017/12/26 上午11:00
         * @Note   网络请求开始
         */
        fun start()

        /**
         * @Coder  LDD
         * @From   com.enation.javashop.android.lib.base.BaseContract.BaseView
         * @Data   2017/12/26 上午11:01
         * @Note   网络监听
         * @param  state 网络状态
         */
        fun networkMonitor(state: NetState)

    }
}