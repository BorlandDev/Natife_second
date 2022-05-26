package com.borlanddev.natife_second.screens.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.adapter.UserAdapter
import com.borlanddev.natife_second.databinding.ListFragmentBinding

class ListFragment : Fragment(R.layout.list_fragment) {
    private lateinit var binding: ListFragmentBinding
    private val listViewModel by viewModels<ListVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)

        val userAdapter = UserAdapter {
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
        }

        // Make extension to LiveData
        listViewModel.userListLiveData.observe(
            viewLifecycleOwner
        ) { userAdapter.setUserList(it) }

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = userAdapter
        }
    }
}


