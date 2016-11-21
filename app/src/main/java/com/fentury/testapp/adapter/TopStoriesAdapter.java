package com.fentury.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fentury.testapp.R;
import com.fentury.testapp.activity.TopStoryDetailActivity;
import com.fentury.testapp.model.Model;
import com.fentury.testapp.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Vadim on 19.11.2016.
 */

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {
    private List<Model> topStoriesList;
    private Context context;


    public TopStoriesAdapter(List<Model> topStoriesList, Context context) {
        this.context = context;
        this.topStoriesList = topStoriesList;
    }

    @Override
    public TopStoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new TopStoriesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TopStoriesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.storyTitle.setText(topStoriesList.get(i).getTitle());
        viewHolder.storyScore.setText(String.valueOf(topStoriesList.get(i).getScore()));
        viewHolder.storyCreator.setText(topStoriesList.get(i).getBy());
        viewHolder.storyNumberOfComments.setText(String.valueOf(topStoriesList.get(i).getDescendants()));
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        viewHolder.storyDate.setText(sdf.format(topStoriesList.get(i).getTime() * 1000L));
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TopStoryDetailActivity.class);
                intent.putExtra("key", topStoriesList.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topStoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.storyTitle)
        TextView storyTitle;
        @BindView(R.id.storyScore)
        TextView storyScore;
        @BindView(R.id.storyCreator)
        TextView storyCreator;
        @BindView(R.id.storyNumberOfComments)
        TextView storyNumberOfComments;
        @BindView(R.id.storyDate)
        TextView storyDate;
        private View view;
        private SharedPreferences sharedPreferences;
        final String ID = context.getString(R.string.save_id);

        public ViewHolder(View view) {
            super(view);
            storyTitle = (TextView) view.findViewById(R.id.storyTitle);
            storyScore = (TextView) view.findViewById(R.id.storyScore);
            storyCreator = (TextView) view.findViewById(R.id.storyCreator);
            storyNumberOfComments = (TextView) view.findViewById(R.id.storyNumberOfComments);
            storyDate = (TextView) view.findViewById(R.id.storyDate);
            this.view = view;
        }
    }
}
