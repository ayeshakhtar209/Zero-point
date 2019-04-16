package org.cfp.citizenconnect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TwitterActivity extends AppCompatActivity {

    RecyclerView Tweets;
    TweetTimelineRecyclerViewAdapter TwitterAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitterfeed_layout);

        Tweets = (RecyclerView) findViewById(R.id.TweetList);

        LinearLayoutManager tweetList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Tweets.setLayoutManager(tweetList);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("dcislamabad").maxItemsPerRequest(10).build();

        TwitterAdapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();
        Tweets.setAdapter(TwitterAdapter);
    }
}
