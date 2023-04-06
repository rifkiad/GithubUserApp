package com.example.githubuserapp.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.GithubResponse
import com.example.githubuserapp.ItemsItem
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.list.ListUserAdapter
import com.example.githubuserapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
        viewModel.listUser.observe(this) { githubResponse ->
            setListUser(githubResponse)
        }
        viewModel.isLoading.observe(this) { boolean ->
            setLoading(boolean)
        }
    }

    private fun setLoading(loading: Boolean?) {
        binding.progressBar.visibility = if (loading == true) View.VISIBLE else View.GONE
    }

    private fun setListUser(githubResponse: GithubResponse) {
        val adapter = ListUserAdapter()
        binding.rvListUser.adapter = adapter
        binding.rvListUser.layoutManager = LinearLayoutManager(this)

        adapter.setListItems(githubResponse.items as List<ItemsItem>)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Ketikkan Nama"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.listUser(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }
}