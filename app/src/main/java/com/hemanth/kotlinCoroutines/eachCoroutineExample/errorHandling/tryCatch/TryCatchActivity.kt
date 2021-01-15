package com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.tryCatch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hemanth.kotlinCoroutines.R
import com.hemanth.kotlinCoroutines.data.api.ApiHelperImpl
import com.hemanth.kotlinCoroutines.data.api.RetrofitBuilder
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.eachCoroutineExample.baseAdapter.ApiUserAdapter
import com.hemanth.kotlinCoroutines.utils.Status
import com.hemanth.kotlinCoroutines.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_common_recycler_view.*

class TryCatchActivity :AppCompatActivity(){
    private lateinit var viewModel: TryCatchViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_recycler_view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        adapter = ApiUserAdapter(arrayListOf())
        recyclerView.adapter = adapter

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(TryCatchViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}