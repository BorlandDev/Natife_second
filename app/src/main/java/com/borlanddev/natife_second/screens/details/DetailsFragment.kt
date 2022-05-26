package com.borlanddev.natife_second.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.DetailsFragnentBinding
import com.bumptech.glide.Glide

class DetailsFragment : Fragment(R.layout.details_fragnent) {
    private lateinit var binding: DetailsFragnentBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsFragnentBinding.bind(view)


        detailsViewModel.getUserLiveDAta.observe(viewLifecycleOwner) {
            with(binding) {
                userNameDetails.text = it.name
                userAge.text = getString(R.string.user_age_format, it.age)
                userPhone.text = getString(R.string.user_phone_format, it.phone)
                userEmail.text = getString(R.string.user_email_format, it.email)
                userLocationDetails.text = it.location
            }

            Glide.with(this)
                .load(it.picture)
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .circleCrop()
                .into(binding.userPictureDetails)

        }
        detailsViewModel.getUser(args.id)

    }
}