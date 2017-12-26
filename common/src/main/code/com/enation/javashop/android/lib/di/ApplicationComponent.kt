package com.enation.javashop.android.lib.di

import com.enation.javashop.android.lib.api.ApiService
import dagger.Component

/**
 * @Coder  LDD
 * @Data   2017/12/26 上午7:29
 * @From   com.enation.javashop.android.lib.di
 * @Note   Dogger依赖注入基础模块
 */
@Component(modules = arrayOf(MainModule::class))
interface ApplicationComponent {

    /**
     * @Coder  LDD
     * @From   com.enation.javashop.android.lib.di ApplicationComponent
     * @Data   2017/12/26 上午7:30
     * @Note   API对象提供者
     * @return 全局API
     */
    fun provideApi(): ApiService
}
