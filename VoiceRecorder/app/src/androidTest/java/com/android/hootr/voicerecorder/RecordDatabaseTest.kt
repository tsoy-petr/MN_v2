package com.android.hootr.voicerecorder

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.hootr.voicerecorder.database.RecordDatabase
import com.android.hootr.voicerecorder.database.RecordingDatabaseDao
import com.android.hootr.voicerecorder.database.RecordingItem
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RecordDatabaseTest {

    private lateinit var recordDatabaseDao: RecordingDatabaseDao
    private lateinit var database: RecordDatabase

    @Before
    fun createDb() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RecordDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        recordDatabaseDao = database.recordDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testDatabase() {
        recordDatabaseDao.insert(RecordingItem())
        val getCount = recordDatabaseDao.getCount()
        assert(getCount.value == 1)
    }
}