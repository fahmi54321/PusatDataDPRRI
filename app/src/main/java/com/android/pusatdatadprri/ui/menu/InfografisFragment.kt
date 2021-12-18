package com.android.pusatdatadprri.ui.menu

import android.content.Intent
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
import com.android.pusatdatadprri.model.infografis.InfografisModel
import com.android.pusatdatadprri.ui.adapter.InfografisAdapter
import com.android.pusatdatadprri.ui.menu.filter.DetailsInfografisActivity

class InfografisFragment : Fragment() {

    //infografis
    var adapterInfografis: InfografisAdapter? = null
    lateinit var gambarInfografis: TypedArray
    lateinit var namaInfografis: Array<String>
    var infografis = arrayListOf<InfografisModel>()

    lateinit var binding: FragmentInfografisBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfografisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isiDataNews()
        bindDataInfografis()
    }

    private fun isiDataNews() {
        gambarInfografis = resources.obtainTypedArray(R.array.gambar_infografis)
        namaInfografis = resources.getStringArray(R.array.name_infografis)
        for (position in namaInfografis.indices) {
            val data = InfografisModel(
                gambarInfografis.getResourceId(position, -1),
                namaInfografis[position]
            )
            infografis.add(data)
        }
        adapterInfografis?.data = infografis
    }

    private fun bindDataInfografis() {
        adapterInfografis =
            InfografisAdapter(infografis, object : InfografisAdapter.onClickListener {
                override fun detail(item: InfografisModel) {
                    val intent = Intent(context, DetailsInfografisActivity::class.java)
                    intent.putExtra("item", item)
                    startActivity(intent)
                }

            })

        var gridLayoutManager: GridLayoutManager? = null
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        binding.rvNews.apply {
            layoutManager = gridLayoutManager
            adapter = adapterInfografis
        }
    }
}