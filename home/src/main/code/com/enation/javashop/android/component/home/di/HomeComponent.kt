package com.enation.javashop.android.component.home.di

import com.enation.javashop.android.component.home.fragment.HomeFragment
import com.enation.javashop.android.lib.di.ApplicationComponent
import dagger.Component

@Component(dependencies = arrayOf(ApplicationComponent::class))
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}