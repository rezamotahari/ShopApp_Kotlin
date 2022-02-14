package com.example.novinshop_project.feature.comment.repo.insertComment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ScoreItems
import com.google.android.material.slider.Slider

class AdapterScoreInsert(val scores: List<ScoreItems>) :
    RecyclerView.Adapter<AdapterScoreInsert.TypeViewHolder>() {

    lateinit var onchangeSlider: OnChangeItemSlider
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_insert_score, parent, false)
        return TypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {

        val itemScore = scores[position]
        holder.txtScoreName.text = itemScore.scoreName
        holder.slider.value = itemScore.value.toFloat()
        holder.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                onchangeSlider.onChangeScore(slider.value,itemScore)
            }

        })


    }

    override fun getItemCount(): Int = scores.size
    fun setOnChangeSlider(onchangeSlider: OnChangeItemSlider) {
        this.onchangeSlider = onchangeSlider
    }

    class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val slider = itemView.findViewById<Slider>(R.id.slider_score)
        val txtScoreName = itemView.findViewById<TextView>(R.id.txt_score)
    }


    interface OnChangeItemSlider {
        fun onChangeScore(value: Float,item:ScoreItems)
    }

}