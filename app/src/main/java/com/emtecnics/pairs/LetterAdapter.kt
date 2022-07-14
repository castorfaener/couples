package com.emtecnics.pairs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter(private val letterList: List<Letter>, private val onClickListener: (Letter) -> Unit ):RecyclerView.Adapter<LetterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LetterViewHolder(layoutInflater.inflate(R.layout.item_letter, parent,false))
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = letterList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return letterList.size
    }
}