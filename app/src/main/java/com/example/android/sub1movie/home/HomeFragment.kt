package com.example.android.sub1movie.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.sub1movie.detail.DetailActivity
import com.example.android.sub1movie.R
import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.ui.MovieAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class HomeFragment : Fragment() {

    private val viewModelScope = getKoin().getOrCreateScope("Scope1", named("AppModuleViewModel"))
    private val homeViewModel: HomeViewModel by viewModelScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            pg_home.visibility = View.VISIBLE
            val movieAdapterFeat = MovieFeaturedAdapter()
            val movieAdapterNow = MovieAdapter()
            movieAdapterFeat.onItemClick = {data ->
                Toast.makeText(activity, data.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_DETAIL, data)
                activity?.startActivity(intent)
            }
            movieAdapterNow.onItemClick = {data ->
                Toast.makeText(activity, data.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_DETAIL, data)
                activity?.startActivity(intent)
            }

            homeViewModel.getAllMovies().observe(viewLifecycleOwner, {data ->
                if (data != null){
                    when(data){
                        is Resource.Loading -> {
                            pg_home.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            pg_home.visibility = View.GONE
                            movieAdapterFeat.setData(data.data)
                            movieAdapterNow.setData(data.data)
                        }
                        is Resource.Error -> {
                            pg_home.visibility = View.GONE
                            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rv_home_featured){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
                setHasFixedSize(true)
                adapter = movieAdapterFeat
            }

            with(rv_home_now){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapterNow
            }
        }

    }
}