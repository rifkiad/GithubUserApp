package com.example.githubuserapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp.Api.DetailResponse
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.viewmodel.DetailUserViewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUsername = intent.getStringExtra("USERNAME")

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailUserViewModel::class.java]
        dataUsername?.let { viewModel.detailUser(it) }

        viewModel.detailUser.observe(this) {
            setDetailUser(it)
        }
        viewModel.isLoading.observe(this) {
            setLoading(it)
        }
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setDetailUser(dataUser: DetailResponse) {
        Glide.with(this)
            .load(dataUser.avatarUrl)
            .into(binding.imgAvatar)

        binding.tvName.text = dataUser.name
        binding.tvBio.text = (dataUser.bio ?: "-") as CharSequence?
        binding.tvCompany.text = dataUser.company ?: "-"
        binding.tvLocation.text = dataUser.location ?: "-"
    }
}