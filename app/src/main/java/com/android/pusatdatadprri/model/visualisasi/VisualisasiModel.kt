package com.android.pusatdatadprri.model.visualisasi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VisualisasiModel(
    var gambar: Int,
    var nama: String
):Parcelable