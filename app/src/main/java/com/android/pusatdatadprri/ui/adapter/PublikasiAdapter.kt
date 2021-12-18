package com.android.pusatdatadprri.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pusatdatadprri.databinding.ItemPublikasiBinding
import com.android.pusatdatadprri.model.publikasi.PublikasiModel

class PublikasiAdapter(
    var data:List<PublikasiModel>
):RecyclerView.Adapter<PublikasiAdapter.KategoriViewHolder>() {
    class KategoriViewHolder(val binding:ItemPublikasiBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item : PublikasiModel){
            with(binding){

                binding.txtNamaPublikasi.text = item.namaPublikasi
                binding.txtLokasiPublikasi.text = item.lokasiPublikasi
                binding.txtTipePublikasi.text = "TIPE: ${item.tipePublikasi}"
                binding.txtTahunPublikasi.text = "TAHUN: ${item.tahunPublikasi}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = ItemPublikasiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KategoriViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }
}