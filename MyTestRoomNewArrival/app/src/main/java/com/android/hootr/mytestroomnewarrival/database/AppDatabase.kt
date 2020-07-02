package com.android.hootr.mytestroomnewarrival.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.hootr.mytestroomnewarrival.pojo.db.BarcodeNewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.GoodsNewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.NewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.Series

@Database(entities = [GoodsNewArrival::class, NewArrival::class, Series::class, BarcodeNewArrival::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "base_1c.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {

            synchronized(LOCK) {
                db?.let { return it}

                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {

                            super.onCreate(db)



                        }
                    })
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() //разрешеат работать с БД в mainThread
                    .build()

                db = instance

                return instance

            }
        }
    }

    abstract fun newArrivalDao(): NewArrivalDao
}