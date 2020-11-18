package com.example.android.sub1movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.android.sub1movie.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeFragment())
        btm_nav.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        btm_nav.add(MeowBottomNavigation.Model(2, R.drawable.ic_fav_menu))
        btm_nav.show(1, true)

        btm_nav.setOnClickMenuListener {

            when(it.id){
                1 -> {
                    replaceFragment(HomeFragment())
                }
                2 -> {
                    replaceFragment(Class.forName("com.example.android.sub1movie.favorite.ui.FavoriteFragment").newInstance() as Fragment)
                }
                else -> Toast.makeText(this, "No Fragment Found", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.container, fragment).commit()
    }

    private fun addFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.container, fragment).commit()
    }
}