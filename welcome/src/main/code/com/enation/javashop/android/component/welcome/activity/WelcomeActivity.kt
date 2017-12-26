package com.enation.javashop.android.component.welcome.activity

import com.enation.javashop.android.component.welcome.contract.WelcomeContract
import com.enation.javashop.android.component.welcome.launch.WelcomeLaunch
import com.enation.javashop.android.component.welcome.presenter.WelcomePresenter
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.base.BaseActivity
import com.enation.javashop.android.lib.utils.debugLog
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.android.welcome.R
import com.enation.javashop.android.welcome.databinding.ActivityWelcomeBinding
import com.enation.javashop.net.engine.model.NetState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.concurrent.TimeUnit

/**
 * 欢迎页面
 */
@Router(path = "/welcome/into")
class WelcomeActivity :BaseActivity<WelcomePresenter,ActivityWelcomeBinding>(), WelcomeContract.View {

    override fun getLayId(): Int {
        return R.layout.activity_welcome
    }

    override fun bindDagger() {
        WelcomeLaunch.component.inject(this)
    }

    override fun init() {
         presenter.registerBusEvent()
         haha.setOnClickListener {
             JRouter.prepare()
                     .create("/main/home")
                     .seek()
         }
        Observable.timer(2000,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    JRouter.prepare()
                            .create("/main/home")
                            .withString("test","JOLTER")
                            .seek()
                            finish()
                }
    }

    override fun bindEvent() {

    }

    override fun destory() {

    }

    override fun toHome() {

    }

    override fun showError(messgae: String) {

    }

    override fun complete(message: String) {

    }

    override fun start() {

    }

    override fun networkMonitor(state: NetState) {
        state.filter({
            debugLog("Welcome","mobile")
        },{
            debugLog("Welcome","wifi")
        },{
            debugLog("Welcome","none")
        })
    }
}