package com.example.buah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buah.R
import com.example.buah.recyclerView.UserData
import kotlinx.android.synthetic.main.item_detail.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.UserViewHolder>() {

    private val datanya = ArrayList<UserData>()

    fun setData(items: ArrayList<UserData>) {
        datanya.clear()
        datanya.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return UserViewHolder(View)
    }

    override fun getItemCount(): Int {
        return datanya.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(datanya[position])
    }

    inner class UserViewHolder(item1: View) : RecyclerView.ViewHolder(item1) {
        fun bind(detailUser: UserData) {
            with(itemView) {
                Glide.with(context)
                    .load(detailUser.logo)
                    .into(detail_avatarUrl)

                detail_name.text = detailUser.name
                detail_type.text = detailUser.login
                detail_locationCity.text = detailUser.location
                detail_publicRepos.text = detailUser.repository.toString()
                detail_followers.text = detailUser.followers.toString()
                detail_following.text = detailUser.following.toString()
            }
        }
    }
}