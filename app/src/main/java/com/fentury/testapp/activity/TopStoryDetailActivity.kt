package com.fentury.testapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.fentury.testapp.R
import com.fentury.testapp.model.Model
import com.fentury.testapp.utils.Constants
import com.fentury.testapp.utils.StringFormatter
import kotlinx.android.synthetic.main.activity_top_story.*
import java.text.SimpleDateFormat

/**
 * Created by Vadim on 19.11.2016.
 */

class TopStoryDetailActivity : AppCompatActivity() {
    var model: Model? = null
    var webView: WebView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_story)
        model = intent.getSerializableExtra(Constants.KEY) as Model
        populateTextViews()
    }

    private fun populateTextViews() {
        val stringFormatter = StringFormatter(this)
        val simpleDateFormat = SimpleDateFormat(Constants.DATE_FORMAT)
        name?.text = stringFormatter.formatString(R.string.name, model?.title)
        score?.text = stringFormatter.formatString(R.string.score, model?.score!!.toString())
        kids?.text = stringFormatter.formatString(R.string.kids, model?.kidsAsString)
        id?.text = stringFormatter.formatString(R.string.id, model?.id?.toString())
        time?.text = stringFormatter.formatString(R.string.time, simpleDateFormat.format(model?.time!! * 1000L))
        type?.text = stringFormatter.formatString(R.string.type, model?.type)
        creator?.text = stringFormatter.formatString(R.string.creator, model?.by)
        numberOfComments?.text = stringFormatter.formatString(R.string.number_of_comments, model?.descendants?.toString())
        webView = findViewById(R.id.url) as WebView
        webView?.settings?.javaScriptEnabled = true
        webView?.loadUrl(model?.url)
    }
}