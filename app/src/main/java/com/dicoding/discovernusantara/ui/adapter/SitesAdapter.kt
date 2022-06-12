package com.dicoding.discovernusantara.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.discovernusantara.data.db.SitesEntity
import com.dicoding.discovernusantara.databinding.SitesItemBinding
import com.dicoding.discovernusantara.ui.DetailActivity

class SitesAdapter: ListAdapter<SitesEntity, SitesAdapter.SitesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitesViewHolder {
        val binding = SitesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SitesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SitesViewHolder, position: Int) {
        val sites = getItem(position)
        holder.bind(sites)
    }

    class SitesViewHolder(val binding: SitesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SitesEntity) {
            binding.txtName.text = data.name
            binding.txtLocation.text = "${data.city}, ${data.province}"
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.circleImageView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_SITES, data)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<SitesEntity> =
            object : DiffUtil.ItemCallback<SitesEntity>() {
                override fun areItemsTheSame(oldUser: SitesEntity, newUser: SitesEntity): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: SitesEntity, newUser: SitesEntity): Boolean {
                    return oldUser == newUser
                }
            }
    }
}