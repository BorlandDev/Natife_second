package com.borlanddev.natife_second.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.DetailsFragnentBinding

class DetailsFragment : Fragment(R.layout.details_fragnent) {
    private lateinit var binding: DetailsFragnentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsFragnentBinding.bind(view)

    }
}