package com.hemanth.kotlinCoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.series.SeriesNetworkCallsActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.single.SingleNetworkCallActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {}
    fun startRoomDatabaseActivity(view: View) {}
    fun startTimeoutActivity(view: View) {}
    fun startTryCatchActivity(view: View) {}
    fun startExceptionHandlerActivity(view: View) {}
    fun startIgnoreErrorAndContinueActivity(view: View) {}
    fun startLongRunningTaskActivity(view: View) {}
    fun startTwoLongRunningTasksActivity(view: View) {}


}