package com.android.pusatdatadprri.ui.menu

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentInfografisBinding
import com.android.pusatdatadprri.model.news.NewsModel
import com.android.pusatdatadprri.ui.adapter.NewsAdapter

class InfografisFragment : Fragment() {

    //news
    var adapterNews: NewsAdapter?=null
    lateinit var gambarNews: TypedArray
    lateinit var idNews:Array<String>
    var news = arrayListOf<NewsModel>()

    lateinit var binding : FragmentInfografisBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfografisBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isiDataNews()
        bindDataNews()
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