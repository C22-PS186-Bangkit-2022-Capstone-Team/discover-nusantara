package com.dicoding.discovernusantara.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.discovernusantara.data.Sites
import com.dicoding.discovernusantara.databinding.SitesItemBinding
import com.dicoding.discovernusantara.ui.DetailActivity

class ListSitesAdapter(val listSites: ArrayList<Sites>) :
    RecyclerView.Adapter<ListSitesAdapter.ListSitesViewHolder>() {
    class ListSitesViewHolder(val binding: SitesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sites) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSitesViewHolder {
        val binding = SitesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSitesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSitesViewHolder, position: Int) {
        val data = listSites[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listSites.size
}