package me.gs42.despensapp

import android.os.Parcel
import android.os.Parcelable

data class ProductB(
    val name: String?,

    val cantidad: Int,
    val precio: Int,
    val tienda: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()

    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(cantidad)
        parcel.writeInt(precio)
        parcel.writeString(tienda)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductB> {
        override fun createFromParcel(parcel: Parcel): ProductB {
            return ProductB(parcel)
        }

        override fun newArray(size: Int): Array<ProductB?> {
            return arrayOfNulls(size)
        }
    }
}
