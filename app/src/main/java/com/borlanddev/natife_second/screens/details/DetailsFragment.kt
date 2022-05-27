package com.borlanddev.natife_second.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.databinding.DetailsFragnentBinding
import com.bumptech.glide.Glide

class DetailsFragment : Fragment(R.layout.details_fragnent) {
    private var binding: DetailsFragnentBinding? = null
    private val args: DetailsFragmentArgs by navArgs()
    private val detailsVM: DetailsVM by viewModels {
        DetailsVMFactory(
            UserDBRepository.get(),
            args.id
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsFragnentBinding.bind(view)

        detailsVM.getUserLiveDAta.observe(viewLifecycleOwner) {

            binding?.apply {
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
                .into(binding!!.userPictureDetails)
        }

        detailsVM.getUser()
    }
}
