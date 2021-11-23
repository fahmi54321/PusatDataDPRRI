package com.android.pusatdatadprri.ui.notifications.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentNotificationsBinding
import com.android.pusatdatadprri.databinding.FragmentNotifikasiContentBinding
import com.android.pusatdatadprri.ui.notifications.model.NotifikasiModel
import com.android.pusatdatadprri.ui.notifications.view.NotificationsFragment.Companion.DATA_NOTIF


class NotifikasiContentFragment : Fragment() {


    lateinit var binding: FragmentNotifikasiContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotifikasiContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataNotifikasi()
    }

    private fun getDataNotifikasi() {

        var getData = arguments?.getParcelable<NotifikasiModel>(DATA_NOTIF)

        if (getData != null) {
            bindData(getData)
        } else {
            Toast.makeText(context, "Kosong", Toast.LENGTH_SHORT).show()
        }

    }

    private fun bindData(data: NotifikasiModel) {
        binding.txtTitle.text = data.title
        binding.txtPembuka.text = data.content
        binding.txtHari.text = data.hari
        binding.txtTgl.text = data.tanggal
        binding.txtContent.text = data.content_long
        binding.txtContentPenutup.text = data.content_penutup
    }
}