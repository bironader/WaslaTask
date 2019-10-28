package com.example.waslatask.features.main

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waslatask.R
import com.example.waslatask.data.remote.QueriesApi
import com.example.waslatask.databinding.ActivityMainBinding
import com.example.waslatask.features.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), OnQueryTextListener, QueriesAdapter.ClickListener {



    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    @Inject
    lateinit var queriesApi: QueriesApi
    @Inject
    lateinit var adapter: QueriesAdapter
    lateinit var autoCompleteList: RecyclerView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        val mainBiding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBiding.lifecycleOwner = this
        autoCompleteList = mainBiding.autoCompleteResult
        mainBiding.mainViewModel = mainViewModel
        mainViewModel.queriesApi = queriesApi

        mainViewModel.getsSuggestedItems().observe(this, Observer { result ->
            adapter.setList(result)
            adapter.notifyDataSetChanged()
        })

//        mainViewModel.getConnectionStatus().observe(this, Observer { })
        populateRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as (SearchView)
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        openBrowser(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        mainViewModel.makeRequest(newText)
        return true
    }

    override fun onClickListener(query: String) {
        openBrowser(query)
        Toast.makeText(this,"clicked",Toast.LENGTH_LONG)
    }

    private fun populateRecyclerView() {
        adapter.setClickListener(this)
        autoCompleteList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        autoCompleteList.setHasFixedSize(true)
        autoCompleteList.adapter = adapter

    }

    private fun openBrowser(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, query)
        startActivity(intent)
    }

}
