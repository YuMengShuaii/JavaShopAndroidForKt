package com.enation.javashop.android.lib.utils

/**
 * @Coder  LDD
 * @Data   2017/12/22 下午4:45
 * @From   com.enation.javashop.android.lib.utils
 * @Note   扩展方法Koltin文件
 */

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.lib.R
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.net.engine.plugin.rxbus.RxBus

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:47
 * @Note   debug日志打印
 * @param  tag 标记
 * @param  message 日志信息
 */
fun debugLog(tag:String?, message: String?){
    Log.i(tag,message)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:47
 * @Note   error日志打印
 * @param  tag 标记
 * @param  message 日志信息
 */
fun errorLog(tag:String,message: String){
    Log.e(tag,message)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:47
 * @Note   显示Toast信息
 * @param  message 需要显示的信息
 */
fun showMessage(message:String){
    Toast.makeText(BaseApplication.appContext,message,Toast.LENGTH_SHORT).show()
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:48
 * @Note   数据类型转换
 * @return 转换后的值
 */
fun <T> Any.to() :T{
    return this as T
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:49
 * @Note   通过JRouter框架检索对象，并实例化
 * @param  path 对象注册到JRouter的路径
 */
fun <T> acquireInstance(path :String):T{
    return JRouter.prepare().create(path).seek().to()
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:51
 * @Note   跳转页面Activity扩展方法
 * @param  path Activity注册到JRouter中的路径
 */
fun AppCompatActivity.push(path :String){
    JRouter.prepare().create(path).withTransition(R.anim.push_left_in,R.anim.push_left_out).seek(this)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:51
 * @Note   跳转页面 V4 Fragment扩展方法
 * @param  path Activity注册到JRouter中的路径
 */
fun android.support.v4.app.Fragment.push(path :String){
    JRouter.prepare().create(path).withTransition(R.anim.push_left_in,R.anim.push_left_out).seek(activity)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:51
 * @Note   跳转页面 V7 Fragment扩展方法
 * @param  path Activity注册到JRouter中的路径
 */
fun Fragment.push(path :String){
    JRouter.prepare().create(path).withTransition(R.anim.push_left_in,R.anim.push_left_out).seek(activity)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:53
 * @Note   Activity 退出扩展方法
 */
fun AppCompatActivity.pop(){
    finish()
    overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午4:53
 * @Note   用于代码规范化 有助于代码美观 一般用于配置对象时对代码格式化
 *         示例: val linearLayout = LinearLayout(context).then {
 *                  lay ->
 *                  lay.orientation = LinearLayout.HORIZONTAL
 *                  lay.setBackgroundColor(Color.RED)
 *              }.removeAllViews()
 * @param  _block 回调
 */
fun <T :Any> T.then(_block: (T)->Unit):T{

    _block.invoke(this)
    return this
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:04
 * @Note   用于代码格式化 与then不同的是 该方法不继续传递self 一般用于格式化对象初始化代码
 *         示例: val view = View(context).more {
 *                  self ->
 *                  self.setBackgroundColor(Color.BLUE)
 *                  self.setFadingEdgeLength(1)
 *                  self.setOnClickListener{}
 *              }
 * @param  _block 回调
 */
fun <T :Any> T.more(_block: (T)->Unit){
    _block.invoke(this)
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:10
 * @Note   获取事件序列中心
 * @return RxBus事件中心
 */
fun getEventCenter(): RxBus {
    return  RxBus.getDefault()
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:13
 * @Note   当变量为空时执行
 * @param  _block 回调
 */
fun Any?.haventDo(_block :() ->Unit){
    if (this == null){
        _block.invoke()
        return
    }else if(this is String){
        if (this == ""){
            _block.invoke()
        }
    }
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:15
 * @Note   当一个序列 都为空时 执行该回调
 * @param  array 需要判断空的序列
 * @param  _block 回调
 */
fun haventDo(vararg array :Any?,_block :() ->Unit){
    var has = false
    for (any in array) {
        if (any != null){
            has = true
            if(any is String){
                if (any == ""){
                    has = false
                }
            }
        }else{
            has = false
        }
    }
    if (!has){
        _block.invoke()
    }
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:13
 * @Note   当变量不为空时执行
 * @param  _block 回调
 */
fun Any?.haveDo(_block :() ->Unit){
    if (this !=null){
        if (this is String){
            if (this == ""){
                return
            }
        }
        _block.invoke()
    }
}

/**
 * @Coder  LDD
 * @From   com.enation.javashop.android.lib.utils ExtendMethods.kt
 * @Data   2017/12/22 下午5:15
 * @Note   当一个序列 都不为空时 执行该回调
 * @param  array 需要判断空的序列
 * @param  _block 回调
 */
fun haveDo(vararg array: Any?,_block: () -> Unit){
    var has = true
    for (any in array) {
        if (any == null){
            has = false
        }else if(any is String){
            if (any == ""){
                has = false
            }
        }
    }
    if (has){
        _block.invoke()
    }
}