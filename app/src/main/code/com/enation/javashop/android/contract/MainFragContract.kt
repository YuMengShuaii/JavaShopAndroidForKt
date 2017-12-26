package com.enation.javashop.android.contract

import com.enation.javashop.android.lib.base.BaseContract

/**
 * Created by LDD on 17/8/9.
 */
interface MainFragContract {
    interface View : BaseContract.BaseView{
        fun showImage(url:String)
    }

    interface Presenter :BaseContract.BasePresenter{
        fun loadImage();
    }
}