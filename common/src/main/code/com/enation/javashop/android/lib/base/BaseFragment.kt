package com.enation.javashop.android.lib.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enation.javashop.android.lib.api.ApiService
import com.enation.javashop.utils.base.tool.BaseInterface
import javax.inject.Inject

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午11:26
 * @From   com.enation.javashop.android.lib.base
 * @Note   Fragment基类
 */
  abstract class BaseFragment<T : BaseContract.BasePresenter, T2 : ViewDataBinding> : Fragment() {

    /**
     * @Name  layout
     * @Type  android.view.View
     * @Note  View根视图
     */
    private var layout: View? = null

    /**
     * @Name  activity
     * @Type  com.enation.javashop.android.lib.base.BaseActivity
     * @Note  Fragment宿主Activity
     */
    protected lateinit var activity: BaseActivity<*, *>

    /**
     * @Name  mViewDataBinding
     * @Type  T2 : ViewDataBinding
     * @Note  DataBinding对象
     */
    protected lateinit var mViewDataBinding: T2
    
    /**
     * @Name  presenter
     * @Type  T : BaseContract.BasePresenter
     * @Note  Fargment的Presenter Dagger自动注入
     */
    @Inject
    protected lateinit var presenter: T

    /**
     * @Name  api
     * @Type  com.enation.javashop.android.lib.api.ApiService
     * @Note  API Dagger自动注入
     */
    @Inject
    internal lateinit var api: ApiService

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:38
     * @Note   Fragment创建时调用
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /**初始化根视图及DataBinding*/
        if (layout == null) {
            layout = inflater.inflate(getLayId(), null)
            mViewDataBinding = DataBindingUtil.bind<T2>(layout)
        }
        /**初始化宿主Activity*/
        activity = getActivity() as BaseActivity<*, *>
        return layout
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:43
     * @Note   在Fragment视图创建完毕后调用
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**绑定Dagger*/
        bindDagger()

        /**绑定视图*/
        attachView()

        /**执行其他初始化操作*/
        init()

        /**初始化响应事件*/
        bindEvent()

    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:45
     * @Note   在Fragment销毁时调用
     */
    override fun onDestroyView() {

        /**调用父类方法*/
        super.onDestroyView()

        /**ViewBinding解除绑定*/
        mViewDataBinding.unbind()

        /**Presenter执行销毁操作*/
        presenter.detachView()

        /**执行其他销毁操作*/
        destory()
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:47
     * @Note   获取根视图LayoutId
     * @return LayoutId
     */
    protected abstract fun getLayId(): Int

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:47
     * @Note   绑定Dagger
     */
    protected abstract fun bindDagger()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:50
     * @Note   初始化其他操作
     */
    protected abstract fun init()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:50
     * @Note   绑定事件
     */
    protected abstract fun bindEvent()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:50
     * @Note   其他销毁操作
     */
    protected abstract fun destory()

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:52
     * @Note   销毁View操作
     */
    private fun attachView() {
        presenter.attachView(this, api)
    }

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.base.BaseFragment
     * @Data   2017/12/26 上午11:57
     * @Note   获取工具对象
     * @return 工具对象
     */
    protected fun getUtils(): BaseInterface {

        return activity
    }
}