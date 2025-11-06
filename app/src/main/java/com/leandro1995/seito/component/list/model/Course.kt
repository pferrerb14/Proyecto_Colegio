package com.leandro1995.seito.component.list.model

import com.leandro1995.seito.R

class Course(
    private val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val videoUrl: String = "",
    val themeArrayList: ArrayList<Theme> = arrayListOf()
) {

    fun iconCourseType() = when (id) {
        COURSE_ALGEBRA -> R.drawable.ic_algebra
        COURSE_GEOMETRY -> R.drawable.ic_geometry
        else -> null
    }

    companion object {
        private const val COURSE_ALGEBRA = "COURSE_ALGEBRA"
        private const val COURSE_GEOMETRY = "COURSE_GEOMETRY"
    }
}