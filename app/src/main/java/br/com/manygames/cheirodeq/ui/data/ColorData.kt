package br.com.manygames.cheirodeq.ui.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ColorData(private val id: Long,
                private val colorInt: Int,
                private val playAnimation: Boolean) : Parcelable{

}
