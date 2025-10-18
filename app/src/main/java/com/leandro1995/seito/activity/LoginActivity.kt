package com.leandro1995.seito.activity

import android.os.Bundle
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient

class LoginActivity : ActivityAmbient() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
    }
}