package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemKategoriBinding
import com.android.pusatdatadprri.databinding.ItemNewsBinding
import com.android.pusatdatadprri.databinding.ItemStatistikBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.model.news.NewsModel
import com.android.pusatdatadprri.model.statistik.StatistikModel
import com.bumptech.glide.Glide

class StatistikAdapter(
    var data:List<StatistikModel>
):RecyclerView.Adapter<StatistikAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemStatistikBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : StatistikModel){
            with(binding){

                binding.txtNamaStatistik.text = item.namaStatistik
                binding.txtTotalStatistik.text = item.totalStatistik

                Glide.with(itemView.context)
                    .load(item.gambarStatistik)
                    .into(binding.imgStatistik)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemStatistikBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KategoriViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }
}