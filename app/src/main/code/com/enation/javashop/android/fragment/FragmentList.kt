package com.enation.javashop.android.fragment

import com.enation.javashop.android.R
import com.enation.javashop.android.adapter.ListAdapter
import com.enation.javashop.android.application.Application
import com.enation.javashop.android.contract.FragmentLIstContract
import com.enation.javashop.android.databinding.FragmentListLayBinding
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.lib.base.BaseFragment
import com.enation.javashop.android.lib.utils.debugLog
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.android.presenter.FragmentListPresenter
import com.enation.javashop.net.engine.model.NetState

/**
 * Created by LDD on 2017/8/22.
 */
class FragmentList :BaseFragment<FragmentListPresenter,FragmentListLayBinding>(),FragmentLIstContract.View {

    override fun getLayId(): Int {
        return R.layout.fragment_list_lay
    }

    override fun bindDagger() {
        Application.component.inject(this)
    }

    override fun init() {
        presenter.loadlist()
    }

    override fun bindEvent() {

    }

    override fun destory() {

    }
    override fun showList(data: ArrayList<String>) {
        mViewDataBinding.listview.adapter = ListAdapter(data,context)
        mViewDataBinding.listview.setOnItemClickListener { adapterView, view, i, l ->
            JRouter.prepare()
                    .create("/main/image")
                    .seek()
        }
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