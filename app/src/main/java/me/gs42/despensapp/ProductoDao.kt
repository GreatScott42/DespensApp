package me.gs42.despensapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAll(): List<Producto>

    @Query("SELECT * FROM producto WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Producto>

    @Query("SELECT * FROM producto WHERE Nombre LIKE :first AND " +
            "Precio LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Producto

    @Insert
    fun insertAll(vararg productos: Producto)

    @Delete
    fun delete(user: Producto)
}