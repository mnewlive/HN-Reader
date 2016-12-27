package com.fentury.testapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.widget.TextView
import com.fentury.testapp.R
import com.fentury.testapp.model.Model
import com.fentury.testapp.utils.Constants
import com.fentury.testapp.utils.StringFormatter
import java.text.SimpleDateFormat

/**
 * Created by Vadim on 19.11.2016.
 */

class TopStoryDetailActivity : AppCompatActivity() {
    var model: Model? = null
    var name: TextView? = null
    var score: TextView? = null
    var creator: TextView? = null
    var numberOfComments: TextView? = null
    var webView: WebView? = null
    var id: TextView? = null
    var type: TextView? = null
    var time: TextView? = null
    var kids: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_story)
        model = intent.getSerializableExtra(Constants.KEY) as Model
        populateTextViews()
    }

    private fun populateTextViews() {
        val stringFormatter = StringFormatter(this)
        val simpleDateFormat = SimpleDateFormat(Constants.DATE_FORMAT)
        name = findViewById(R.id.name) as TextView
        name!!.text = stringFormatter.formatString(R.string.name, model!!.title)
        score = findViewById(R.id.score) as TextView
        score!!.text = stringFormatter.formatString(R.string.score, model!!.score!!.toString())
        kids = findViewById(R.id.kids) as TextView
        kids!!.text = stringFormatter.formatString(R.string.kids, model!!.kidsAsString)
        id = findViewById(R.id.id) as TextView
        id!!.text = stringFormatter.formatString(R.string.id, model!!.id!!.toString())
        time = findViewById(R.id.time) as TextView
        time!!.text = stringFormatter.formatString(R.string.time, simpleDateFormat.format(model!!.time!! * 1000L))
        type = findViewById(R.id.type) as TextView
        type!!.text = stringFormatter.formatString(R.string.type, model!!.type)
        creator = findViewById(R.id.creator) as TextView
        creator!!.text = stringFormatter.formatString(R.string.creator, model!!.by)
        numberOfComments = findViewById(R.id.numberOfComments) as TextView
        numberOfComments!!.text = stringFormatter.formatString(R.string.number_of_comments, model!!.descendants!!.toString())
        webView = findViewById(R.id.url) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.loadUrl(model!!.url)
    }
}