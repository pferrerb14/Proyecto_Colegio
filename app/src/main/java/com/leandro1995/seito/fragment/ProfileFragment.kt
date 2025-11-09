package com.leandro1995.seito.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import com.leandro1995.seito.BuildConfig
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.LoginActivity
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.databinding.FragmentProfileBinding
import com.leandro1995.seito.extension.capsSentences
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ProfileIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ProfileIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ProfileIntentActionConfig
import com.leandro1995.seito.intent.config.event.ProfileIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.ProfileViewModel

class ProfileFragment : FragmentAmbient<FragmentProfileBinding>(), ProfileIntentEventCallBack,
    ProfileIntentActionCallBack {

    private val profileViewModel by viewModels<ProfileViewModel>()

    private val profileIntentEventConfig =
        ProfileIntentEventConfig(profileIntentEventCallBack = this)

    private val profileIntentActionConfig =
        ProfileIntentActionConfig(profileIntentActionCallBack = this)

    private val backGroundCoroutine = BackGroundCoroutine()

    override var idLayout: Int = R.layout.fragment_profile

    override fun initView() {
        dataBinding?.apply {
            profileViewModel = this@ProfileFragment.profileViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar, idTitle = R.string.profile_title
            ).config()
            versionText.text = getString(R.string.version_text, BuildConfig.VERSION_NAME)
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            profileViewModel.event.collect { profileIntentEvent ->
                profileIntentEventConfig.initConfig(event = profileIntentEvent)
            }
        }

        lifecycleScope {
            profileViewModel.action.collect { profileIntentAction ->
                profileIntentActionConfig.initConfig(event = profileIntentAction)
            }
        }
    }

    override fun cleanProtoDataStore() {
        backGroundCoroutine.start {
            UserProtoDataStoreConfig.let {
                it.setName(name = "")
                it.setLastName(lastName = "")
                it.setAge(age = -1)
                it.setSex(sex = "")
                it.setCode(code = "")
                it.setEmail(email = "")
                it.setCoins(coins = -1)
                it.setNameTeacher(nameTeacher = "")
            }

            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finishAffinity()
        }
    }

    override fun getProtoDataStore() {
        backGroundCoroutine.start {
            UserProtoDataStoreConfig.apply {
                profileViewModel.protoDataStore(
                    isUserType = getIsUserType(),
                    name = getName(),
                    lastName = getLastName(),
                    email = getEmail(),
                    coins = getCoins(),
                    code = getCode()
                )
            }

            profileViewModel.button.invoke(ProfileViewModel.GET_PROTO_DATA_STORE)
        }
    }

    override fun studentView(student: Student) {
        dataBinding?.apply {
            nameText.text = student.fullName().capsSentences()
            initialComponent.setText(text = student.fullName())
            emailText.text = student.email
            valueProfileText.text = getString(R.string.available_coins_text)
            valueProfileImage.setImageResource(R.drawable.ic_coins)
            valueText.text = student.coins.toString()
            rolText.text = getString(R.string.student_type_text)
            roleUserText.text = getString(R.string.student_type_text)
        }
    }

    override fun teacherView(teacher: Teacher) {
        dataBinding?.apply {
            nameText.text = teacher.fullName().capsSentences()
            initialComponent.setText(text = teacher.fullName())
            emailText.text = teacher.email
            valueProfileText.text = getString(R.string.code_text)
            valueProfileImage.setImageResource(R.drawable.ic_code)
            valueText.text = teacher.code
            rolText.text = getString(R.string.teacher_type_text)
            roleUserText.text = getString(R.string.teacher_type_text)
        }
    }
}