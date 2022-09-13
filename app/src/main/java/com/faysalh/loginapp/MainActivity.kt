package com.faysalh.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faysalh.loginapp.adapter.AppAdapter
import com.faysalh.loginapp.databinding.ActivityMainBinding
import com.faysalh.loginapp.entity.AppModel
import com.faysalh.loginapp.viewmodel.AppViewModel

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var appViewModel:AppViewModel
   lateinit var adapter : AppAdapter



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        appViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingBTN.setOnClickListener {

            val intent = Intent(this,TodoActivity::class.java)
            startActivity(intent)
        }
         adapter = AppAdapter(this,::actionCallbackTwo)
        appViewModel.getAllView().observe(this){

            adapter .submitList(it)

        }

        val lm = LinearLayoutManager(this)
        binding.rclView.layoutManager = lm
        binding.rclView.adapter = adapter



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.queryHint="Search Something!"
        searchView?.setOnQueryTextListener(this)
        return true
        }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            getAllView(query)
        }
        return true
    }

    private fun getAllView(query:String){
        var searchQuery = query
         searchQuery = "%$searchQuery%"

        appViewModel.searchDatabaseView(searchQuery).observe(this) { list ->
              adapter.submitList(list)
            Log.e("TAG", "Data:$list ", )

        }
    }
   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite->{
                val intent = Intent(this,FavoriteActivity::class.java)
                startActivity(intent)

            }
        }

        return super.onOptionsItemSelected(item)
    }*/


    private fun actionCallbackTwo(appModel: AppModel, tag:String){

        when(tag){

            deleteClick -> {
                appViewModel.remove(appModel)
            }


        }
    }

}