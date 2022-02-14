package com.example.novinshop_project.feature.detials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.CommentsItem
import com.google.android.material.card.MaterialCardView

class AdapterCommnet(val comment:List<CommentsItem>):RecyclerView.Adapter<AdapterCommnet.CommnetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommnetViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return CommnetViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommnetViewHolder, position: Int) {

        val itemComment = comment[position]
        holder.txtTitle.text = itemComment.title
        holder.txtDate.text = itemComment.commentDate
        holder.txtUser.text = itemComment.nameFamily
        holder.txtContent.text = itemComment.content
    }

    override fun getItemCount(): Int =comment.size
    class CommnetViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_title_commnet)
        val txtDate =itemView.findViewById<TextView>(R.id.txt_date_commnet)
        val txtContent =itemView.findViewById<TextView>(R.id.txt_content_commnet)
        val txtUser =itemView.findViewById<TextView>(R.id.txt_user_commnet)
    }
}