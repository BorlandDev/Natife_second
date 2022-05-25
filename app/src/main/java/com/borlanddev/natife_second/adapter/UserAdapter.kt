package com.borlanddev.natife_second.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.ItemUserBinding
import com.borlanddev.natife_second.model.Name
import com.borlanddev.natife_second.model.User

class UserAdapter(private var onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UserHolder>() {

    private var users: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun setUserList(_user: List<User>) {
        users = _user
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.bind(user, onItemClick)
    }
}

class UserHolder(private val binding: ItemUserBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, onUserClick: (User) -> Unit) {

        binding.userNameTextView.text = user.name?.let { getNameFormat(it, context) }

        // Use to Glide for download images

        binding.itemUser.setOnClickListener {
            onUserClick.invoke(user)
        }
    }
}

private fun getNameFormat(userName: Name, context: Context): String {
    val (title, first, last) = userName

    return context.getString(R.string.user_name_format, title, first, last)
}