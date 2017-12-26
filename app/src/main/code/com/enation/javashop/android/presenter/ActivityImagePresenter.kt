package com.enation.javashop.android.presenter

import com.enation.javashop.android.contract.ActivityImageContract
import com.enation.javashop.android.lib.base.RxPresenter
import javax.inject.Inject

/**
 * Created by LDD on 2017/8/22.
 */
class ActivityImagePresenter @Inject constructor() :RxPresenter<ActivityImageContract.View>(),ActivityImageContract.Presenter {
    override fun loadimage() {

    }
}