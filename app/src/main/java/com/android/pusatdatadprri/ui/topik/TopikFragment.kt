package com.android.pusatdatadprri.ui.topik

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentTopikBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.ui.adapter.TopikAdapter

class TopikFragment : Fragment() {

    //kategori
    var adapterTopik: TopikAdapter?=null
    lateinit var namaKategori:Array<String>
    lateinit var gambarKategori: TypedArray
    var topik = arrayListOf<KategoriModel>()

    lateinit var binding : FragmentTopikBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopikBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isiDataKategori()
        bindDataKategori()
    }

    private fun isiDataKategori() {
        namaKategori = resources.getStringArray(R.array.nama_kategori)
        gambarKategori = resources.obtainTypedArray(R.array.gambar_kategori)

        for (position in namaKategori.indices){
            val data = KategoriModel(
                namaKategori[position],
                gambarKategori.getResourceId(position,-1)
            )
            topik.add(data)
        }
        adapterTopik?.data = topik
    }

    private fun bindDataKategori() {
        adapterTopik = TopikAdapter(topik,object : TopikAdapter.onCLickListener{
            override fun detail(item: KategoriModel) {
                startActivity(Intent(context,TopikDetailsActivity::class.java))
            }

        })
        binding.rvTopik.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = adapterTopik
        }
    }
}