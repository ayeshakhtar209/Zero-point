/*
package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.FilterTimelineDelegate;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TimelineFilter;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;

public class TwitterTimelineAdapter extends
        RecyclerView.Adapter<TwitterTimelineAdapter.TweetViewHolder> {

    protected static final class TweetViewHolder extends RecyclerView.ViewHolder {
        public TweetViewHolder(CompactTweetView itemView) {
            super(itemView);
        }
    }

    public static class Builder {
        private Context context;
        private Timeline<Tweet> timeline;
        private Callback<Tweet> actionCallback;
        private TimelineFilter timelineFilter;
        private int styleResId = R.style.tw__TweetLightStyle;

        */
/**
         * Constructs a Builder.
         * @param context Context for Tweet views.
         *//*

        public Builder(Context context) {
            this.context = context;
        }

        */
/**
         * Sets the Tweet timeline data source.
         * @param timeline Timeline of Tweets
         *//*

        public TweetTimelineRecyclerViewAdapter.Builder setTimeline(Timeline<Tweet> timeline) {
            this.timeline = timeline;
            return this;
        }

        */
/**
         * Sets the Tweet view style by resource id.
         * @param styleResId resource id of the Tweet view style
         *//*

        public TweetTimelineRecyclerViewAdapter.Builder setViewStyle(int styleResId) {
            this.styleResId = styleResId;
            return this;
        }

        */
/**
         * Sets the callback to call when a Tweet action is performed on a Tweet view.
         * @param actionCallback called when a Tweet action is performed.
         *//*

        public TweetTimelineRecyclerViewAdapter.Builder setOnActionCallback(
                Callback<Tweet> actionCallback) {
            this.actionCallback = actionCallback;
            return this;
        }

        */
/**
         * Sets the TimelineFilter used to filter tweets from timeline.
         * @param timelineFilter timelineFilter for timeline
         *//*

        public TweetTimelineRecyclerViewAdapter.Builder setTimelineFilter(
                TimelineFilter timelineFilter) {
            this.timelineFilter = timelineFilter;
            return this;
        }

        */
/**
         * Builds a TweetTimelineRecyclerViewAdapter from Builder parameters.
         * @return a TweetTimelineListAdpater
         *//*

        public TweetTimelineRecyclerViewAdapter build() {
            if (timelineFilter == null) {
                return new TweetTimelineRecyclerViewAdapter(context, timeline, styleResId,
                        actionCallback);
            } else {
                final FilterTimelineDelegate delegate = new FilterTimelineDelegate(timeline,
                        timelineFilter);
                return new TweetTimelineRecyclerViewAdapter(context, delegate, styleResId,
                        actionCallback, TweetUi.getInstance());
            }
        }
    }
}
*/
