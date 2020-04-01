package com.user.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.user.indexedrecyclerview.BaseMovieAdapter
import com.user.indexedrecyclerview.MovieAdapterByTitle
import com.user.indexedrecyclerview.R
import com.user.indexedrecyclerview.RecyclerViewDataModel
import com.user.view.viewmodel.RecyclerViewDataViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity(), BaseMovieAdapter.OnItemClickListener {
    var recyclerViewDataViewModel: RecyclerViewDataViewModel? = null
    var adapter: MovieAdapterByTitle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
        initViewModel()
        initView()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        adapter = MovieAdapterByTitle(recyclerViewDataViewModel!!.recyclerViewData!!.value)
        recyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(this)
    }

    private fun initViewModel() {
        recyclerViewDataViewModel = ViewModelProviders.of(this).get(RecyclerViewDataViewModel::class.java)
        recyclerViewDataViewModel!!.init()
        recyclerViewDataViewModel!!.recyclerViewData!!.observe(this,
            Observer<List<RecyclerViewDataModel?>> { adapter!!.notifyDataChanged() }
        )
    }

    override fun onItemClicked(movie: RecyclerViewDataModel) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
    }
}