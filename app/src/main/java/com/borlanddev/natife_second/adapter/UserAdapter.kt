package com.borlanddev.natife_second.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.natife_second.databinding.ItemUserBinding
import com.borlanddev.natife_second.model.User

class UserAdapter(private var onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UserHolder>() {

    private var users: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun getUserList(_user: List<User>) {
        users = _user
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.bind(user, onItemClick)
    }
}

class UserHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, onUserClick: (User) -> Unit) {
       binding.userNameTextView.text = user.name.toString()

        // Use to Glide for download images

        binding.itemUser.setOnClickListener {
            onUserClick.invoke(user)
        }
    }
}

