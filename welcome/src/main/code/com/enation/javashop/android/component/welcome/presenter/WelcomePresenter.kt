package com.enation.javashop.android.component.welcome.presenter

import com.enation.javashop.android.component.welcome.contract.WelcomeContract
import com.enation.javashop.android.lib.base.RxPresenter
import com.enation.javashop.android.lib.vo.BlogTagData
import com.enation.javashop.net.engine.plugin.rxbus.RxBindEvent
import com.enation.javashop.net.engine.plugin.rxbus.RxBus
import javax.inject.Inject

/**
 * Created by LDD on 2017/8/15.
 */
class WelcomePresenter @Inject constructor() :RxPresenter<WelcomeContract.View>(),WelcomeContract.Presenter, RxBindEvent {
    override fun LoadAnim() {
        mView!!.start()
        mView!!.toHome()
        mView!!.complete("加载成功")
    }

    override fun registerBusEvent() {
        RxBus.getDefault().register(BlogTagData::class.java,{
            mView!!.complete(it.name)
        })
    }
}