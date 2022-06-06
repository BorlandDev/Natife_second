package com.borlanddev.natife_second.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.base.BaseFragment
import com.borlanddev.natife_second.databinding.DetailsFragnentBinding
import com.borlanddev.natife_second.helpers.MainRepository
import com.bumptech.glide.Glide

class DetailsFragment : BaseFragment<DetailsVM, DetailsFragnentBinding>() {

    override val bindingInflation =
        { inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean ->
            DetailsFragnentBinding.inflate(inflater, container, attachToParent)
        }
    private val args: DetailsFragmentArgs by navArgs()
    override val viewModel: DetailsVM by viewModels {
        DetailsVMFactory(
            args.id,
            MainRepository.getInstance()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserLiveDAta.subscribe {

            binding.apply {
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
        viewModel.getUser()
    }
}
