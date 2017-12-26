package com.enation.javashop.android.lib.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.enation.javashop.android.lib.api.ApiService
import javax.inject.Inject
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.enation.javashop.utils.base.tool.BaseToolActivity
import java.lang.reflect.Field

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午9:26
 * @From   com.enation.javashop.android.lib.base
 * @Note   Activity基类
 */
abstract class BaseActivity<T : BaseContract.BasePresenter, T2 : ViewDataBinding> : BaseToolActivity() {

    /**
     * @Name  presenter
     * @Type  T : BaseContract.BasePresenter
     * @Note  Activity中Presenter Dagger自动注入
     */
    @Inject
    protected lateinit var presenter: T

    /**
     * @Name  api
     * @Type  com.enation.javashop.android.lib.api.ApiService
     * @Note  Api对象 Dagger自动注入
     */
    @Inject
    protected lateinit var api: ApiService

    /**
     * @Name  presenter
     * @Type  T2 : ViewDataBinding
     * @Note  DataBinding对象
     */
    protected lateinit var mViewBinding: T2

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:30
     * @Note   Activity创建时进行相关的配置
     * @param  ...
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /**父类初始化*/
        super.onCreate(savedInstanceState)
        /**创建根视图*/
        val rootView = layoutInflater.inflate(getLayId(), null, false)
        /**初始化Databinding对象*/
        mViewBinding = DataBindingUtil.bind(rootView)
        /**设置根视图到Activity*/
        setContentView(rootView)
        /**执行抽象方法初始化Dagger相应操作*/
        bindDagger()
        /**Presenter绑定View*/
        attachView()
        /**执行初始化操作*/
        init()
        /**执行绑定event操作*/
        bindEvent()
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:34
     * @Note   Activity销毁回调
     */
    override fun onDestroy() {
        super.onDestroy()
        /**Presenter解除绑定*/
        presenter.detachView()
        /**DataBinding解除绑定*/
        mViewBinding.unbind()
        /**执行抽象方法destory()*/
        destory()
        /**处理android4.4.2 底层内存泄漏*/
        fixInputMethodManagerLeak(activity)
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:39
     * @Note   获取Activit_LayoutId
     * @param  ...
     */
    protected abstract fun getLayId(): Int

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:44
     * @Note   执行绑定Dagger操作
     */
    protected abstract fun bindDagger()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:45
     * @Note   执行初始化操作
     */
    protected abstract fun init()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:45
     * @Note   执行绑定事件操作
     */
    protected abstract fun bindEvent()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:46
     * @Note   执行销毁相关操作
     */
    protected abstract fun destory()

    private fun attachView() {
        presenter.attachView(this , api)
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseActivity
     * @Data   2017/12/26 上午9:50
     * @Note   处理4.4.2 Android底层内存泄漏
     * @param  destContext 上下文
     */
    private fun fixInputMethodManagerLeak(destContext: Context?) {
        if (destContext == null) {
            return
        }
        val imm = destContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager ?: return
        val arr = arrayOf("mCurRootView", "mServedView", "mNextServedView")
        var f: Field?
        var obj_get: Any?
        arr.indices
                .asSequence()
                .map { arr[it] }
                .forEach {
                    try {
                        f = imm.javaClass.getDeclaredField(it)
                        if (f!!.isAccessible === false) {
                            f!!.isAccessible = true
                        }
                        obj_get = f!!.get(imm)
                        if (obj_get != null && obj_get is View) {
                            val v_get = obj_get as View?
                            if (v_get!!.getContext() === destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                                f!!.set(imm, null) // 置空，破坏掉path to gc节点
                            }
                        }
                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }
                }
    }
}