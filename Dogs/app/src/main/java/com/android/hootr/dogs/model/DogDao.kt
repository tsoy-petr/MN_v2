package com.android.hootr.dogs.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {

    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("select * from dogbreed")
    suspend fun gelAllDogs(): List<DogBreed>

    @Query("select * from dogbreed where uuid = :dogId")
    suspend fun getDos(dogId: Int): DogBreed

    @Query("delete from dogbreed")
    suspend fun deleteAllDogs()
}