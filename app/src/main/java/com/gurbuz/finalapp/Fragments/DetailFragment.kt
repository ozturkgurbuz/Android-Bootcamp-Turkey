package com.gurbuz.finalapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gurbuz.finalapp.Activity.MainActivity
import com.gurbuz.finalapp.ViewModel.PaymentViewModel
import com.gurbuz.finalapp.R
import com.gurbuz.finalapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var mPaymentViewModel: PaymentViewModel
    private lateinit var binding : FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater,container,false)
        val toolBarDetail : Toolbar = binding.toolBarDetail
        (activity as MainActivity).setSupportActionBar(toolBarDetail)
        (activity as MainActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        binding.DetailsImageView.setImageResource(args.PaymentData.vektor)
        binding.DetailsAmount.setText(args.PaymentData.amount.toInt().toString() + args.PaymentData.amountType)
        binding.DetailsSection.setText(args.PaymentData.section)
        mPaymentViewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)

        binding.DetailsSilButton.setOnClickListener {
            mPaymentViewModel.deletePayment(args.PaymentData)
            Toast.makeText(requireContext()," ${args.PaymentData.section} Başarıyla Silindi", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }


        return binding.root
    }

}