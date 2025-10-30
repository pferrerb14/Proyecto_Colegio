package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.model.entity.ambient.User
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class LoginViewModel : ViewModelAmbient<Any, LoginIntentEvent>() {

    val user = User()
    private val student = Student()
    private val teacher = Teacher()

    private var userType = STUDENT_TYPE

    override fun event(action: Int) {
        when (action) {
            STUDENT_TYPE -> {
                studentType()
            }

            TEACHER_TYPE -> {
                teacherType()
            }

            LOGIN_VALIDATION -> {
                loginValidation()
            }

            STUDENT_REGISTER -> {
                studentRegister()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            LOGIN_FIREBASE -> {
                loginFirebase()
            }

            DETAIL_FIREBASE -> {
                detailFirebase()
            }

            USER_PROTO_DATA_STORE -> {
                userProtoDataStore()
            }
        }
    }

    private fun studentType() {
        userType = STUDENT_TYPE
        emit(LoginIntentEvent.StudentSelect)
    }

    private fun teacherType() {
        userType = TEACHER_TYPE
        emit(LoginIntentEvent.TeacherSelect)
    }

    private fun loginValidation() {
        when {
            user.isLogin() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_empty_fields_message)))
            }

            user.isEmail() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_email_message)))
            }

            user.isPassword() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_password_message)))
            }

            !user.isPasswordLength() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.password_length_message)))
            }

            !user.isEmailFormat() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_email_format_message)))
            }

            else -> {
                loading(idService = LOGIN_FIREBASE)
            }
        }
    }

    private fun loginFirebase() {
        user.loginFirebase(success = { email ->
            loading(idService = DETAIL_FIREBASE, isDelayDisable = false)
        }, error = {
            emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_user_message)))
            loading()
        })
    }

    private fun detailFirebase() {
        user.detailFirebase(success = { name, lastName, age, sex, code, teacherName, coins ->
            when (userType) {
                STUDENT_TYPE -> {
                    student.name = name
                    student.lastName = lastName
                    student.age = age
                    student.sex = sex
                    student.teacher = Teacher(name = teacherName)
                    student.coins = coins
                }

                TEACHER_TYPE -> {
                    teacher.name = name
                    teacher.lastName = lastName
                    teacher.age = age
                    teacher.sex = sex
                    teacher.code = code
                }
            }

            when {
                userType == STUDENT_TYPE && student.isCoins() -> {
                    emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.profile_student_message)))
                    loading()
                }

                userType == TEACHER_TYPE && teacher.isCode() -> {
                    emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.profile_teacher_message)))
                    loading()
                }

                else -> {
                    loading(idService = USER_PROTO_DATA_STORE, isDelayDisable = false)
                }
            }
        }, error = {
            emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_user_message)))
            loading()
        })
    }

    private fun studentRegister() {
        emit(event = LoginIntentEvent.StudentRegister)
    }

    suspend fun userProtoDataStore() {
        when (userType) {
            STUDENT_TYPE -> {
                with(UserProtoDataStoreConfig) {
                    setName(student.name)
                    setLastName(student.lastName)
                    setAge(student.age)
                    setSex(student.sex)
                    setNameTeacher(student.teacher.name)
                    setCoins(student.coins)
                }
            }

            TEACHER_TYPE -> {
                with(UserProtoDataStoreConfig) {
                    setName(teacher.name)
                    setLastName(teacher.lastName)
                    setAge(teacher.age)
                    setSex(teacher.sex)
                    setCode(teacher.code)
                }
            }
        }

        emit(event = LoginIntentEvent.HomeActivity)
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = LoginIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val STUDENT_TYPE = 0
        const val TEACHER_TYPE = 1
        const val LOGIN_VALIDATION = 2
        const val STUDENT_REGISTER = 3
        private const val LOGIN_FIREBASE = 4

        private const val DETAIL_FIREBASE = 5
        private const val USER_PROTO_DATA_STORE = 6
    }
}