package com.android.pusatdatadprri.ui.notifications.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotifikasiModel(
    var title: String,
    var content: String,
    var hari: String,
    var tanggal: String,
    var content_long: String,
    var content_penutup: String
):Parcelable