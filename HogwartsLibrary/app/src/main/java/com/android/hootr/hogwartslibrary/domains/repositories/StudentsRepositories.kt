package com.android.hootr.hogwartslibrary.domains.repositories

import com.android.hootr.hogwartslibrary.data.models.CharacterRemote
import com.android.hootr.hogwartslibrary.domains.models.StudentModel

interface StudentsRepositories {
    suspend fun fetchStudents(): List<StudentModel>
}