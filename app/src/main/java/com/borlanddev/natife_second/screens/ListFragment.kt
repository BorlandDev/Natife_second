package com.borlanddev.natife_second.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.ListFragmentBinding

class ListFragment : Fragment(R.layout.list_fragment) {
    private lateinit var binding: ListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)



    }
}