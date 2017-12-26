package com.enation.javashop.android.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.enation.javashop.android.R
import com.enation.javashop.android.databinding.FragmentListItemBinding

/**
 * Created by LDD on 2017/8/22.
 */
class ListAdapter :BaseAdapter {
    private var data : ArrayList<String>
    private var layoutinflater :LayoutInflater
    constructor(data: ArrayList<String>, context: Context) : super() {
        this.data = data
        this.layoutinflater = LayoutInflater.from(context)
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var pp = p1
        var binding :FragmentListItemBinding
        if (pp==null) {
            pp = layoutinflater.inflate(R.layout.fragment_list_item,null)
            binding = DataBindingUtil.bind<FragmentListItemBinding>(pp)
            pp!!.tag = binding
       }else{
            binding = pp!!.tag as FragmentListItemBinding
       }
            binding.imageview.setImageResource(R.mipmap.ic_launcher)
        return pp
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }


}