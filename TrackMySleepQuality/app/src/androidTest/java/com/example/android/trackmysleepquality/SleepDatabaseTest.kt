/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SleepDatabaseTest {

    private lateinit var sleepDao: SleepDatabaseDao
    private lateinit var db: SleepDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, SleepDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        sleepDao = db.sleepDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() = runBlocking {
        val night = SleepNight(sleepQuality = 9)
        sleepDao.insert(night)
        val tonight = sleepDao.getTonight()
        assertThat(tonight?.sleepQuality, `is` (9))
    }

    @Test
    @Throws(Exception::class)
    fun update() = runBlocking {
        var night = SleepNight(nightId = 1L, sleepQuality = 3)
        sleepDao.insert(night)
        var tonight = SleepNight(nightId = 1L, sleepQuality = 4)
        sleepDao.update(tonight)
        val currentNight = sleepDao.getTonight()
        assertThat(currentNight?.sleepQuality, `is` (4))
    }

    @Test
    @Throws(Exception::class)
    fun get() = runBlocking {
        var night = SleepNight(nightId = 1L)
        sleepDao.insert(night)
        var id = 1L;
        var selectedNight = sleepDao.get(id)
        assertThat(selectedNight?.nightId, `is` (1L))
    }

    @Test
    @Throws(Exception::class)
    fun clear() = runBlocking {
        var night = SleepNight()
        sleepDao.insert(night)
        sleepDao.clear()
        val tonight = sleepDao.getTonight()
        assertNull(tonight)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Test
    @Throws(Exception::class)
    fun getAllNights() = runBlocking {
        var night = SleepNight(nightId = 1)
        sleepDao.insert(night)
        var nextNight = SleepNight(nightId = 2)
        sleepDao.insert(nextNight)
        val allNights = sleepDao.getAllNights()
        allNights.getOrAwaitValue()

        assertThat(allNights.value?.isNotEmpty(), `is` (true))
        assertThat(allNights.value?.size, `is` (2))
    }
}