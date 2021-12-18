package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemVisualisasiBinding
import com.android.pusatdatadprri.model.visualisasi.VisualisasiModel
import com.bumptech.glide.Glide

class VisualisasiAdapter(
    var data: List<VisualisasiModel>,
    var onClick:onClickListener
) : RecyclerView.Adapter<VisualisasiAdapter.VisualisasiViewHolder>() {
    class VisualisasiViewHolder(val binding: ItemVisualisasiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VisualisasiModel) {
            with(binding) {
                binding.txtVisualisasi.text = item.nama
                Glide.with(itemView.context)
                    .load(item.gambar)
                    .into(binding.imgVisualisasi)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisualisasiViewHolder {
        val view =
            ItemVisualisasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisualisasiViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: VisualisasiViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick.detail(item)
        }
    }

    interface onClickListener {
        fun detail(item: VisualisasiModel)
    }
}