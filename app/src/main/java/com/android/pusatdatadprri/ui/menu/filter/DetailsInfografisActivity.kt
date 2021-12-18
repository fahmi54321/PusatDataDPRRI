package com.android.pusatdatadprri.ui.menu.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.ActivityDetailsInfografisBinding
import com.android.pusatdatadprri.model.infografis.InfografisModel
import com.bumptech.glide.Glide

class DetailsInfografisActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsInfografisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsInfografisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getData = intent.getParcelableExtra<InfografisModel>("item")
        if (getData != null) {
            binding.txtInfografis.text = getData.nama
            Glide.with(this)
                .load(getData.gambar)
                .into(binding.imgInfografis)
        }

    }
}