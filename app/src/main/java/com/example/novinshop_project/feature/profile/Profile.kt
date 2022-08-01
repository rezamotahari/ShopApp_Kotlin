package com.example.novinshop_project.feature.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.address.Address_Activity
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.Login_Activity
import com.example.novinshop_project.feature.order.Order_Activity
import com.example.novinshop_project.feature.profile.chargeWallet.Charge_Wallet
import com.example.novinshop_project.feature.profile.infoUser.Info_Activity
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.UtilsSocial
import kotlinx.android.synthetic.main.empty_login.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class Profile : Fragment() {

    val authViewMolde: AuthViewMolde by viewModel()
    val imageLoadingServices: ImageLoadingServices by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        rltv_wallet.setOnClickListener {
            startActivity(Intent(context, Charge_Wallet::class.java))

        }
        rltv_info.setOnClickListener {

            startActivity(Intent(context, Info_Activity::class.java))
        }
        rltv_favourite.setOnClickListener {
            findNavController().navigate(R.id.action_profile2_to_favourit)

        }
        rltv_adress.setOnClickListener {

            startActivity(Intent(context, Address_Activity::class.java))
        }
        lnr_cloud.setOnClickListener { startActivity(Intent(context, Order_Activity::class.java)) }
        imageLoadingServices.loadImage(img_profile, "https://s4.uupload.ir/files/ll_ozot.png")
        authViewMolde.checkLoginLiveData.observe(viewLifecycleOwner)
        {
            if (it) {
                lnr_login_empty.visibility = View.GONE
                lnr_profile.visibility = View.VISIBLE
                rltv_profile.visibility = View.GONE


            } else {

                lnr_login_empty.visibility = View.VISIBLE
                lnr_profile.visibility = View.GONE
                rltv_profile.visibility = View.GONE
            }
        }
        btn_intetn_login.setOnClickListener {

            startActivity(Intent(context, Login_Activity::class.java))

        }
        img_instagram.setOnClickListener {

            context?.let {
                UtilsSocial.instagram(it, "novindevelopers")
            }
        }
        img_telegram.setOnClickListener {

            context?.let {
                UtilsSocial.telegram(it, "novindevelopers96")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authViewMolde.checkLogin()
    }
}