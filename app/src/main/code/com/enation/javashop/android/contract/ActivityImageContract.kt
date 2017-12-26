package com.enation.javashop.android.contract

import com.enation.javashop.android.lib.base.BaseContract

/**
 * Created by LDD on 2017/8/22.
 */
interface ActivityImageContract {
    interface View :BaseContract.BaseView{
      fun  showImage(url: String)
    }
    interface Presenter:BaseContract.BasePresenter{
      fun  loadimage()
    }
}