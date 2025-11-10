package com.leandro1995.seito.model.entity.ambient

import android.os.Parcelable
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.extension.isEmailFormat
import com.leandro1995.seito.fcm.authentication.AuthenticationFCM
import com.leandro1995.seito.fcm.firestore.UserFirestoreFCM
import com.leandro1995.seito.model.entity.Course
import kotlinx.parcelize.Parcelize

@Parcelize
open class User(
    open var name: String = "",
    open var lastName: String = "",
    open var email: String = "",
    open var age: Int = -1,
    open var sex: String = "",
    open var password: String = ""
) : Parcelable {

    fun loginFirebase(success: (email: String) -> Unit, error: () -> Unit) {
        AuthenticationFCM().signInWithEmailAndPassword(
            email = email, password = password, success = success, error = error
        )
    }

    fun detailFirebase(
        success: (
            name: String, lastName: String, age: Int, sex: String, code: String, teacherName: String, coins: Int, email: String
        ) -> Unit, error: () -> Unit
    ) {
        UserFirestoreFCM().whereEqualToEmail(email = email, success = success, error = error)
    }

    fun courseFirebaseArrayList(success: (ArrayList<Course>) -> Unit, error: () -> Unit) {
        UserFirestoreFCM().courseArrayList(success = success, error = error)
    }

    fun fullName() = "$name $lastName"

    fun isLogin() = isEmail() && isPassword()

    fun isEmail() = email.isEmpty()

    fun isPassword() = password.isEmpty()

    fun isPasswordLength() = password.length >= Setting.PASSWORD_LENGTH

    fun isEmailFormat() = email.isEmailFormat()

    fun isName() = name.isEmpty()

    fun isLastName() = lastName.isEmpty()
}