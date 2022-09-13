package com.faysalh.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.faysalh.loginapp.adapter.AppAdapter
import com.faysalh.loginapp.databinding.ActivityFavoriteBinding
import com.faysalh.loginapp.entity.AppModel
import com.faysalh.loginapp.viewmodel.AppViewModel

class FavoriteActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
lateinit var binding :ActivityFavoriteBinding
    private val appViewModel: AppViewModel by viewModels()
    private lateinit var adapter :AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "Favorite"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


                val lm = LinearLayoutManager(this)
                val adapter = AppAdapter(this,::actionCallback)

                /*appViewModel.favoriteView().observe(this){
                    adapter.submitList(it)
                }*/
        binding.favRCL.layoutManager = lm
        binding.favRCL.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.queryHint = "Search Something!"
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            getAllView(query)
        }
        return true
    }


    fun getAllView(query: String) {
        var searchQuery = query
        searchQuery = "%$searchQuery%"

        appViewModel.searchDatabaseView(searchQuery).observe(this) { list ->
             adapter.submitList(list)

        }
    }

    private fun actionCallback(appModel: AppModel, tag:String){

        when(tag){

            on -> {
                appViewModel.insert(appModel)
                /*appViewModel.favoriteView().observe(this) {
                    adapter.submitList(it)
                }*/
            }

                off -> {
                    appViewModel.remove(appModel)
                }
            }
        }
    }
