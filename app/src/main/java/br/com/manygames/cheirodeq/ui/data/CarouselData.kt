package br.com.manygames.cheirodeq.ui.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class CarouselData(private val id: Long,
                   private val colors: List<ColorData>) : Parcelable
