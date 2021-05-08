package com.gurbuz.finalapp.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gurbuz.finalapp.Activity.MainActivity
import com.gurbuz.finalapp.Database.Payment
import com.gurbuz.finalapp.ViewModel.PaymentViewModel
import com.gurbuz.finalapp.R
import com.gurbuz.finalapp.databinding.FragmentUploadBinding


class UploadFragment : Fragment() {

    private lateinit var binding: FragmentUploadBinding
    var radioButtonHarcama = R.drawable.ic_outline_maps_home_work_24
    var radioButtonPara = " "
    lateinit var preferences : SharedPreferences
    private lateinit var mPaymentViewModel : PaymentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUploadBinding.inflate(inflater, container, false)
        mPaymentViewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)
        val toolbar: Toolbar = binding.toolBar
        val buttonUploadEkle: Button = binding.buttonUploadEkle

     //   val preferences = SharedPreferencesManager.getSharedPreferences(requireContext())
        preferences = requireActivity().getSharedPreferences("com.gurbuz.finalapp", Context.MODE_PRIVATE)
        binding.radioGroupMoney.setOnCheckedChangeListener { group, checkedId ->
            var radioButton:View = binding.radioGroupMoney.findViewById(checkedId)
            var index = binding.radioGroupMoney.indexOfChild(radioButton)
            when(index){
                0 -> {
                    preferences?.edit()?.putInt("radioButtonPara",0)?.apply()
                    radioButtonPara = "TL"
                }
                1 -> {
                    preferences?.edit()?.putInt("radioButtonPara",1)?.apply()
                    radioButtonPara = "$"
                }
                2 -> {
                    preferences?.edit()?.putInt("radioButtonPara",2)?.apply()
                    radioButtonPara = "£"
                }
                3 -> {
                    preferences?.edit()?.putInt("radioButtonPara",3)?.apply()
                    radioButtonPara = "€"
                }



            }

        }

        binding.radioGroupHarcama.setOnCheckedChangeListener { group, checkedId ->
            var radioButton:View = binding.radioGroupHarcama.findViewById(checkedId)
            var index = binding.radioGroupHarcama.indexOfChild(radioButton)
            when(index){
                0 -> radioButtonHarcama = R.drawable.ic_outline_article_24
                1 -> radioButtonHarcama = R.drawable.ic_outline_maps_home_work_24
                2 -> radioButtonHarcama = R.drawable.ic_outline_shopping_bag_24
            }
        }

        (activity as MainActivity).setSupportActionBar(toolbar)
        buttonUploadEkle.setOnClickListener {
            val vektor = radioButtonHarcama
            val section = binding.TextInputeditTextAciklama.text.toString()
            val amount = binding.TextInputeditTextHarcama.text
            val amountType = radioButtonPara



            if(inputCheck(section, amount!!)){
                val payment = Payment(0,vektor,section,amount.toString().toDouble(),amountType)
                mPaymentViewModel.addPayment(payment)
                //val id : Int = mPaymentViewModel.addPayment(payment)
                Toast.makeText(requireContext(),"Başarıyla Kaydedildi",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_uploadFragment_to_mainFragment)
            }else{
                Toast.makeText(requireContext(),"Lütfen Boş Alanları Doldurun",Toast.LENGTH_LONG).show()
            }
        }
        return binding.root

    }

    private fun inputCheck(section: String, amount: Editable):Boolean{
        return !(TextUtils.isEmpty(section)&& amount.isEmpty())
    }


}