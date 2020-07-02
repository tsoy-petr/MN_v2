package com.android.hootr.hogwartslibrary.domains.repositories

import com.android.hootr.hogwartslibrary.domains.models.FacultyModel

interface HatRepositories {

    suspend fun generateFaculty(userName: String): FacultyModel
}