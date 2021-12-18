package com.android.pusatdatadprri.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.ActivityDetailVisualisasiBinding
import com.android.pusatdatadprri.model.visualisasi.VisualisasiModel
import com.bumptech.glide.Glide

class DetailVisualisasiActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailVisualisasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVisualisasiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val getData = intent.getParcelableExtra<VisualisasiModel>("item")
        if (getData != null) {
            Glide.with(this)
                .load(getData.gambar)
                .into(binding.imgVisualisasi)

            binding.txtNamaVisualisasi.text = getData.nama
        }

    }
}