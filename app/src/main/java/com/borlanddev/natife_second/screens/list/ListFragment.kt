package com.borlanddev.natife_second.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.adapter.UserAdapter
import com.borlanddev.natife_second.base.BaseFragment
import com.borlanddev.natife_second.databinding.ListFragmentBinding
import com.borlanddev.natife_second.helpers.MainRepository

class ListFragment : BaseFragment<ListVM, ListFragmentBinding>() {

    override val bindingInflation =
        { inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean ->
            ListFragmentBinding.inflate(inflater, container, attachToParent)
        }

    //@Inject
    //lateinit var mainRepository : MainRepository

    override val viewModel: ListVM by viewModels { ListVMFactory(MainRepository.getInstance()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // requireContext().appComponent.inject(this)

        val userAdapter = UserAdapter(onItemClick = {
            val direction = ListFragmentDirections.actionListFragmentToDetailsFragment(it.id)
            findNavController().navigate(
                direction,
                navOptions {
                    anim {
                        enter = R.anim.enter
                        exit = R.anim.exit
                        popEnter = R.anim.pop_enter
                        popExit = R.anim.pop_exit
                    }
                })
        }, onPageEndReached = {
            viewModel.getUsers()
        })

        viewModel.userListLiveData.subscribe {
            userAdapter.submitList(it)
        }

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = userAdapter
        }
    }
}



