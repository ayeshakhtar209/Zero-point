package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.facebook.drawee.view.SimpleDraweeView;

import org.cfp.citizenconnect.Model.Notifications;
import org.cfp.citizenconnect.R;

public class NotificationLayoutAdapter extends RecyclerView.Adapter<NotificationLayoutAdapter.MyViewHolder> {
    List<Notifications> notificationList;
    Context mContext;
    private static OnItemInteractionListener mListener;
    private LayoutInflater inflater;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public NotificationLayoutAdapter(Context mContext, List<Notifications> snapList, OnItemInteractionListener mListener) {
        this.mContext = mContext;
        this.notificationList = snapList;
        NotificationLayoutAdapter.mListener = mListener;
    }

    public NotificationLayoutAdapter(Context mContext, List<Notifications> snapList) {
        this.notificationList = snapList.subList(0,5);
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case VIEW_PROG:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.progress_bar, parent, false);
                break;
            case VIEW_ITEM:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.notification_layout, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.notification_layout, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.snapHolder.setImageURI(Uri.parse(notificationList.get(position).getFilePath()));
        holder.description.setText(notificationList.get(position).getDescription());
        holder.DateTime.setText(notificationList.get(position).getDate());

//        holder.BtnShare.setOnClickListener(view -> mListener.ShareImageClickListener(position, holder.snapHolder.getDrawable()));
//        holder.view.setOnClickListener(view -> mListener.FullSizeImageClickListener(position,notificationList.get(position).getFilePath(), holder.description.getText().toString()));
     /*   holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.FullSizeImageClickListener(position, notificationList.get(position).getFilePath(), holder.description.getText().toString());
            }
        });*/
        holder.descriptionLayout.setOnClickListener(view -> mListener.FullSizeImageClickListener(position,notificationList.get(position).getFilePath(), holder.description.getText().toString()));
        holder.snapHolder.setOnClickListener(view -> mListener.FullSizeImageClickListener(position,notificationList.get(position).getFilePath(), holder.description.getText().toString()));
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return notificationList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public interface OnItemInteractionListener {
        void ShareImageClickListener(int position, Drawable image);
        void FullSizeImageClickListener(int position, String imagePath, String description);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView snapHolder;
        //TextView BtnShare;
        TextView description;
        TextView DateTime;
        LinearLayout descriptionLayout;
        View view;

        public MyViewHolder(final View itemView) {
            super(itemView);
            snapHolder = itemView.findViewById(R.id.notificationLayoutHolder);
            description = itemView.findViewById(R.id.description);
            DateTime = itemView.findViewById(R.id.DateTime);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            view = itemView.findViewById(R.id.notificationCardView);
            //BtnShare = itemView.findViewById(R.id.BtnShare);

            /*BtnShare.setOnClickListener((View view) -> {
                mListener.ShareImageClickListener(getAdapterPosition(), snapHolder.getDrawable());
            }); */

           /* view.setOnClickListener(view -> mListener.FullSizeImageClickListener(mContext.getAdapterPosition(), notificationList.get(getAdapterPosition()).getFilePath(), description.getText().toString()));
            descriptionLayout.setOnClickListener(view -> mListener.FullSizeImageClickListener(/getAdapterPosition(), notificationList.get(getAdapterPosition()).getFilePath(), description.getText().toString()));
            snapHolder.setOnClickListener(view -> mListener.FullSizeImageClickListener(getAdapterPosition(), notificationList.get(getAdapterPosition()).getFilePath(), description.getText().toString()));
        */
        }
    }
}
