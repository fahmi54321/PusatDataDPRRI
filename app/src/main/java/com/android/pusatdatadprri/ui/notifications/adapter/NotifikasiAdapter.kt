package com.android.pusatdatadprri.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemKategoriBinding
import com.android.pusatdatadprri.databinding.ItemNotifikasiBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.ui.notifications.model.NotifikasiModel
import com.bumptech.glide.Glide

class NotifikasiAdapter(
    var data:List<NotifikasiModel>,
    var onClick : onClicklistener
):RecyclerView.Adapter<NotifikasiAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemNotifikasiBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : NotifikasiModel){
            with(binding){
                binding.txtTitle.text = item.title
                binding.txtContent.text = item.content
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemNotifikasiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

    interface onClicklistener{
        fun detail(item: NotifikasiModel)
    }
}