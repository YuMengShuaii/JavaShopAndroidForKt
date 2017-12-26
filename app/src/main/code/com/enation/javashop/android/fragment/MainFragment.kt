package com.enation.javashop.android.fragment

import com.enation.javashop.android.R
import com.enation.javashop.android.activity.MainActivity
import com.enation.javashop.android.application.Application
import com.enation.javashop.android.contract.MainFragContract
import com.enation.javashop.android.databinding.FragmentMainLayBinding
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.lib.base.BaseFragment
import com.enation.javashop.android.lib.utils.debugLog
import com.enation.javashop.android.lib.vo.BlogTagData
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.android.presenter.MainFragPresenter
import com.enation.javashop.net.engine.model.NetState
import com.enation.javashop.photoutils.uitl.RxGetPhotoUtils
import kotlinx.android.synthetic.main.fragment_main_lay.*

/**
 * Created by LDD on 17/8/9.
 */
class MainFragment : BaseFragment<MainFragPresenter, FragmentMainLayBinding>(),MainFragContract.View {

    override fun getLayId(): Int {
        return R.layout.fragment_main_lay
    }

    override fun bindDagger() {
        Application.component.inject(this)
    }

    override fun init() {
        mViewDataBinding.blogData = BlogTagData("sadad","as123123","https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/472309f790529822f4f4ba96d5ca7bcb0b46d4a4.jpg")
        presenter.loadImage()
        imageview.setOnClickListener {
            RxGetPhotoUtils.init(activity as MainActivity)
                           .configCompress(true,true,false,102400,800,800)
                           .getPhotoFromGallery(true)
            JRouter.prepare().create("/main/image").seek()
        }
        test.setOnClickListener {
            mViewDataBinding.blogData = BlogTagData("sadad","as123123","https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/8694a4c27d1ed21bc8fe1c2aab6eddc450da3fcd.jpg")
        }
    }

    override fun bindEvent() {

    }

    override fun destory() {

    }

    override fun showImage(url: String) {
        mViewDataBinding.blogData.permalink = url
    }

    override fun showError(messgae: String) {

    }

    override fun complete(message: String) {

    }

    override fun start() {

    }
    override fun networkMonitor(state: NetState) {
        state.filter({
            debugLog(this.javaClass.name,"mobile")
        },{
            debugLog(this.javaClass.name,"wifi")
        },{
            debugLog(this.javaClass.name,"none")
        })
    }
}