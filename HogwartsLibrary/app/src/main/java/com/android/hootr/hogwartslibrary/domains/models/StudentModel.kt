package com.android.hootr.hogwartslibrary.domains.models

import com.android.hootr.hogwartslibrary.data.models.CharacterRemote

data class StudentModel(
    val id: String, val name: String, val facultyName: String, val bloodStatus: String
)

fun CharacterRemote.mapToModel(): StudentModel {
    return StudentModel(id = this._id, name = this.name, facultyName = this.house, bloodStatus = this.bloodStatus)
}