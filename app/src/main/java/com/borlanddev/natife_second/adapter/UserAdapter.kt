package com.borlanddev.natife_second.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.natife_second.R
import com.borlanddev.natife_second.databinding.ItemUserBinding
import com.borlanddev.natife_second.model.UserDB
import com.bumptech.glide.Glide


class UserAdapter(private var onItemClick: (UserDB) -> Unit) :
    ListAdapter<UserDB, UserAdapter.UserHolder>(UsersDiffCallback()) {

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

    class UsersDiffCallback(
        // private val oldList: List<UserDB>,
        // private val newList: List<UserDB>
    ) : DiffUtil.ItemCallback<UserDB>() {

        // override fun getOldListSize() = oldList.size

        // override fun getNewListSize() = newList.size

      //  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      override fun areItemsTheSame(oldItem: UserDB , newItem: UserDB): Boolean {
          //  val oldUser = oldList[oldItemPosition]
          //  val newUser = newList[newItemPosition]
          // return oldUser.id == newUser.id
            return oldItem == newItem
        }

       // override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        override fun areContentsTheSame(oldItem: UserDB , newItem: UserDB): Boolean {
           // val oldUser = oldList[oldItemPosition]
           // val newUser = newList[newItemPosition]
           // return oldUser == newUser
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        return UserHolder.create(parent)
    }


    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position),onItemClick)
    }



    /*
    private var users: List<UserDB> = emptyList()
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
     */

}
