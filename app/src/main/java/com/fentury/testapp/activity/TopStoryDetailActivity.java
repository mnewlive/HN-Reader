package com.fentury.testapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fentury.testapp.R;
import com.fentury.testapp.model.Model;
import com.fentury.testapp.utils.StringFormater;

import butterknife.BindView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_story);
        model = getIntent().getParcelableExtra("key");
        populateTextViews();
    }

    private void populateTextViews() {
        StringFormater stringFormater = new StringFormater(this);
        name = (TextView) findViewById(R.id.name);
        name.setText(stringFormater.formatString(model.getTitle(), R.string.name));
        score = (TextView) findViewById(R.id.score);
        score.setText(stringFormater.formatString(model.getScore().toString(), R.string.score));
        creator = (TextView) findViewById(R.id.creator);
        creator.setText(stringFormater.formatString(model.getBy(), R.string.creator));
        numberOfComments = (TextView) findViewById(R.id.numberOfComments);
        numberOfComments.setText(stringFormater.formatString(model.getDescendants().toString(), R.string.number_of_comments));
    }
}
