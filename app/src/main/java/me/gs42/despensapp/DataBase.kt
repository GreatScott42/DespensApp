package me.gs42.despensapp

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}




