package com.developingstorys.chowii.cameraaws.dscustomview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.developingstorys.chowii.cameraaws.R
import com.developingstorys.chowii.cameraaws.R.color.text_white

/**
 * Created by chowii on 13/08/16.
 */
class DSMenu: Fragment() {


    val checkBok: TextView? by bindOptionalView(R.id.ds_checkBox)
    val menu_root: LinearLayout? by bindOptionalView(R.id.ds_menu_root)
    var unbind: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.ds_menu, container, false)
        unbind = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkBok?.text = getString(R.string.action_send_aws)
        checkBok?.setTextColor(ContextCompat.getColor(context, text_white))
        Log.d("TAF", "menu created" + unbind)
    }

    override fun onPause() {
        super.onPause()
        unbind?.unbind()
        unbind = null
    }

}