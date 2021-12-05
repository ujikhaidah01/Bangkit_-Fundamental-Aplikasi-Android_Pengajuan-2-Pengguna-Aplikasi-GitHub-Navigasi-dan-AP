package com.example.buah.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buah.R
import com.example.buah.recyclerView.UserData
import com.example.buah.recyclerView.UserDetail
import kotlinx.android.synthetic.main.activity_user_detail.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private val datanya = ArrayList<UserData>()

    fun setData(items: ArrayList<UserData>) {
        datanya.clear()
        datanya.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return datanya.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(datanya[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(detailuser: UserData) {
            with(itemView) {
                Glide.with(context)
                    .load(detailuser.logo)
                    .into(gambarUser)
                tv_nama.text = detailuser.login

                itemView.setOnClickListener {
                    Intent(context, UserDetail::class.java).apply {
                        putExtra(UserDetail.Usernamenya, detailuser.login)
                    }.run {
                        context.startActivity(this)
                    }
                }
            }
        }
    }
}