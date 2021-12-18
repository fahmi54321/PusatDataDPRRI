package com.android.pusatdatadprri.model.infografis

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfografisModel(
    var gambar: Int,
    var nama: String
):Parcelable