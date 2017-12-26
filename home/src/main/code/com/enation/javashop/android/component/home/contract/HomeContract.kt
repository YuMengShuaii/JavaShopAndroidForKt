package com.enation.javashop.android.component.home.contract

import com.enation.javashop.android.lib.base.BaseContract
import com.tmall.wireless.tangram.dataparser.concrete.Card

/**
 * Created by LDD on 2017/8/17.
 */
interface HomeContract {

    interface View :BaseContract.BaseView{
        fun showHome(value:String)
    }

    interface Presenter :BaseContract.BasePresenter{
        fun loadHome()
    }

}