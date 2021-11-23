package com.android.pusatdatadprri.ui.notifications.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentNotificationsBinding
import com.android.pusatdatadprri.ui.notifications.adapter.NotifikasiAdapter
import com.android.pusatdatadprri.ui.notifications.model.NotifikasiModel

class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding

    companion object {
        const val DATA_NOTIF = "data_notif"
    }

    //notifikasi
    var adapterNotifikasi: NotifikasiAdapter? = null
    lateinit var titleNotifikasi: Array<String>
    lateinit var contentNotifikasi: Array<String>
    lateinit var hariNotifikasi: Array<String>
    lateinit var tglNotifikasi: Array<String>
    lateinit var contentLongNotifikasi: Array<String>
    lateinit var contentPenutupNotifikasi: Array<String>
    var notifikasi = arrayListOf<NotifikasiModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //isi data
        isiDataNotifikasi()

        //bindData
        bindDataNotifikasi()
    }

    private fun isiDataNotifikasi() {
        titleNotifikasi = resources.getStringArray(R.array.item_title_notifikasi)
        contentNotifikasi = resources.getStringArray(R.array.item_content_notifikasi)
        hariNotifikasi = resources.getStringArray(R.array.item_hari_notifikasi)
        tglNotifikasi = resources.getStringArray(R.array.item_tanggal_notifikasi)
        contentLongNotifikasi = resources.getStringArray(R.array.item_content_long_notifikasi)
        contentPenutupNotifikasi = resources.getStringArray(R.array.item_content_penutup_notifikasi)

        for (position in titleNotifikasi.indices) {
            val data = NotifikasiModel(
                titleNotifikasi[position],
                contentNotifikasi[position],
                hariNotifikasi[position],
                tglNotifikasi[position],
                contentLongNotifikasi[position],
                contentPenutupNotifikasi[position]
            )
            notifikasi.add(data)
        }
        adapterNotifikasi?.data = notifikasi
    }

    private fun bindDataNotifikasi() {
        adapterNotifikasi =
            NotifikasiAdapter(notifikasi, object : NotifikasiAdapter.onClicklistener {
                override fun detail(item: NotifikasiModel) {

//                val toNotifikasiContentFragment = NotificationsFragmentDirections.actionNavigationNotificationsToNotifikasiContentFragment()
//                toNotifikasiContentFragment.arguments.putParcelable(DATA_NOTIF,item)
//                view?.findNavController()?.navigate(toNotifikasiContentFragment)

                    val bundle = Bundle()
                    bundle.putParcelable(DATA_NOTIF,item)
                    view?.findNavController()?.navigate(R.id.action_navigation_notifications_to_notifikasiContentFragment,bundle)
                }

            })
        binding.rvNotifikasi.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterNotifikasi
        }
    }
}