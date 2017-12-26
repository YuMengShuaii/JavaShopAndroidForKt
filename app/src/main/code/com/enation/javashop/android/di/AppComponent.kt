package com.enation.javashop.android.di

import com.enation.javashop.android.activity.ActivityImage
import com.enation.javashop.android.activity.MainActivity
import com.enation.javashop.android.fragment.FragmentList
import com.enation.javashop.android.fragment.MainFragment
import com.enation.javashop.android.lib.di.ApplicationComponent
import dagger.Component

/**
 * Created by LDD on 17/8/9.
 */
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: MainFragment)

    fun inject(fragment: FragmentList)

    fun inject(activity: ActivityImage)
}