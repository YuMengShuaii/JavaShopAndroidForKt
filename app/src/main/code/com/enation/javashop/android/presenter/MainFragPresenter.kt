package com.enation.javashop.android.presenter

import com.enation.javashop.android.contract.MainFragContract
import com.enation.javashop.android.lib.base.RxPresenter
import javax.inject.Inject

/**
 * Created by LDD on 17/8/9.
 */
class MainFragPresenter @Inject constructor() : RxPresenter<MainFragContract.View>(),MainFragContract.Presenter {
    override fun loadImage() {
        mView!!.start()
        mView!!.showImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502271239&di=6c9385e6f2d7f0e7313262fcd8ec2b7b&src=http://image.tianjimedia.com/uploadImages/2015/164/21/Z2NXM3PX9MS2.jpg")
        mView!!.complete("加载完成")
    }
}