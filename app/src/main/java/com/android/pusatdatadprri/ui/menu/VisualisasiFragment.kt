package com.android.pusatdatadprri.ui.menu

import android.content.Intent
import android.content.res.TypedArray
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentVisualisasiBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.model.visualisasi.VisualisasiModel
import com.android.pusatdatadprri.ui.adapter.KategoriAdapter
import com.android.pusatdatadprri.ui.adapter.VisualisasiAdapter

class VisualisasiFragment : Fragment() {

    lateinit var binding: FragmentVisualisasiBinding

    //visualisasi
    var adapterVisualisasi: VisualisasiAdapter? = null
    lateinit var namaVisualiasi: Array<String>
    lateinit var gambarVisualisasi: TypedArray
    var visualisasi = arrayListOf<VisualisasiModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVisualisasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //isi data
        isiDataVisualisasi()

        //bindData
        bindDataVisualisasu()

    }

    private fun isiDataVisualisasi() {
        namaVisualiasi = resources.getStringArray(R.array.txt_visualiasi)
        gambarVisualisasi = resources.obtainTypedArray(R.array.gambar_visualisasi)

        for (position in namaVisualiasi.indices) {
            val data = VisualisasiModel(
                gambarVisualisasi.getResourceId(position, -1),
                namaVisualiasi[position]
            )
            visualisasi.add(data)
        }
        adapterVisualisasi?.data = visualisasi
    }

    private fun bindDataVisualisasu() {
        adapterVisualisasi = VisualisasiAdapter(visualisasi,object : VisualisasiAdapter.onClickListener{
            override fun detail(item: VisualisasiModel) {
                val intent = Intent(context,DetailVisualisasiActivity::class.java)
                intent.putExtra("item",item)
                startActivity(intent)
            }

        })
        var gridLayoutManager: GridLayoutManager? = null
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvTopik.apply {
            layoutManager = gridLayoutManager
            adapter = adapterVisualisasi
        }
    }
}