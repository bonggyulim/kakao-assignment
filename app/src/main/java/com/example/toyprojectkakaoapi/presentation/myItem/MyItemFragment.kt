package com.example.toyprojectkakaoapi.presentation.myItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toyprojectkakaoapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_item, container, false)
    }
}