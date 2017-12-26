package com.enation.javashop.android.contract

import com.enation.javashop.android.lib.base.BaseContract

/**
 * Created by LDD on 2017/8/22.
 */
interface FragmentLIstContract {
    interface View :BaseContract.BaseView{
        fun showList(data :ArrayList<String>)
    }
    interface Presenter :BaseContract.BasePresenter{
        fun loadlist()
    }
}