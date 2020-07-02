package com.android.hootr.hogwartslibrary.domains.repositories

import com.android.hootr.hogwartslibrary.domains.models.FacultyModel
import kotlinx.coroutines.delay

class HatRepositoriesImpl: HatRepositories {
    override suspend fun generateFaculty(userName: String): FacultyModel {
        delay(5000)
        return FacultyModel("Griffindor")
    }
}