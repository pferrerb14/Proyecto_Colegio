package com.leandro1995.seito.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityStudentRegisterBinding

class StudentRegisterActivity : ActivityAmbient<ActivityStudentRegisterBinding>() {

    override var idLayout: Int = R.layout.activity_student_register
}