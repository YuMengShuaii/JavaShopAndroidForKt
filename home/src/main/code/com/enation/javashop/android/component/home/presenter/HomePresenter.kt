package com.enation.javashop.android.component.home.presenter

import com.enation.javashop.android.component.home.contract.HomeContract
import com.enation.javashop.android.lib.base.RxPresenter
import com.enation.javashop.android.lib.utils.errorLog
import com.enation.javashop.net.engine.utils.ThreadFromUtils
import javax.inject.Inject

/**
 * Created by LDD on 2017/8/17.
 */
class HomePresenter @Inject constructor() :RxPresenter<HomeContract.View>(),HomeContract.Presenter {
    override fun loadHome() {
        api.getData()
                .compose(ThreadFromUtils.defaultSchedulers())
                .subscribe({result ->
                    mView?.showHome(result.string())
                },{error ->
                    mView?.showError(error.localizedMessage)
                })
    }
}