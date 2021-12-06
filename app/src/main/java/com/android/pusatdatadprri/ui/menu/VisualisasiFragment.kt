package com.android.pusatdatadprri.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentVisualisasiBinding

class VisualisasiFragment : Fragment() {

    lateinit var binding: FragmentVisualisasiBinding

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

        val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://public.tableau.com/app/profile/hisyam.al.fattah"))
        startActivity(i)

    }
}