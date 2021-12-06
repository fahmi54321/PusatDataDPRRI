package com.android.pusatdatadprri.ui.menu

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentPublikasiBinding
import com.android.pusatdatadprri.model.kategori.KategoriModel
import com.android.pusatdatadprri.model.publikasi.PublikasiModel
import com.android.pusatdatadprri.ui.adapter.KategoriAdapter
import com.android.pusatdatadprri.ui.adapter.PublikasiAdapter
import com.android.pusatdatadprri.ui.menu.filter.FilterFragment

class PublikasiFragment : Fragment() {


    //publikasi
    var adapterPublikasi: PublikasiAdapter? = null
    lateinit var namaPublikasi: Array<String>
    lateinit var lokasiPublikasi: Array<String>
    lateinit var tipePublikasi: Array<String>
    lateinit var tahunPublikasi: Array<String>
    lateinit var idPublikasi: Array<String>
    var publikasi = arrayListOf<PublikasiModel>()

    lateinit var binding: FragmentPublikasiBinding
    private var filterFragment: FilterFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPublikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initt()

        //isi data
        isiDataPublikasi()

        //bindData
        bindDataPublikasi()

        aksiTombol()
    }

    private fun initt() {
        filterFragment = FilterFragment()
    }

    private fun isiDataPublikasi() {
        namaPublikasi = resources.getStringArray(R.array.nama_publikasi)
        lokasiPublikasi = resources.getStringArray(R.array.lokasi_publikasi)
        tipePublikasi = resources.getStringArray(R.array.tipe_publikasi)
        tahunPublikasi = resources.getStringArray(R.array.tahun_publikasi)
        idPublikasi = resources.getStringArray(R.array.id_publikasi)

        for (position in idPublikasi.indices) {
            val data = PublikasiModel(
                namaPublikasi[position],
                lokasiPublikasi[position],
                tipePublikasi[position],
                tahunPublikasi[position],
            )
            publikasi.add(data)
        }
        adapterPublikasi?.data = publikasi
    }

    private fun bindDataPublikasi() {
        adapterPublikasi = PublikasiAdapter(publikasi)
        binding.rvPublikasi.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterPublikasi
        }
    }

    private fun aksiTombol() {
        binding.imgFilter.setOnClickListener {
            filterFragment?.show(childFragmentManager, filterFragment?.tag)
        }
    }
}