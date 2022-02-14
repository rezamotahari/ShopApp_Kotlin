package com.example.novinshop_project.feature.comment.repo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.Resps

class AdapterShowCommnet(val commnets:List<Resps>):RecyclerView.Adapter<AdapterShowCommnet.ShowCommnetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCommnetViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_show_comment,parent,false)
        return ShowCommnetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowCommnetViewHolder, position: Int) {

        val itemCommnet = commnets[position]

      if ( itemCommnet.positive.equals(""))
          holder.txtPosotive.visibility =View.GONE
        holder.txtAdvice.text = itemCommnet.advice
        holder.txtContent.text = itemCommnet.content
        holder.txtDate.text = itemCommnet.date
        holder.txtTitle.text = itemCommnet.title
        holder.txtUser.text = itemCommnet.nameFamily
        val scoreValue :String = itemCommnet.value
        holder.txtScore.text = scoreValue
        holder.txtNegative.text = itemCommnet.negative
        val posotive:String = itemCommnet.positive.toString().replace(",","\n")
        holder.txtPosotive.text = posotive

    }

    override fun getItemCount(): Int =commnets.size
    class ShowCommnetViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtScore =itemView.findViewById<TextView>(R.id.txt_score)
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_title)
        val txtDate =itemView.findViewById<TextView>(R.id.txt_date)
        val txtUser =itemView.findViewById<TextView>(R.id.txt_user)
        val txtAdvice =itemView.findViewById<TextView>(R.id.txt_advice)
        val txtContent =itemView.findViewById<TextView>(R.id.txt_content)
        val txtPosotive =itemView.findViewById<TextView>(R.id.txt_posotive)
        val txtNegative =itemView.findViewById<TextView>(R.id.txt_minus)
    }
}