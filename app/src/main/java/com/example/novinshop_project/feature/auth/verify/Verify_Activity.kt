package com.example.novinshop_project.feature.auth.verify

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.Toast
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.utils.SMSReceiver
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.material.button.MaterialButton
import com.mukesh.OtpView
import kotlinx.android.synthetic.main.activity_verify.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Verify_Activity : AppCompatActivity(), SMSReceiver.OTPReceiveListener {
    var phone: String? = null
    var family: String? = null
    val authViewMolde : AuthViewMolde by viewModel()

    private var smsReceiver: SMSReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        authViewMolde.register(intent.getStringExtra("phone",),intent.getStringExtra("name"))
        authViewMolde.registerLiveData.observe(this)
        {
            it.code
        }

        startSMSListener()
    }

    private fun startSMSListener() {
        try {
            smsReceiver = SMSReceiver()
            smsReceiver!!.setOTPListener(this)
            val intentFilter = IntentFilter()
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
            this.registerReceiver(smsReceiver, intentFilter)
            val client = SmsRetriever.getClient(this)
            val task = client.startSmsRetriever()
            task.addOnSuccessListener {
                // API successfully started
                //  showToast("successfully")


            }
            task.addOnFailureListener {
                // Fail to start API
                //   showToast("Fail to start")

            }
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    override fun onOTPReceived(otp: String?) {
        //  showToast("OTP Received: $otp")
        val codes = otp!!.replace("???? ?????????? ?????? ", "")
        val show = codes!!.replace("?????? ???????????????? ????????????????", "")
        val ss = show.replace("x8Vn5A7rgCP", "")

        edt_code.setText(ss.toString().trim())
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            Toast.makeText(applicationContext, "?????? ?????? ???? ???????????? ?????????? ????", Toast.LENGTH_SHORT)
                .show()
            finish()

        }, 1000)

        // showToast(ss)
        //    edt
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }

    override fun onOTPTimeOut() {
        showToast("OTP Time out")
    }

    override fun onOTPReceivedError(error: String?) {
        showToast(error.toString())
    }


    override fun onDestroy() {
        super.onDestroy()
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
        }
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}