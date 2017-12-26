package apkbuild

import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.lib.base.BaseApplication
import com.enation.javashop.net.engine.config.NetEngineConfig

/**
 * Created by LDD on 2017/8/23.
 */
class HomeApplication :BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        NetEngineConfig.init(applicationContext).openLogger()
        JRouter.init(this)
        JRouter.openDebug()
        JRouter.openLog()
        JRouter.prepare().create("/home/launch").seek()
    }
}