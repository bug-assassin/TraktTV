package com.pixis.traktTV.screen_main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import com.pixis.traktTV.R

class MainActivity: AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            onOptionsItemSelected(menuItem)
            drawerLayout.closeDrawers()
            true
         }

        viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            val fragments = listOf(MoviesFragment(), MoviesFragment(), MoviesFragment())
            val titles = listOf("Trending Movies", "Trending Shows", "Watchlist")

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(Gravity.START)
                return true
            }
            R.id.nav_movies -> {viewPager.setCurrentItem(0, false); return true}
            R.id.nav_shows -> {viewPager.setCurrentItem(1, false); return true}
            R.id.nav_watchlist -> {viewPager.setCurrentItem(2, false); return true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}