package com.enation.javashop.android.contract

import com.enation.javashop.android.lib.base.BaseContract

/**
 * Created by LDD on 17/8/9.
 */
interface MainContract {
    interface View : BaseContract.BaseView {
        fun showData(url: String)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun loadData(url: String)
    }
}