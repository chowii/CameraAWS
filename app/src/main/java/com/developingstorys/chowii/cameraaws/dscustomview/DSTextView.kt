package com.developingstorys.chowii.cameraaws.dscustomview

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import com.developingstorys.chowii.cameraaws.R.color.text_white

/**
 * Created by chowii on 13/08/16.
 */

class DSTextView: TextView {
    private var fontCache: HashMap<String, Typeface> = HashMap()

    constructor(context: Context)
            :super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet)
            :super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            :super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    internal fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        val dsTypeface: Typeface? = getTypeface(context, "fonts/DosisRegular.ttf")
        typeface = dsTypeface
        this.setTextColor(ContextCompat.getColor(context, text_white))
        this.setAllCaps(false)
    }

    private fun getTypeface(context: Context, fontName: String): Typeface? {
        var typeface: Typeface? = fontCache[fontName]

        if(typeface == null){
            typeface = Typeface.createFromAsset(context.assets, fontName)
            fontCache.put(fontName, typeface)
        }
        return typeface
    }

}