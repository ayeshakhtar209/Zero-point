package org.cfp.citizenconnect;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.cfp.citizenconnect.Adapters.FullNotificationLayoutAdapter;
import org.cfp.citizenconnect.Interfaces.ScrollStatus;
import org.cfp.citizenconnect.Interfaces.Search;
import org.cfp.citizenconnect.Model.NotificationUpdate;
import org.cfp.citizenconnect.Model.Notifications;
import org.cfp.citizenconnect.databinding.NotificationFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Case;
import io.realm.RealmResults;

import static org.cfp.citizenconnect.CitizenConnectApplication.FilesRef;
import static org.cfp.citizenconnect.CitizenConnectApplication.realm;
import static org.cfp.citizenconnect.Model.Notifications.fetchFirebaseNotifications;

public class Notification_Activity extends AppCompatActivity implements ScrollStatus{

    NotificationFragmentBinding binding;
    List<Notifications> notificationsModel = new ArrayList<>();
    FullNotificationLayoutAdapter fullnotificationListAdapter;
    ProgressDialog progressDialog;
    ScrollStatus mScrollStatus;
    NotificationUpdate notificationUpdate;
    private BroadcastReceiver mNotificationReceiver;
    SearchView searchView;
    MenuItem searchMenu;
    MenuItem menuItem;
    public Search mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.notification_fragment);

        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.in_progress_msg));
        progressDialog.show();
        notificationUpdate = NotificationUpdate.getInstance(realm);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                loadFromFirebase();
                if (fullnotificationListAdapter != null && notificationsModel != null) {
                    notificationsModel.clear();
                    fullnotificationListAdapter.notifyDataSetChanged();
                }
            }, 500);

        });
        if (notificationUpdate.getNewNotification() > 0 || !notificationUpdate.isLastStateRead()) {
            loadFromFirebase();
        } else {
            loadFromRealm();
        }

        binding.notificationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    OnScrollStatusChanged(true);
                }
            }
        });

        FilesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                loadFromFirebase();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        View view = binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(
                "android.intent.action.MAIN");

        mNotificationReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                boolean update = intent.getBooleanExtra("newUpdate", false);
                if (update) {
                    loadFromFirebase();
                }
            }
        };
        this.registerReceiver(mNotificationReceiver, intentFilter);
    }

    @Override
    public void OnScrollStatusChanged(boolean status) {
        if (status) {
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.
                            NOTIFICATION_SERVICE);
            notificationManager.cancel(Integer.parseInt(getString(R.string.ICT_NOTIFICATION_ID)));
            realm.executeTransaction(realm -> {
                notificationUpdate.setLastStateRead(true);
                notificationUpdate.setNewNotification(0);
            });
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mNotificationReceiver);
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
        notificationsModel.clear();
        fetchFirebaseNotifications(FilesRef, (List<Notifications> response) -> {
            notificationsModel = response;
            Collections.reverse(notificationsModel);
            updateRecyclerView();
        }, error -> {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG);
            progressDialog.dismiss();
        });
    }

    public void OnSearchNotification(String query) {
        notificationsModel.clear();
        RealmResults<Notifications> realmResults = realm.where(Notifications.class).contains("description", query, Case.INSENSITIVE).findAll();
        for (Notifications _Notifications : realmResults) {
            notificationsModel.add(_Notifications);
        }
        Collections.reverse(notificationsModel);
        LinearLayoutManager notificationList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fullnotificationListAdapter = new FullNotificationLayoutAdapter(this, notificationsModel);
        binding.notificationList.setLayoutManager(notificationList);
        binding.notificationList.setAdapter(fullnotificationListAdapter);
    }

    public void updateRecyclerView() {
        binding.swipeRefreshLayout.setRefreshing(false);
        LinearLayoutManager notificationList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fullnotificationListAdapter = new FullNotificationLayoutAdapter(this, notificationsModel);
        binding.notificationList.setLayoutManager(notificationList);
        binding.notificationList.setAdapter(fullnotificationListAdapter);
        progressDialog.dismiss();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notification_menu, menu);
        searchMenu = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(false);
        searchView.setOnCloseListener(() -> {
            searchView.clearFocus();
            if (menuItem != null) {
                menuItem.collapseActionView();
            }
            return true;
        });
        searchView.setOnSearchClickListener(view -> {
                setItemsVisibility(menu, searchMenu, false);
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearch.OnSearchNotification(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mSearch.OnSearchNotification(query);
                return true;
            }
        });
        return true;
    }
    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
}
