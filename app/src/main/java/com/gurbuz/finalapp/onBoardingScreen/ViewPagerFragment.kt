package com.gurbuz.finalapp.onBoardingScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurbuz.finalapp.databinding.FragmentViewPagerBinding
import com.gurbuz.finalapp.onBoardingScreen.onBoardingScreenFragments.OnBoardingScreenLast
import com.gurbuz.finalapp.onBoardingScreen.onBoardingScreenFragments.OnBoardingScreenOne
import com.gurbuz.finalapp.onBoardingScreen.onBoardingScreenFragments.OnBoardingScreenTwo

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        val fragmentList = arrayListOf<Fragment>(
            OnBoardingScreenOne(),
            OnBoardingScreenTwo(),
            OnBoardingScreenLast()

        )
        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter =  adapter
        return binding.root
    }


}