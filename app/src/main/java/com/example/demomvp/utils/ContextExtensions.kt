package com.example.demomvp.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, time).show()
}