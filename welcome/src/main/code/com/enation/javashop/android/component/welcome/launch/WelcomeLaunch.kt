package com.enation.javashop.android.component.welcome.launch

import android.content.Context
import com.enation.javashop.android.component.welcome.di.DaggerWelcomeComponent
import com.enation.javashop.android.component.welcome.di.WelcomeComponent
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.android.lib.base.BaseLaunch

/**
 * Created by LDD on 2017/9/4.
 */
@Router(path = "/welcome/launch")
class WelcomeLaunch :BaseLaunch() {
    companion object {
        lateinit var component:WelcomeComponent
    }

    override fun moduleInit() {
        component = DaggerWelcomeComponent
                .builder()
                .applicationComponent(BaseApplication.applicationComponent)
                .build()
    }
}