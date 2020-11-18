package com.example.android.sub1movie.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.sub1movie.detail.DetailActivity
import com.example.android.sub1movie.core.ui.MovieAdapter
import com.example.android.sub1movie.favorite.R
import com.example.android.sub1movie.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteFragment : Fragment() {

    private val loadFavorite by lazy { loadKoinModules(favoriteModule) }
    private fun injectFavorite() = loadFavorite

    private val favoriteViewModel: FavoriteViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFavorite()
    }

    override fun onResume() {
        super.onResume()
        pg_fav.visibility = View.VISIBLE

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null){
            pg_fav.visibility = View.VISIBLE

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {data ->
                Toast.makeText(activity, data.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_DETAIL, data)
                activity?.startActivity(intent)
            }

            favoriteViewModel.getAllFavorites().observe(viewLifecycleOwner , {
                Log.d("DEBUGS FAV", it.toString())
                if(it.isNotEmpty()){
                    tv_empty_fav.visibility = View.GONE
                    rv_fav.visibility = View.GONE
                    movieAdapter.setData(it)
                }else {
                    tv_empty_fav.visibility = View.GONE
                    rv_fav.visibility = View.GONE
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    pg_fav.visibility = View.GONE
                    if (it.isNotEmpty()){
                        tv_empty_fav.visibility = View.GONE
                        rv_fav.visibility = View.VISIBLE
                    }else {
                        tv_empty_fav.visibility = View.VISIBLE
                        rv_fav.visibility = View.GONE
                    }
                }, 1000)

            })

            rv_fav.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}