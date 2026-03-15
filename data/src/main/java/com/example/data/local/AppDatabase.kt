package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.GameDao
import com.example.data.local.dao.RemoteKeyDao
import com.example.data.local.entity.GameEntity
import com.example.data.local.entity.GameDetailsEntity
import com.example.data.local.entity.RemoteKeyEntity

@Database(
    entities = [GameEntity::class, RemoteKeyEntity::class, GameDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
