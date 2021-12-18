package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemTopikBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.bumptech.glide.Glide

class TopikAdapter(
    var data:List<KategoriModel>,
    var onClick:onCLickListener
):RecyclerView.Adapter<TopikAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemTopikBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : KategoriModel){
            with(binding){
                binding.txtTopik.text = item.nama_kategori
                Glide.with(itemView.context)
                    .load(item.gambar)
                    .into(binding.imgTopik)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemTopikBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KategoriViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick.detail(item)
        }
    }

    interface onCLickListener{
        fun detail(item: KategoriModel)
    }
}