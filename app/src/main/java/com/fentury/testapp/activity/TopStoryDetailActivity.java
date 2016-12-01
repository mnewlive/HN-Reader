package com.fentury.testapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.fentury.testapp.R;
import com.fentury.testapp.model.Model;
import com.fentury.testapp.utils.Constants;
import com.fentury.testapp.utils.StringFormatter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vadim on 19.11.2016.
 */

public class TopStoryDetailActivity extends AppCompatActivity {
    private Model model;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.creator)
    TextView creator;
    @BindView(R.id.numberOfComments)
    TextView numberOfComments;
    @BindView(R.id.url)
    WebView webView;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.kids)
    TextView kids;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_story);
        model = (Model) getIntent().getSerializableExtra("key");
        ButterKnife.bind(this);
        populateTextViews();
    }

    private void populateTextViews() {
        StringFormatter stringFormatter = new StringFormatter(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        name.setText(stringFormatter.formatString(model.getTitle(), R.string.name));
        kids.setText(stringFormatter.formatString(model.getKidsAsString(), R.string.kids));
        id.setText(stringFormatter.formatString(model.getId().toString(), R.string.id));
        time.setText(stringFormatter.formatString((simpleDateFormat.format(model.getTime() * 1000L)), R.string.time));
        type.setText(stringFormatter.formatString(model.getType(), R.string.type));
        score.setText(stringFormatter.formatString(model.getScore().toString(), R.string.score));
        creator.setText(stringFormatter.formatString(model.getBy(), R.string.creator));
        numberOfComments.setText(stringFormatter.formatString(model.getDescendants().toString(), R.string.number_of_comments));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(model.getUrl());
    }
}