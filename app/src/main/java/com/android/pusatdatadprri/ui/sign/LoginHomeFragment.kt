package com.android.pusatdatadprri.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.pusatdatadprri.R
import com.android.pusatdatadprri.databinding.FragmentLoginHomeBinding


class LoginHomeFragment : Fragment() {

    lateinit var binding: FragmentLoginHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_login_home_to_nav_login)
        )
    }
}