package com.example.novinshop_project.feature.comment.repo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseRating

class AdapterRatingCommnet(val rating:List<ResponseRating>):RecyclerView.Adapter<AdapterRatingCommnet.RatinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatinViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_rating,parent,false)
        return RatinViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatinViewHolder, position: Int) {

        val itemRating = rating[position]
        val scoreValue:String = itemRating.scoreValue.toString().substring(0,3)
        holder.txtTitle.text = itemRating.scoreName
        holder.prRating.progress = itemRating.scoreValue.toFloat()
        holder.txtScore.text = scoreValue
    }

    override fun getItemCount(): Int =rating.size
    class RatinViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_ratingItem_title)
        val txtScore =itemView.findViewById<TextView>(R.id.txt_ratingItem_score)
        val prRating =itemView.findViewById<RoundCornerProgressBar>(R.id.roundCornerProgressBar)
    }
}