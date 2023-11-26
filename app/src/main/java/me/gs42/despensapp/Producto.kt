package me.gs42.despensapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Nombre") val nombre: String?,
    @ColumnInfo(name = "Precio") val precio: Int?
)