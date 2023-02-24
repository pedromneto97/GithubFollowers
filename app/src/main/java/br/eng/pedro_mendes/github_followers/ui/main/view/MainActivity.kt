package br.eng.pedro_mendes.github_followers.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.eng.pedro_mendes.github_followers.data.models.User
import br.eng.pedro_mendes.github_followers.data.provider.ApiHelper
import br.eng.pedro_mendes.github_followers.data.provider.ApiService
import br.eng.pedro_mendes.github_followers.databinding.ActivityMainBinding
import br.eng.pedro_mendes.github_followers.ui.core.ViewModelFactory
import br.eng.pedro_mendes.github_followers.ui.main.adapter.MainAdapter
import br.eng.pedro_mendes.github_followers.ui.main.view_model.MainViewModel
import br.eng.pedro_mendes.github_followers.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getUsers().observe(this) {
            binding.apply {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { users ->
                            renderList(users)
                        }
                        followersRecyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        followersRecyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        val retrofitClient = ApiService.create("https://api.github.com")

        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelper(
                    retrofitClient.create(ApiService::class.java)
                )
            )
        )[MainViewModel::class.java]
    }

    private fun setupUI() {
        binding.followersRecyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            adapter = MainAdapter(arrayListOf())
            it.addItemDecoration(
                DividerItemDecoration(
                    it.context,
                    (it.layoutManager as LinearLayoutManager).orientation
                ),
            )
            it.adapter = adapter
        }
    }

}