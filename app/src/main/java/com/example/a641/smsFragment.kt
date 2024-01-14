package com.example.a641

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.fragment.findNavController
import com.example.a641.databinding.FragmentSmsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [smsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class smsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentSmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentSmsBinding.inflate(inflater,container,false)

        var bundle = arguments?.getSerializable("key") as Contact

        binding.contactName.text = bundle.phone_name
        binding.contactNumber.text = bundle.phone_number
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.btnSend.setOnClickListener{

            try {

                // on below line we are initializing sms manager.
                //as after android 10 the getDefault function no longer works
                //so we have to check that if our android version is greater
                //than or equal toandroid version 6.0 i.e SDK 23
                val smsManager:SmsManager
                if (Build.VERSION.SDK_INT>=23) {
                    //if SDK is greater that or equal to 23 then
                    //this is how we will initialize the SmsManager
                    smsManager = binding.root.context.getSystemService(SmsManager::class.java)
                }
                else{
                    //if user's SDK is less than 23 then
                    //SmsManager will be initialized like this
                    smsManager = SmsManager.getDefault()
                }

                // on below line we are sending text message.
                smsManager.sendTextMessage(bundle.phone_number, null, binding.smsTxt.text.toString(), null, null)

                // on below line we are displaying a toast message for message send,
                Toast.makeText(binding.root.context, "XABAR YUBORILDI", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            } catch (e: Exception) {

                // on catch block we are displaying toast message for error.
                Toast.makeText(binding.root.context, "Iltimos, barcha ma'lumotlarni kiriting.."+e.message.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment smsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            smsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}