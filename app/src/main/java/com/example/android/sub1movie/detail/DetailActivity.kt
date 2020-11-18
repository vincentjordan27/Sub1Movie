package com.example.android.sub1movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.sub1movie.R
import com.example.android.sub1movie.core.domain.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class DetailActivity : AppCompatActivity() {

    private val viewModelScope = getKoin().getOrCreateScope("Scope1", named("AppModuleViewModel"))
    private val viewModel: DetailViewModel by viewModelScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        pg_detail.visibility = View.VISIBLE

        val movie = intent.getParcelableExtra<Movie>(MOVIE_DETAIL)

        val genreAdapter = GenreAdapter()


        if (movie != null){

            Glide.with(this).load(movie.image).into(img_movie_detail)
            tv_detail_header.text = movie.title
            tv_detail_title.text = movie.title
            tv_detail_rating.text = movie.rating.toString()
            setButton(movie.isFavorite)

            viewModel.getDetailMovie(movie.id.toInt())
            viewModel.result.observe(this, {
                if (it != null){
                    tv_detail_overview.text = it.overview
                    genreAdapter.setData(it.genres)
                    pg_detail.visibility = View.GONE
                }else {
                    Log.d("DEBUGSS" , "Kosong")

                }
            })


        }

        rv_genres.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
            adapter = genreAdapter
            setHasFixedSize(true)
        }


        btn_fav.setOnClickListener {
            if (movie != null) {
                pg_detail.visibility = View.VISIBLE
                movie.isFavorite = !movie.isFavorite
                viewModel.setFavorite(movie, movie.isFavorite)
                setButton(movie.isFavorite)
                pg_detail.visibility = View.GONE

            }
        }


        btn_back_detail.setOnClickListener {
            finish()
        }

    }

    private fun setButton(state: Boolean){
        if (state){
            btn_fav.setBackgroundResource(R.drawable.ic_fav_true)
        }else {
            btn_fav.setBackgroundResource(R.drawable.ic_fav_false)
        }
    }

    companion object {
        const val MOVIE_DETAIL = "movie"
    }
}