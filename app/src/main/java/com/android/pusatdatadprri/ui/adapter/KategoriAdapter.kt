package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemKategoriBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.bumptech.glide.Glide

class KategoriAdapter(
    var data:List<KategoriModel>
):RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemKategoriBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : KategoriModel){
            with(binding){
                binding.txtKategori.text = item.nama_kategori
                Glide.with(itemView.context)
                    .load(item.gambar)
                    .into(binding.imgKategori)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KategoriViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }
}