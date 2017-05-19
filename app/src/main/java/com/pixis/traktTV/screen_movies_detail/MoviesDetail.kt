package com.pixis.traktTV.screen_movies_detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import butterknife.BindView

import com.pixis.traktTV.R
import com.pixis.trakt_api.services_fanart.MovieImages
import com.pixis.trakt_api.services_trakt.models.Movie
import com.squareup.picasso.Picasso

class MoviesDetail : AppCompatActivity() {

    @BindView(R.id.imgMediaTrailer)
    lateinit var imgMediaTrailer: ImageView

    lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        viewModel.bindView()

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbarLayout = findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
        val appBarLayout = findViewById(R.id.app_bar) as AppBarLayout

        var isShow = false
        var scrollRange = -1

        collapsingToolbarLayout.title = " "
        appBarLayout.addOnOffsetChangedListener({ appBarLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = title
                    isShow = true
                } else if(isShow) {
                    collapsingToolbarLayout.title = " ";//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
    })
    }

    fun setData(item: Pair<Movie, MovieImages>) {
        Picasso.with(this).load(item.second.moviebackground.first().getPreviewUrl()).into(imgMediaTrailer)
        Picasso.with(this).load(item.second.movieposter.first().getPreviewUrl()).into(imgMediaTrailer)
    }
}
