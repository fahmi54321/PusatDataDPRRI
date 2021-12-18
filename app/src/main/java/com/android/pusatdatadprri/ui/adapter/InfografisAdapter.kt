package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemInfografisBinding
import com.android.pusatdatadprri.databinding.ItemVisualisasiBinding
import com.android.pusatdatadprri.model.infografis.InfografisModel
import com.bumptech.glide.Glide

class InfografisAdapter(
    var data: List<InfografisModel>,
    var onClick: onClickListener
) : RecyclerView.Adapter<InfografisAdapter.InfografisViewHolder>() {
    class InfografisViewHolder(val binding: ItemInfografisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InfografisModel) {
            with(binding) {
                binding.txtInfografis.text = item.nama
                Glide.with(itemView.context)
                    .load(item.gambar)
                    .into(binding.imgInfografis)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfografisViewHolder {
        val view =
            ItemInfografisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfografisViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: InfografisViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick.detail(item)
        }
    }

    interface onClickListener {
        fun detail(item: InfografisModel)
    }
}