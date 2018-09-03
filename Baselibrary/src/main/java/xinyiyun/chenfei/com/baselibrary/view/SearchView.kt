package com.logistics.management.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_search_layout.view.*
import org.jetbrains.anko.include
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import xinyiyun.chenfei.com.baselibrary.R

/**
 * Created by YangQiang on 2017/11/22.
 */
class SearchView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {


        include<View>(R.layout.view_search_layout) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.SearchView)
            attrs?.apply {
                edit_query.hint = a.getString(R.styleable.SearchView_hint)
            }
            a.recycle()

            img_close.onClick {
                edit_query.setText("")
            }
            edit_query.textChangedListener {
                afterTextChanged {
                    edit_query.text.toString().apply {
                        if (isEmpty()) {
                            img_close.visibility = View.GONE
                        } else {
                            img_close.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    fun onQuery(queryListener: String.() -> Unit) {
        btn_query.onClick {
            queryListener?.apply {
                queryListener.invoke(edit_query.text.toString())
            }
        }
    }

    fun getQuerContent() = edit_query.text.toString()

}


