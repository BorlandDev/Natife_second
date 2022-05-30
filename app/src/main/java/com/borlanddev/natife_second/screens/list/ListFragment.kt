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
    private var binding: ListFragmentBinding? = null
    private val listVM: ListVM by viewModels { factory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)

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
            listVM.getUsers()
        })

        listVM.userListLiveData.observe(
            viewLifecycleOwner
        ) {
            userAdapter.submitList(it)
        }

        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = userAdapter
        }
    }
}



