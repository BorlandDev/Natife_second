package com.borlanddev.natife_second.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.ItemUserBinding
import com.borlanddev.natife_second.helpers.PREFETCH_DISTANCE
import com.borlanddev.natife_second.model.UserDB
import com.bumptech.glide.Glide

class UsersDiffCallback(
    private val oldList: List<UserDB>,
    private val newList: List<UserDB>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.id == newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }


    class UserAdapter(
        private var onItemClick: (UserDB) -> Unit,
        private val onPageEndReached: () -> Unit
    ) :
        RecyclerView.Adapter<UserHolder>() {

        private var users: List<UserDB> = emptyList()
            @SuppressLint("NotifyDataSetChanged")
            set(newValue) {
                val diffCallback = UsersDiffCallback(field, newValue)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                field = newValue
                diffResult.dispatchUpdatesTo(this)
            }

        fun setUserList(_user: List<UserDB>) {
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
            if (itemCount - position == PREFETCH_DISTANCE) {
                onPageEndReached.invoke()
            }
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
    }
}