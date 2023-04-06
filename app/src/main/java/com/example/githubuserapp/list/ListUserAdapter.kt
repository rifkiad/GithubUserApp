package com.example.githubuserapp.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.ItemsItem
import com.example.githubuserapp.databinding.ItemUserBinding
import com.example.githubuserapp.main.DetailUserActivity

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private var listItem: MutableList<ItemsItem> = mutableListOf()

    fun setListItems(listItem: List<ItemsItem>) {
        clearListItems()
        addListItem(listItem)
    }

    private fun addListItem(listItem: List<ItemsItem>) {
        this.listItem.addAll(listItem)    }

    private fun clearListItems() {
        this.listItem.clear()
    }


    class ListUserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: ItemsItem) {
            with(item) {
                binding.tvName.text = this.login
                binding.tvUrlProfile.text = this.urlProfile
                Glide.with(itemView)
                    .load(this.avatarUrl)
                    .into(binding.imgAvatar)

                itemView.setOnClickListener {
                    val intentToDetail = Intent(it.context, DetailUserActivity::class.java)
                    intentToDetail.putExtra("USERNAME", this.login)
                    it.context.startActivity(intentToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bindView(listItem[position])
    }
}
