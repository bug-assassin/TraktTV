package com.pixis.traktTV.screen_main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pixis.traktTV.R

class MoviesFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (view.findViewById(R.id.textView) as TextView).text = Math.random().toString()
    }
}