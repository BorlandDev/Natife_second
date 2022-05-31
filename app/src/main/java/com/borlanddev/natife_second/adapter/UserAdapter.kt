package com.borlanddev.natife_second.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.ItemUserBinding
import com.borlanddev.natife_second.helpers.PREFETCH_DISTANCE
import com.borlanddev.natife_second.model.UserDB
import com.bumptech.glide.Glide

class UserAdapter(
    private var onItemClick: (UserDB) -> Unit,
    private val onPageEndReached: () -> Unit
) :
    ListAdapter<UserDB, UserAdapter.UserHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
        if (itemCount - position == PREFETCH_DISTANCE) {
            onPageEndReached.invoke()
        }
    }

    class UserHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserDB, onUserClick: (UserDB) -> Unit) {
            Glide.with(itemView.context)
                .load(user.picture)
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .into(binding.imageListUser)

            binding.userNameTextView.text = user.name

            binding.itemUser.setOnClickListener {
                onUserClick.invoke(user)
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserHolder {
                return UserHolder(
                    ItemUserBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    class UsersDiffCallback
        : DiffUtil.ItemCallback<UserDB>() {

        override fun areItemsTheSame(oldItem: UserDB, newItem: UserDB): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDB, newItem: UserDB): Boolean {
            return oldItem == newItem
        }
    }
}