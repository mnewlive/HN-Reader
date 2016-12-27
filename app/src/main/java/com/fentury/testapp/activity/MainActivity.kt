package com.fentury.testapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.fentury.testapp.R
import com.fentury.testapp.adapter.TopStoriesAdapter
import com.fentury.testapp.api.StoriesApi
import com.fentury.testapp.model.Model
import com.fentury.testapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var emptyView: TextView? = null
    private var topStoriesAdapter: TopStoriesAdapter? = null
    private var topStories: List<Int> = ArrayList()
    private var retrofit: Retrofit? = null
    private var request: StoriesApi? = null
    private val models = ArrayList<Model>()
    private var mLoadPosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initRetrofit()
        loadJSON()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        request = retrofit!!.create(StoriesApi::class.java)
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.card_recycler_view) as RecyclerView
        emptyView = findViewById(R.id.empty_view) as TextView

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        topStoriesAdapter = TopStoriesAdapter(models, this)
        recyclerView!!.adapter = topStoriesAdapter
        if (topStories.isEmpty()) {
            recyclerView!!.visibility = View.VISIBLE
            emptyView!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            emptyView!!.visibility = View.VISIBLE
        }
    }

    private fun loadJSON() {
        val call = request!!.getTopStories()
        call.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                topStories = response.body()
                loadDetails()
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadDetails() {
        mLoadPosition++
        if (mLoadPosition == topStories.size) {
            return
        }
        val id = topStories[mLoadPosition]
        request!!.getTopStore(id).enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                models.add(response.body())
                topStoriesAdapter!!.notifyDataSetChanged()
                loadDetails()
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_LONG).show()
            }
        })
    }
}
