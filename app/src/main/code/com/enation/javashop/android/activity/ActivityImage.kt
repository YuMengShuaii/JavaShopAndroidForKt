package com.enation.javashop.android.activity

import com.enation.javashop.android.R
import com.enation.javashop.android.application.Application
import com.enation.javashop.android.contract.ActivityImageContract
import com.enation.javashop.android.databinding.ActivityImageLayBinding
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.aspect.LoginHelper
import com.enation.javashop.android.lib.base.BaseActivity
import com.enation.javashop.android.lib.utils.*
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.android.presenter.ActivityImagePresenter
import com.enation.javashop.net.engine.model.NetState

/**
 * Created by LDD on 2017/8/22.
 */
@Router(path = "/main/image")
class ActivityImage :BaseActivity<ActivityImagePresenter,ActivityImageLayBinding>(),ActivityImageContract.View {

    lateinit var v1 :AutoClearValue<String>
    lateinit var v2 :AutoClearValue<String>
    lateinit var v3 :AutoClearValue<String>
    lateinit var v4 :AutoClearValue<String>
    override fun getLayId(): Int {
        return R.layout.activity_image_lay
    }

    override public open fun bindDagger() {
        Application.component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        debugLog("AutoMap-SIZE", message ="${ AutoClearHelper.intance.valueCount()}")
    }

    @LoginHelper
    override fun init() {
        debugLog(localClassName,"初始化！")
    }

    override fun bindEvent() {
        v1 = AutoClearValue(this,"11111")
        v2 = AutoClearValue(this,"22222")
        v3 = AutoClearValue(this,"33333")
        v4 = AutoClearValue(this,"44444")


        Do.prepare().doOnBack {
            errorLog("Do","DoNomal1")
            errorLog("Thread",Thread.currentThread().name)
            it.invoke()
        }.doOnUI {
            errorLog("Do","DoNomal2")
            errorLog("Thread",Thread.currentThread().name)
            it.invoke()
        }.onUISend {
            call ->
            errorLog("Do","DoSend3")
            errorLog("Thread",Thread.currentThread().name)
            call.invoke("asdad")
        }.onUIResult { result, function ->
            errorLog("Do","DoResult4")
            errorLog("Result",result.to())
            errorLog("Thread",Thread.currentThread().name)
            function.invoke()
        }.onUISend {
            call ->
            errorLog("Do","DoSend5")
            errorLog("Thread",Thread.currentThread().name)
            call.invoke(11111)
        }.onBackResult { result, function ->
            errorLog("Do","DoResult6")
            errorLog("Result","${result.to<Int>()}")
            errorLog("Thread",Thread.currentThread().name)
            function.invoke()
        }.execute()

        Do.prepare().onBackSend {


        }
    }

    override fun destory() {

    }

    override fun showImage(url: String) {

    }

    override fun showError(messgae: String) {

    }

    override fun complete(message: String) {

    }

    override fun start() {

    }

    override fun networkMonitor(state: NetState) {
        state.filter({
            debugLog(this.javaClass.name,"mobile")
        },{
            debugLog(this.javaClass.name,"wifi")
        },{
            debugLog(this.javaClass.name,"none")
        })
    }

}