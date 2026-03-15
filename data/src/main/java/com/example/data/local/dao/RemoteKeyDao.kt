package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Query("SELECT * FROM remote_keys WHERE gameId = :id AND genreSlug = :genreSlug")
    suspend fun getRemoteKeyByIdAndGenre(id: Int, genreSlug: String): RemoteKeyEntity?

    @Query("DELETE FROM remote_keys WHERE genreSlug = :genreSlug")
    suspend fun clearRemoteKeysByGenre(genreSlug: String)
}
