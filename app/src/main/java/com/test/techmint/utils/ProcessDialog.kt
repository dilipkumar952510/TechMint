package com.test.techmint.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.test.techmint.R


object ProcessDialog {
    private var progressDialog: Dialog? = null

    @JvmStatic
    fun startDialog(context: Context) {
        if (!isShowing()) {
            if (!(context as Activity).isFinishing) {
                progressDialog = Dialog(context)
                progressDialog!!.setCancelable(false)
                progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                progressDialog!!.setContentView(R.layout.process_dialog)
                progressDialog!!.show()
            }
        }
    }

    @JvmStatic
    fun dismissDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            progressDialog = null
        }
    }

    private fun isShowing(): Boolean {
        return if (progressDialog != null) {
            progressDialog!!.isShowing
        } else {
            false
        }
    }
}