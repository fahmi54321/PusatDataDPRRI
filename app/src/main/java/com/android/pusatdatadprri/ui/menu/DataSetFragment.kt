package com.android.pusatdatadprri.ui.menu

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentDataSetBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.Function5

class DataSetFragment : Fragment() {

    //nama,nohp,email,judul,deskripsi

    lateinit var binding : FragmentDataSetBinding
    lateinit var namaStream : Observable<Boolean>
    lateinit var noHpStream : Observable<Boolean>
    lateinit var emailStream : Observable<Boolean>
    lateinit var judulStream : Observable<Boolean>
    lateinit var deskripsiStream : Observable<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding = FragmentDataSetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStreamInput()
        implementasiDataStream()
    }

    private fun dataStreamInput(){
        namaStream = RxTextView.textChanges(binding.edtNama)
            .skipInitialValue()
            .map {
                it.isNullOrEmpty()
            }

        namaStream.subscribe {
            showNamaAlert(it)
        }

        noHpStream = RxTextView.textChanges(binding.edtNoTelp)
            .skipInitialValue()
            .map {
                it.isNullOrEmpty()
            }

        noHpStream.subscribe {
            showNoHpAlert(it)
        }

        emailStream = RxTextView.textChanges(binding.edtEmail)
            .skipInitialValue()
            .map {
                !Patterns.EMAIL_ADDRESS.matcher(it).matches() || it.isNullOrEmpty()
            }

        emailStream.subscribe {
            showEmailAlert(it)
        }

        judulStream = RxTextView.textChanges(binding.edtJudul)
            .skipInitialValue()
            .map {
                it.isNullOrEmpty()
            }

        judulStream.subscribe {
            showJudulAlert(it)
        }

        deskripsiStream = RxTextView.textChanges(binding.edtDeskripsi)
            .skipInitialValue()
            .map {
                it.isNullOrEmpty()
            }

        deskripsiStream.subscribe {
            showDeskripsiAlert(it)
        }
    }

    private fun implementasiDataStream(){
        val invalidDataStream = Observable.combineLatest(
            namaStream,
            noHpStream,
            emailStream,
            judulStream,
            deskripsiStream,
            Function5 { namaInvalid: Boolean,
                        noHpInvalid: Boolean,
                        emailInvalid: Boolean,
                        judulInvalid: Boolean,
                        deskripsiInvalid: Boolean ->
                !namaInvalid && !noHpInvalid && !emailInvalid && !judulInvalid && !deskripsiInvalid
            }
        )

        invalidDataStream.subscribe({
            if (it){
                binding.btnKirim.isEnabled = true
            }else{
                binding.btnKirim.isEnabled = false
            }
        })
    }

    private fun showDeskripsiAlert(it: Boolean) {
        binding.edtDeskripsi.error = if (it) "Deskripsi kosong" else null
    }

    private fun showEmailAlert(it: Boolean) {
        binding.edtEmail.error = if (it) "Email kosong atau tidak benar" else null
    }

    private fun showJudulAlert(it: Boolean) {
        binding.edtJudul.error = if (it) "Judul kosong" else null
    }

    private fun showNamaAlert(it: Boolean) {
        binding.edtNama.error = if (it) "Nama kosong" else null
    }

    private fun showNoHpAlert(it: Boolean) {
        binding.edtNoTelp.error = if (it) "No Telp kosong" else null
    }
}