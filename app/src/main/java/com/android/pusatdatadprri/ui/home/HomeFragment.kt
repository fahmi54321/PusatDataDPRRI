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
import com.android.pusatdatadprri.model.statistik.StatistikModel
import com.android.pusatdatadprri.ui.adapter.KategoriAdapter
import com.android.pusatdatadprri.ui.adapter.NewsAdapter
import com.android.pusatdatadprri.ui.adapter.StatistikAdapter
import com.android.pusatdatadprri.ui.home.adapter.CalendarAdapter
import com.android.pusatdatadprri.ui.home.model.CalendarModel
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

    //statistik
    private var adapterStatistik: StatistikAdapter?=null
    lateinit var idStatistik : Array<String>
    lateinit var namaStatistik : Array<String>
    lateinit var gambarStatistik : TypedArray
    lateinit var totalStatistik : Array<String>
    private var statistik = arrayListOf<StatistikModel>()

    //calender
    private val DAYS_COUNT = 42
    private val calendarList = ArrayList<CalendarModel>()
    private val calendar = Calendar.getInstance()
    private var tahun : Int = -1
    private var monthOfYear : Int = -1
    private var adapter : CalendarAdapter = CalendarAdapter(calendarList)

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
        isiDataStatistik()

        //bindData
        bindDataKategori()
        bindDataNews()
        bindDataStatistik()

        loadCalendar()
        aksiTombol()
        bindCalender()

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

    private fun isiDataStatistik(){
        gambarStatistik = resources.obtainTypedArray(R.array.gambar_statistik)
        namaStatistik = resources.getStringArray(R.array.nama_statistik)
        totalStatistik = resources.getStringArray(R.array.total_statistik)
        idStatistik = resources.getStringArray(R.array.id_statistik)
        for(position in idStatistik.indices){
            val data = StatistikModel(
                totalStatistik[position],
                namaStatistik[position],
                gambarStatistik.getResourceId(position,-1)
            )
            statistik.add(data)
        }

        adapterStatistik?.data = statistik
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
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterNews
        }
    }

    private fun bindDataStatistik(){

        var gridLayoutManager: GridLayoutManager? = null
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        adapterStatistik = StatistikAdapter(statistik)
        binding.rvStatistik.apply {
            layoutManager = gridLayoutManager
            adapter = adapterStatistik
        }
    }

    private fun loadCalendar() {
        //ubah val ke var
        val cells = ArrayList<CalendarModel>()         // inisialisasi variabel untuk setiap tanggal kalender
        if (tahun != -1 && monthOfYear != -1 ){     // pengecekan bila varuiabel tahun dan monthOfYear kosong (-1 hanya pengecoh)
            //ubah obyek kalender ke tahun dan bulan yang diterima
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.YEAR, tahun)
        } else {
            // set variabel tahun dan monthOfYear ke tahun dan bulan sekarang
            tahun = calendar.get(Calendar.YEAR)
            monthOfYear = calendar.get(Calendar.MONTH)
        }
        var sdf = SimpleDateFormat("MMMM,yyyy", Locale("in", "ID"))  // obyek untuk parse bulan dan tahun
        val dateToday = sdf.format(calendar.time).split(",") //format obyek calendar lalu split berdasarkan ,
        binding.month.text = dateToday[0] //settext bulan ke textview month
        binding.year.text = dateToday[1] //settext bulan ke textview year

        //calendarToday
        val calendarCompare : Calendar = Calendar.getInstance() //instansiasi obyek calendar pembanding

        calendarCompare.set(Calendar.MONTH, monthOfYear) //set bulan pada calendar pembanding ke monthOfYear
        calendarCompare.set(Calendar.YEAR, tahun) //set tahun pada calendar pembanding ke tahun


        // memnentukan kapan tanggal dimulai pada bulan
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1

        // pindah calendar ke awal minggu
        calendar.add(Calendar.DAY_OF_MONTH,-monthBeginningCell)

        //obyek untuk parse tanggal
        sdf = SimpleDateFormat("dd-MM-yyyy", Locale("in", "ID"))

        // isi tanggal
        while (cells.size < DAYS_COUNT) {
            if(sdf.format(calendar.time).equals("22-11-2021")){
                cells.add(CalendarModel(
                    calendar.get(Calendar.DATE),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR),
                    calendarCompare,
                    "hijau"
                ))
            }else{
                cells.add(CalendarModel(
                    calendar.get(Calendar.DATE),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR),
                    calendarCompare,
                    null
                ))
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendarList.clear()
        calendarList.addAll(cells)
        adapter.notifyDataSetChanged()
    }

    private fun aksiTombol() {
        binding.month.setOnClickListener {
            showMonthYearPicker()
        }
        binding.year.setOnClickListener {
            showMonthYearPicker()
        }
    }

    private fun showMonthYearPicker(){
        val dialogFragment  = MonthYearPickerDialogFragment.getInstance(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
        dialogFragment.show(childFragmentManager, null)

        dialogFragment.setOnDateSetListener { year, month ->
            tahun = year
            monthOfYear = month
            loadCalendar()
        }
    }

    private fun bindCalender() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 7)
        binding.recyclerView.adapter = adapter
    }
}