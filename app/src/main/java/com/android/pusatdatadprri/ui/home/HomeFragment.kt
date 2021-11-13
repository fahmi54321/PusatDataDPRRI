package com.android.pusatdatadprri.ui.home

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentHomeBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.model.news.NewsModel
import com.android.pusatdatadprri.ui.adapter.KategoriAdapter
import com.android.pusatdatadprri.ui.adapter.NewsAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //kategori
    var adapterKategori:KategoriAdapter?=null
    lateinit var namaKategori:Array<String>
    lateinit var gambarKategori: TypedArray
    var kategori = arrayListOf<KategoriModel>()

    //news
    var adapterNews: NewsAdapter?=null
    lateinit var gambarNews: TypedArray
    lateinit var idNews:Array<String>
    var news = arrayListOf<NewsModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //isi data
        isiDataKategori()
        isiDataNews()

        //bindData
        bindDataKategori()
        bindDataNews()


    }

    private fun isiDataKategori() {
        namaKategori = resources.getStringArray(R.array.nama_kategori)
        gambarKategori = resources.obtainTypedArray(R.array.gambar_kategori)

        for (position in namaKategori.indices){
            val data = KategoriModel(
                namaKategori[position],
                gambarKategori.getResourceId(position,-1)
            )
            kategori.add(data)
        }
        adapterKategori?.data = kategori
    }
    private fun isiDataNews() {
        gambarNews = resources.obtainTypedArray(R.array.gambar_news)
        idNews = resources.getStringArray(R.array.id_news)
        for (position in idNews.indices){
            val data = NewsModel(
                gambarNews.getResourceId(position,-1)
            )
            news.add(data)
        }
        adapterNews?.data = news
    }

    private fun bindDataKategori() {
        adapterKategori = KategoriAdapter(kategori)
        binding.rvKategori.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterKategori
        }
    }
    private fun bindDataNews() {
        adapterNews = NewsAdapter(news)
        var gridLayoutManager: GridLayoutManager? = null
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.apply {
            layoutManager = gridLayoutManager
            adapter = adapterNews
        }
    }
}