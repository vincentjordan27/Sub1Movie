package com.example.android.sub1movie.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.sub1movie.R
import com.example.android.sub1movie.core.domain.model.Movie
import kotlinx.android.synthetic.main.layout_featured.view.*

class MovieFeaturedAdapter : RecyclerView.Adapter<MovieFeaturedAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newData: List<Movie>?){
        if(newData == null) return
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie){
            with(itemView){
                Glide.with(this).load(data.image).into(img_featured)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_featured, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}