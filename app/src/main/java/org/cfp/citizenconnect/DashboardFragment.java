package org.cfp.citizenconnect;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;*/

import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.TwitterTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import org.cfp.citizenconnect.Adapters.NotificationLayoutAdapter;
import org.cfp.citizenconnect.Model.NotificationUpdate;
import org.cfp.citizenconnect.Model.Notifications;
import org.cfp.citizenconnect.Notification.FullNewsViewFragment;
import org.cfp.citizenconnect.databinding.NotificationFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.RealmResults;

import static org.cfp.citizenconnect.CitizenConnectApplication.FilesRef;
import static org.cfp.citizenconnect.CitizenConnectApplication.realm;
import static org.cfp.citizenconnect.Model.Notifications.fetchFirebaseNotifications;

public class DashboardFragment extends Fragment implements NotificationLayoutAdapter.OnItemInteractionListener{
    NotificationFragmentBinding binding;
    List<Notifications> notificationsModel = new ArrayList<>();
    NotificationLayoutAdapter notificationListAdapter;
    NotificationUpdate notificationUpdate;
    RecyclerView notifPanel;

    RecyclerView tweetRView;
    TwitterTimelineRecyclerViewAdapter TweetAdapter;

    Button serviceBtn;
    Button dataBtn;
    Button contactBtn;

    TextView expandNotifs;
    TextView expandTwts;

    CustomViewPager view_pager;

    public static DashboardFragment newInstance() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        return dashboardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /*view_pager.disableScroll(true);*/

        serviceBtn = view.findViewById(R.id.servicebttn);
        dataBtn = view.findViewById(R.id.databttn);
        contactBtn = view.findViewById(R.id.contactsbttn);

        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getmViewPager().setCurrentItem(1);
            }
        });
///////////////////////////////////////////////
        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getmViewPager().setCurrentItem(2);
            }
        });
///////////////////////////////////
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getmViewPager().setCurrentItem(3);
            }
        });
//////////////////////////////////////////////////////////////////////////////////

        expandNotifs = view.findViewById(R.id.expand_notifications);

        expandNotifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(), Notification_Activity.class);
                startActivity(a);
            }
        });

        notifPanel = view.findViewById(R.id.notification_dashList);
        LinearLayoutManager notificationList = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        notificationListAdapter = new NotificationLayoutAdapter(getActivity(), notificationsModel,this);
        notifPanel.setLayoutManager(notificationList);

        notificationUpdate = NotificationUpdate.getInstance(realm);
        loadFromRealm();

        notifPanel.setAdapter(notificationListAdapter);

        ////////////////////////////////////////////////////

        expandTwts = view.findViewById(R.id.expand_tweets);
        expandTwts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //it should go to TwitterActivity
                Intent i = new Intent(getActivity(), TwitterActivity.class);
                startActivity(i);
            }
        });

        tweetRView = view.findViewById(R.id.twitter_FeedList);
        LinearLayoutManager tweetList = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        tweetRView.setLayoutManager(tweetList);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("dcislamabad").maxItemsPerRequest(5).build();
//      CollectionTimeline userTimeline = new CollectionTimeline.Builder().id(153032033L).build();
        TweetAdapter = new TwitterTimelineRecyclerViewAdapter.Builder(getActivity()) //getContext()
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightStyle)
                .build();
        tweetRView.setAdapter(TweetAdapter);

        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
//        ((MainActivity) getActivity()).mSearch = this;
    }

    private void loadFromRealm() {
        notificationsModel.clear();
        RealmResults<Notifications> realmResults = realm.where(Notifications.class).findAll();
        if (realmResults.size() != 0) {
            for (Notifications _Notifications : realmResults) {
                notificationsModel.add(_Notifications);
            }
            Collections.reverse(notificationsModel);
                updateRecyclerView();

        } else {
            loadFromFirebase();
        }
    }

    private void loadFromFirebase() {
        //binding.swipeRefreshLayout.setRefreshing(false);
        notificationsModel.clear();
        fetchFirebaseNotifications(FilesRef, (List<Notifications> response) -> {
            notificationsModel = response;
            Collections.reverse(notificationsModel);
            updateRecyclerView();
        }, error -> {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG);
            //progressDialog.dismiss();
        });
    }

    public void updateRecyclerView() {
        //binding.swipeRefreshLayout.setRefreshing(false);
        LinearLayoutManager notificationList = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        notificationListAdapter = new NotificationLayoutAdapter(getActivity(), notificationsModel,this);
        notifPanel.setLayoutManager(notificationList);
        notifPanel.setAdapter(notificationListAdapter);
        //progressDialog.dismiss();
    }

    @Override
    public void ShareImageClickListener(int position, Drawable image) {

    }

    @Override
    public void FullSizeImageClickListener(int position, String imagePath, String description) {
        FullNewsViewFragment fullNewsViewFragment = new FullNewsViewFragment();
        fullNewsViewFragment.setNotifications(notificationsModel);
        fullNewsViewFragment.setPosition(position);
        fullNewsViewFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog_NoTitle);
        fullNewsViewFragment.show(getFragmentManager(), "FullScreenNews");

    }

}
