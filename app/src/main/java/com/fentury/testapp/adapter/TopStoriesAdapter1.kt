package com.fentury.testapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.fentury.testapp.R
import com.fentury.testapp.activity.TopStoryDetailActivity
import com.fentury.testapp.model.Model
import com.fentury.testapp.utils.Constants
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat

/**
 * Created by morozov on 12/20/16.
 */
class TopStoriesAdapter(private val topStoriesList: List<Model>, private val context: Context) : RecyclerView.Adapter<TopStoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TopStoriesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TopStoriesAdapter.ViewHolder, i: Int) {
        viewHolder.storyTitle.text = topStoriesList[i].title
        viewHolder.storyScore.text = topStoriesList[i].score.toString()
        viewHolder.storyCreator.text = topStoriesList[i].by
        viewHolder.storyNumberOfComments.text = topStoriesList[i].descendants.toString()
        val sdf = SimpleDateFormat(Constants.DATE_FORMAT)
        viewHolder.storyDate.text = sdf.format(topStoriesList[i].time!! * 1000L)
        viewHolder.view.setOnClickListener {
            val intent = Intent(context, TopStoryDetailActivity::class.java)
            intent.putExtra(Constants.KEY, topStoriesList[viewHolder.adapterPosition])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return topStoriesList.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var storyTitle: TextView
        var storyScore: TextView
        var storyCreator: TextView
        var storyNumberOfComments: TextView
        var storyDate: TextView

        init {
            storyTitle = view.storyTitle
            storyScore = view.storyScore
            storyCreator = view.storyCreator
            storyNumberOfComments = view.storyNumberOfComments
            storyDate = view.storyDate
        }
    }
}