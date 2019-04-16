package com.twitter.sdk.android.tweetui;


import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetBuilder;

/**
 * TwitterTimelineRecyclerViewAdapter is a RecyclerView adapter which can provide Timeline Tweets to
 * RecyclerViews.
 */
public class TwitterTimelineRecyclerViewAdapter extends
        RecyclerView.Adapter<TwitterTimelineRecyclerViewAdapter.TweetViewHolder> {

    protected final Context context;
    protected final TimelineDelegate<Tweet> timelineDelegate;
    protected Callback<Tweet> actionCallback;
    protected final int styleResId;
    protected TweetUi tweetUi;
    private int previousCount;

    /**
     * Constructs a TwitterTimelineRecyclerViewAdapter for a RecyclerView implementation of a timeline
     *
     * @param context the context for row views.
     * @param timeline a Timeline&lt;Tweet&gt; providing access to Tweet data items.
     * @throws java.lang.IllegalArgumentException if context is null
     */
    public TwitterTimelineRecyclerViewAdapter(Context context, Timeline<Tweet> timeline) {
        this(context, timeline, R.style.tw__TweetLightStyle, null);
    }

    protected TwitterTimelineRecyclerViewAdapter(Context context, Timeline<Tweet> timeline,
                                               int styleResId, Callback<Tweet> cb) {
        this(context, new TimelineDelegate<>(timeline), styleResId, cb, TweetUi.getInstance());
    }

    TwitterTimelineRecyclerViewAdapter(Context context, TimelineDelegate<Tweet> timelineDelegate,
                                     int styleResId, Callback<Tweet> cb, TweetUi tweetUi) {
        this(context, timelineDelegate, styleResId);
        actionCallback = new ReplaceTweetCallback(timelineDelegate, cb);
        this.tweetUi = tweetUi;
    }

    TwitterTimelineRecyclerViewAdapter(Context context,
                                     final TimelineDelegate<Tweet> timelineDelegate,
                                     int styleResId) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.context = context;
        this.timelineDelegate = timelineDelegate;
        this.styleResId = styleResId;

        this.timelineDelegate.refresh(new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                notifyDataSetChanged();
                previousCount = TwitterTimelineRecyclerViewAdapter.this.timelineDelegate.getCount();
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });

        final DataSetObserver dataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (previousCount == 0) {
                    notifyDataSetChanged();
                } else {
                    notifyItemRangeInserted(previousCount,
                            TwitterTimelineRecyclerViewAdapter.this.timelineDelegate.getCount()
                                    - previousCount);
                }
                previousCount = TwitterTimelineRecyclerViewAdapter.this.timelineDelegate.getCount();
            }

            @Override
            public void onInvalidated() {
                notifyDataSetChanged();
                super.onInvalidated();
            }
        };

        this.timelineDelegate.registerDataSetObserver(dataSetObserver);
    }

    public void refresh(Callback<TimelineResult<Tweet>> cb) {
        timelineDelegate.refresh(cb);
        previousCount = 0;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Tweet tweet = new TweetBuilder().build();
        final CompactTwitterView compactTwitterView = new CompactTwitterView(context, tweet, styleResId);
        compactTwitterView.setOnActionCallback(actionCallback);
        return new TweetViewHolder(compactTwitterView);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        final Tweet tweet = timelineDelegate.getItem(position);
        final CompactTwitterView compactTwitterView = (CompactTwitterView) holder.itemView;
        compactTwitterView.setTweet(tweet);
    }

    @Override
    public int getItemCount() {
        return timelineDelegate.getCount();
    }

    public static final class TweetViewHolder extends RecyclerView.ViewHolder {
        public TweetViewHolder(CompactTwitterView itemView) {
            super(itemView);
        }
    }

    /*
     * On success, sets the updated Tweet in the TimelineDelegate to replace any old copies
     * of the same Tweet by id.
     */
    static class ReplaceTweetCallback extends Callback<Tweet> {
        TimelineDelegate<Tweet> delegate;
        Callback<Tweet> cb;

        ReplaceTweetCallback(TimelineDelegate<Tweet> delegate, Callback<Tweet> cb) {
            this.delegate = delegate;
            this.cb = cb;
        }

        @Override
        public void success(Result<Tweet> result) {
            delegate.setItemById(result.data);
            if (cb != null) {
                cb.success(result);
            }
        }

        @Override
        public void failure(TwitterException exception) {
            if (cb != null) {
                cb.failure(exception);
            }
        }
    }

    /**
     * TwitterTimelineRecyclerViewAdapter Builder
     */
    public static class Builder {
        private Context context;
        private Timeline<Tweet> timeline;
        private Callback<Tweet> actionCallback;
        private TimelineFilter timelineFilter;
        private int styleResId = R.style.tw__TweetLightStyle;

        /**
         * Constructs a Builder.
         * @param context Context for Tweet views.
         */
        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Sets the Tweet timeline data source.
         * @param timeline Timeline of Tweets
         */
        public TwitterTimelineRecyclerViewAdapter.Builder setTimeline(Timeline<Tweet> timeline) {
            this.timeline = timeline;
            return this;
        }

        /**
         * Sets the Tweet view style by resource id.
         * @param styleResId resource id of the Tweet view style
         */
        public TwitterTimelineRecyclerViewAdapter.Builder setViewStyle(int styleResId) {
            this.styleResId = styleResId;
            return this;
        }

        /**
         * Sets the callback to call when a Tweet action is performed on a Tweet view.
         * @param actionCallback called when a Tweet action is performed.
         */
        public TwitterTimelineRecyclerViewAdapter.Builder setOnActionCallback(
                Callback<Tweet> actionCallback) {
            this.actionCallback = actionCallback;
            return this;
        }

        /**
         * Sets the TimelineFilter used to filter tweets from timeline.
         * @param timelineFilter timelineFilter for timeline
         */
        public TwitterTimelineRecyclerViewAdapter.Builder setTimelineFilter(
                TimelineFilter timelineFilter) {
            this.timelineFilter = timelineFilter;
            return this;
        }

        /**
         * Builds a TwitterTimelineRecyclerViewAdapter from Builder parameters.
         * @return a TweetTimelineListAdpater
         */
        public TwitterTimelineRecyclerViewAdapter build() {
            if (timelineFilter == null) {
                return new TwitterTimelineRecyclerViewAdapter(context, timeline, styleResId,
                        actionCallback);
            } else {
                final FilterTimelineDelegate delegate = new FilterTimelineDelegate(timeline,
                        timelineFilter);
                return new TwitterTimelineRecyclerViewAdapter(context, delegate, styleResId,
                        actionCallback, TweetUi.getInstance());
            }
        }
    }
}