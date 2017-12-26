package com.enation.javashop.android.component.welcome.contract

import com.enation.javashop.android.lib.base.BaseContract

/**
 * Created by LDD on 2017/8/15.
 */
interface WelcomeContract {
    interface View : BaseContract.BaseView{
        fun toHome()
    }
    interface Presenter :BaseContract.BasePresenter{
        fun LoadAnim()
    }
}