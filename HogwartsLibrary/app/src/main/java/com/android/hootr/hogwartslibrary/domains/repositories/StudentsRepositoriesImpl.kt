package com.android.hootr.hogwartslibrary.domains.repositories

import com.android.hootr.hogwartslibrary.data.models.CharacterRemote
import com.android.hootr.hogwartslibrary.data.netwrok.RetrofitFactory
import com.android.hootr.hogwartslibrary.domains.models.StudentModel
import com.android.hootr.hogwartslibrary.domains.models.mapToModel
import kotlinx.coroutines.delay

class StudentsRepositoriesImpl: StudentsRepositories {
    override suspend fun fetchStudents(): List<StudentModel> {
        return RetrofitFactory.instance.charactersService.getAllCharacters(RetrofitFactory.key)
            .filter { it.role == "student" }
            .filter { it.house.isNotEmpty() }
            .map { it.mapToModel() }
    }
}