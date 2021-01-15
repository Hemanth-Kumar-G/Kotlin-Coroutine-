package com.hemanth.kotlinCoroutines.eachCoroutineExample.task.onetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hemanth.kotlinCoroutines.R
import com.hemanth.kotlinCoroutines.data.api.ApiHelperImpl
import com.hemanth.kotlinCoroutines.data.api.RetrofitBuilder
import com.hemanth.kotlinCoroutines.utils.Status
import com.hemanth.kotlinCoroutines.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_long_running_task.*

class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: LongRunningTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getStatus().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    textView.text = it.data
                    textView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.startLongRunningTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(LongRunningTaskViewModel::class.java)
    }
}
