package com.rishabh.intricare.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rishabh.intricare.data.model.PostOffice
import kotlinx.coroutines.flow.Flow

@Dao
interface PostOfficeDao {

    @Query("SELECT * FROM post_office")
    fun getAllPostOffices(): LiveData<List<PostOffice>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(postOffice: List<PostOffice>)
}