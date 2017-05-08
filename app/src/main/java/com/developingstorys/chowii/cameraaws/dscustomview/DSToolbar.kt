package com.developingstorys.chowii.cameraaws.dscustomview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.developingstorys.chowii.cameraaws.R
import com.developingstorys.chowii.cameraaws.R.id.fragment_container

/**
 * Created by chowii on 13/08/16.
 */
class DSToolbar: Fragment(), View.OnClickListener {

//    val send_aws: ImageButton? by bindOptionalView(R.id.send_aws)
//    val titleTextView: TextView? by bindOptionalView(R.id.titleTextView)
//    val toolbar: RelativeLayout? by bindOptionalView(R.id.ds_toolbar_root)

    var menuCheckBoxSelect: Boolean = false

    var unbind: Unbinder? = null
    var menu: DSMenu? = null
    var open: Boolean = false
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.ds_toolbar, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        unbind = ButterKnife.bind(this, activity)
        menu = DSMenu()
        menu?.let {
            menu?.checkBOX(object: DSMenu.onSelectedListener{
                override fun onSelected(select: Boolean) {
                    menuCheckBoxSelect = select
                }
            })

        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        titleTextView?.text = getString(R.string.app_name)
//        titleTextView?.textSize = 15f
//        titleTextView?.setLineSpacing(14f,4f)
//        send_aws?.setOnClickListener(this)
    }
    public fun getCheckBoxValue(): Boolean{
        menu?.let{
            (menu as DSMenu).checkBOX(object: DSMenu.onSelectedListener{
                override fun onSelected(select: Boolean){
                    menuCheckBoxSelect =  select
                }
            })
        }
        return menuCheckBoxSelect
    }


    override fun onClick(v: View){
//        if(v.id == R.id.send_aws){
//            if(open){
//                open = false
//                fragmentManager
//                        .beginTransaction()
//                        .remove(menu)
//                        .commit()
//            }else{
//                open = true
//                fragmentManager
//                        .beginTransaction()
//                        .add(fragment_container, menu)
//                        .commit()
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        unbind?.unbind()
        unbind = null
    }
}