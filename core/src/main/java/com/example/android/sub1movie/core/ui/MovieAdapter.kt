package com.example.android.sub1movie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.sub1movie.core.R
import com.example.android.sub1movie.core.domain.model.Movie
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newData: List<Movie>?){
        if(newData == null) return
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie){
            with(itemView){

                Glide.with(this).load(data.image).into(img_movie)
                tv_title_movie.text = data.title
                tv_rating_movie.text = data.rating.toString()
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}