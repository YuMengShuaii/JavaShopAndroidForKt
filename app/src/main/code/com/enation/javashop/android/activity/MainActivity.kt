package com.enation.javashop.android.activity

import android.support.v4.app.Fragment
import com.enation.javashop.android.R
import com.enation.javashop.android.application.Application
import com.enation.javashop.android.contract.MainContract
import com.enation.javashop.android.databinding.ActivityMainBinding
import com.enation.javashop.android.fragment.MainFragment
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.jrouter.external.annotation.Autowired
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.base.GalleryActivity
import com.enation.javashop.android.lib.utils.*
import com.enation.javashop.android.lib.vo.BlogTagData
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.android.presenter.MainPresenter
import com.enation.javashop.android.widget.navigationview.NavigationModel
import com.enation.javashop.net.engine.model.NetState
import com.enation.javashop.photoutils.model.TResult
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Router(path = "/main/home")
class MainActivity : GalleryActivity<MainPresenter, ActivityMainBinding>(),MainContract.View {

    @Autowired(name = "test")
    @JvmField var test: String? = null

    private var fragments = ArrayList<Fragment>()

    override fun getLayId(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        debugLog("AutoMap-SIZE", message = "${AutoClearHelper.intance.valueCount()}")
    }

    override fun bindDagger() {
        Application.component.inject(this)
        JRouter.prepare().inject(this)
    }

    override fun init() {
        presenter.registerBusEvent()
        var data = ArrayList<NavigationModel>()
        data.add(NavigationModel("首页",R.mipmap.ic_launcher,R.drawable.image_error))
        data.add(NavigationModel("分类",R.mipmap.ic_launcher,R.drawable.image_error))
        data.add(NavigationModel("购物车",R.mipmap.ic_launcher,R.drawable.image_error))
        data.add(NavigationModel("我的",R.mipmap.ic_launcher,R.drawable.image_error))
        navitagion.setData(data,{
            i ->
            viewpager.setCurrentItem(i,true)
        })
        navitagion.withViewPager(viewpager)
        fragments.add(MainFragment())
        fragments.add(MainFragment())
        fragments.add(acquireInstance("/home/fragment"))
        fragments.add(acquireInstance("/home/fragment"))
        viewpager.setFragments(fragments,supportFragmentManager)
        debugLog("MainActivity", message = test)
        presenter.loadData("wwwwwwwwwwww")
    }

    override fun bindEvent() {
        kotlin.setOnClickListener {
            getEventCenter().post(BlogTagData("1231123","231","231"))
            presenter.loadData("你猜我你猜不猜！")
        }

    }

    override fun destory() {
        debugLog("MainActivity","销毁Activity")
    }

    override fun showData(url: String) {
        debugLog("MainActivity",url)
    }

    override fun showError(messgae: String) {
        dismissDialog()
        debugLog("MainActivity","加载数据错误")
    }

    override fun complete(message: String) {
        dismissDialog()
        debugLog("MainActivity","Main")

    }

    override fun takeSuccess(result: TResult?) {
        if (result != null) {
            showMessage(result.image.compressPath)
        }
    }

    override fun start() {
        showDialog()
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
