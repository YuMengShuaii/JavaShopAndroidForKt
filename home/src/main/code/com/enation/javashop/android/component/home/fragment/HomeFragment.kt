package com.enation.javashop.android.component.home.fragment

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.enation.javashop.android.component.home.R
import com.enation.javashop.android.component.home.contract.HomeContract
import com.enation.javashop.android.component.home.databinding.FragmentHomeLayBinding
import com.enation.javashop.android.component.home.launch.HomeLaunch
import com.enation.javashop.android.component.home.presenter.HomePresenter
import com.enation.javashop.android.component.home.widget.*
import com.enation.javashop.android.jrouter.external.annotation.Router
import com.enation.javashop.android.lib.base.BaseFragment
import com.enation.javashop.android.lib.utils.*
import com.enation.javashop.android.lib.vo.filter
import com.enation.javashop.net.engine.model.NetState
import com.enation.javashop.net.engine.utils.ThreadUtils
import com.tmall.wireless.tangram.TangramEngine
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator
import com.tmall.wireless.tangram.support.async.AsyncLoader
import com.tmall.wireless.tangram.support.async.AsyncPageLoader
import kotlinx.android.synthetic.main.fragment_home_lay.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by LDD on 2017/8/17.
 */
@Router(path = "/home/fragment")
class HomeFragment  :BaseFragment<HomePresenter,FragmentHomeLayBinding>(),HomeContract.View {

    private lateinit var plugin: TangramPlugin
    override fun getLayId(): Int {
        return R.layout.fragment_home_lay
    }

    override fun bindDagger() {
        HomeLaunch.component.inject(this)
    }

    override fun init() {
        initRefresh()
        presenter.loadHome()
    }

    override fun bindEvent() {

    }

    override fun destory() {
        plugin.destory()
    }

    override fun showHome(result:String) {
        initTangram(result)
    }

    override fun showError(messgae: String) {
        errorLog("Error",messgae)
        if (refresh.isRefreshing){
            refresh.finishRefresh()
        }
    }

    override fun complete(message: String) {

    }

    override fun start() {

    }

    private fun initTangram(result:String){
        if (refresh.isRefreshing){
            refresh.finishRefresh()
        }
        errorLog("NETJSON",result)
        TangramPlugin.initApplication()
        plugin = TangramPlugin.prepare(activity)
        plugin  .registerCell(1,TestView::class.java)
                .registerCell(10, SimpleImgView::class.java)
                .registerCell(2, SimpleImgView::class.java)
                .registerCell(4, RatioTextView::class.java)
                .registerCell(199, SingleImageView::class.java)
                //.registerCell(110,TestViewHolderCell::class.java, ViewHolderCreator(R.layout.item_holder, TestViewHolder::class.java, TextView::class.java))
                .setLoadListener(getAsyncLoader(),getAsyncPageLoader())
                .addClickSupport(SampleClickSupport())
                .enableAutoLoadMore()
                .bindRecyclerView(main_view)
                .onScrolled()
                .then { self -> configEngine(self.getEngine())}
                .addDataSource(JSONArray(result))
    }

    private fun initRefresh(){
        refresh.isEnableAutoLoadmore = false
        refresh.isEnableRefresh = true
        refresh.setHeaderHeight(60f)
        refresh.setOnRefreshListener {
            presenter.loadHome()
        }
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

    private fun configEngine(engine: TangramEngine){
        engine.layoutManager.setFixOffset(0, 0, 0, 0)
    }

    private fun getAsyncLoader():AsyncLoader{
        return AsyncLoader { card, callback ->
            ThreadUtils.MainThread().schedule {
                // do loading
                val cells = JSONArray()

                for (i in 0..9) {
                    try {
                        val obj = JSONObject()
                        obj.put("type", 1)
                        obj.put("msg", "async loaded")
                        val style = JSONObject()
                        style.put("bgColor", "#FF1111")
                        obj.put("style", style.toString())
                        cells.put(obj)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
                callback.finish(plugin.getEngine().parseComponent(cells))
            }
        }
    }
    private fun getAsyncPageLoader():AsyncPageLoader{
        return AsyncPageLoader { page, card, callback ->
            ThreadUtils.MainThread().schedule {
                Log.w("Load page", card.load + " page " + page)
                val cells = JSONArray()
                for (i in 0..8) {
                    try {
                        val obj = JSONObject()
                        obj.put("type", 1)
                        obj.put("msg", "async page loaded, params: " + card.params!!.toString())
                        cells.put(obj)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
                val cs = plugin.getEngine().parseComponent(cells)

                if (card.page == 1) {
                    val adapter = plugin.getEngine().groupBasicAdapter

                    card.setCells(cs)
                    adapter.refreshWithoutNotify()
                    val range  = adapter.getCardRange(card)

                    adapter.notifyItemRemoved(range.getLower())
                    adapter.notifyItemRangeInserted(range.getLower(), cs.size)

                } else
                    card.addCells(cs)

                //mock load 6 pages
                callback.finish(card.page != 6)
                card.notifyDataChange()
            }
        }
    }
}