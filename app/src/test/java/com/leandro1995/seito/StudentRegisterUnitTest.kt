package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.StudentRegisterIntentEvent
import com.leandro1995.seito.viewmodel.StudentRegisterViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StudentRegisterUnitTest : TestAmbient() {

    private val studentRegisterViewModel = StudentRegisterViewModel()

    @Test
    fun isEmptyStudentName() = runBlocking {
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentLastName() = runBlocking {
        studentRegisterViewModel.student.apply {
            name = "Leandro"
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentEmail() = runBlocking {
        studentRegisterViewModel.student.apply {
            name = "Leandro"
            lastName = "Castillo Borja"
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isStudentEmailFormat() = runBlocking {
        studentRegisterViewModel.student.apply {
            name = "Leandro"
            lastName = "Castillo Borja"
            email = "leccbo45454"
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentPassword() = runBlocking {
        studentRegisterViewModel.student.apply {
            name = "Leandro"
            lastName = "Castillo Borja"
            email = "leccbo@gmail.com"
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentPasswordLength() = runBlocking {
        studentRegisterViewModel.student.apply {
            name = "Leandro"
            lastName = "Castillo Borja"
            email = "leccbo@gmail.com"
            password = "1234"
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentConfirmPassword() = runBlocking {
        studentRegisterViewModel.apply {
            student.apply {
                name = "Leandro"
                lastName = "Castillo Borja"
                email = "leccbo@gmail.com"
                password = "123456"
            }
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isEmptyStudentConfirmPasswordLength() = runBlocking {
        studentRegisterViewModel.apply {
            confirmPassword = "12354"
            student.apply {
                name = "Leandro"
                lastName = "Castillo Borja"
                email = "leccbo@gmail.com"
                password = "123456"
            }
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun studentConfirmPassword() = runBlocking {
        studentRegisterViewModel.apply {
            confirmPassword = "123056"
            student.apply {
                name = "Leandro"
                lastName = "Castillo Borja"
                email = "leccbo@gmail.com"
                password = "123456"
            }
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isAgeStudent() = runBlocking {
        studentRegisterViewModel.apply {
            confirmPassword = "123456"
            student.apply {
                name = "Leandro"
                lastName = "Castillo Borja"
                email = "leccbo@gmail.com"
                password = "123456"
            }
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }

    @Test
    fun isAgeRangeStudent() = runBlocking {
        studentRegisterViewModel.apply {
            confirmPassword = "123456"
            student.apply {
                name = "Leandro"
                lastName = "Castillo Borja"
                email = "leccbo@gmail.com"
                password = "123456"
                age = 20
            }
        }
        test<StudentRegisterIntentEvent.AlertMessage>(
            sharedFlow = studentRegisterViewModel.event, action = {
                studentRegisterViewModel.button.invoke(StudentRegisterViewModel.STUDENT_VALIDATION)
            })
    }
}