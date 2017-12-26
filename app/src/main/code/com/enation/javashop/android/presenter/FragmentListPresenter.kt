package com.enation.javashop.android.presenter

import com.enation.javashop.android.contract.FragmentLIstContract
import com.enation.javashop.android.lib.base.RxPresenter
import javax.inject.Inject

/**
 * Created by LDD on 2017/8/22.
 */
class FragmentListPresenter @Inject constructor() :RxPresenter<FragmentLIstContract.View>(),FragmentLIstContract.Presenter {
    override fun loadlist() {
        var data = ArrayList<String>()
        for (i in 0..19) {
            data.add("HAIADSADADASD")
        }
        mView!!.showList(data)
    }
}