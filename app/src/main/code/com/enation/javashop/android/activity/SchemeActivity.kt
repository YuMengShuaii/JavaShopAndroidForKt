package com.enation.javashop.android.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.enation.javashop.android.jrouter.JRouter
import com.enation.javashop.android.jrouter.logic.datainfo.Postcard
import com.enation.javashop.android.jrouter.logic.listener.NavListener

/**
 * Created by LDD on 2017/9/10.
 */
class SchemeActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent.data

        JRouter.prepare().create(uri).seek(this,object:NavListener(){
            override fun onArrival(postcard: Postcard?) {
                finish()
            }
        })

    }

}