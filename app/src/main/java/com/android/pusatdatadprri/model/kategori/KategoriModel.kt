package com.android.pusatdatadprri.model.kategori

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KategoriModel(
    var nama_kategori: String,
    var gambar: Int
):Parcelable