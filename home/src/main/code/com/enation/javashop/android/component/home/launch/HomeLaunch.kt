package com.enation.javashop.android.component.home.launch

import android.content.Context
import com.enation.javashop.android.component.home.di.DaggerHomeComponent
import com.enation.javashop.android.component.home.di.HomeComponent
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.android.lib.base.BaseLaunch
import com.enation.javashop.android.lib.di.DaggerApplicationComponent
import com.enation.javashop.android.lib.di.MainModule

/**
 * Created by LDD on 2017/9/4.
 */
@Router(path = "/home/launch")
class HomeLaunch : BaseLaunch() {
    companion object {
        lateinit var component:HomeComponent
    }

    override fun moduleInit() {
        component = DaggerHomeComponent.builder()
                .applicationComponent(BaseApplication.applicationComponent)
                .build()
    }

}