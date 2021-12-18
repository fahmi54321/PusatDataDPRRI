package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemNewsBinding
import com.android.pusatdatadprri.model.news.NewsModel
import com.bumptech.glide.Glide

class NewsAdapter(
    var data:List<NewsModel>
):RecyclerView.Adapter<NewsAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : NewsModel){
            with(binding){
                Glide.with(itemView.context)
                    .load(item.gambar)
                    .into(binding.imgNews)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KategoriViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }
}