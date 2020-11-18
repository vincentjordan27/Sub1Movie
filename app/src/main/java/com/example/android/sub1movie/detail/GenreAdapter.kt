package com.example.android.sub1movie.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sub1movie.core.data.source.remote.response.detail.Genre
import com.example.android.sub1movie.R
import kotlinx.android.synthetic.main.layout_genre.view.*

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var data = ArrayList<Genre>()
    fun setData(list: List<Genre>?){
        if(list == null) return
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(genre: Genre){
            with(itemView){
                tv_genre.text = genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}