package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.cfp.citizenconnect.Model.Layout;
import org.cfp.citizenconnect.R;

import java.util.List;

public class Services_GridViewAdapter extends BaseAdapter {
    Context mContext;
    List<Layout> gridViewList;
    GridViewAdapter.OnItemClickListener mListener;

    public Services_GridViewAdapter(Context mContext, List<Layout> gridViewList) {
        this.mContext = mContext;
        this.gridViewList = gridViewList;
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return gridViewList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridViewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.services_tiles_layout, viewGroup, false);
        }
        SimpleDraweeView icons = view.findViewById(R.id.iconHolder);
        TextView title = view.findViewById(R.id.titleGV);
        ImageView titleAlphabet = view.findViewById(R.id.titleAlphabet);
        CardView sCardView = view.findViewById(R.id.service_mainCV);
        LinearLayout background = view.findViewById(R.id.background);
        final Layout layout = (Layout) this.getItem(i);
        icons.setImageURI(Uri.parse(layout.getIcon()));
        //background.setBackgroundColor(Color.parseColor(layout.getColor()));
        title.setText(layout.getName());
        sCardView.setOnClickListener(view1 -> mListener.viewDataList(title.getText().toString()));

        Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.circle_img);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        layoutParams.setMargins(10,10,10,10);

        switch (layout.getName()) {
            case "Domicile Certificate":
                titleAlphabet.setImageResource(R.drawable.ic_domicile);
                titleAlphabet.setLayoutParams(layoutParams);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#64c5fa"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "Marriage Registration Certificate":
                titleAlphabet.setImageResource(R.drawable.ic_marriage_reg);
                titleAlphabet.setLayoutParams(layoutParams);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#fc5d53"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "International Driving Permit":
                titleAlphabet.setImageResource(R.drawable.ic_drivers_license);
                titleAlphabet.setLayoutParams(layoutParams);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4cfc65"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);


                /*DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);*/
                break;
        }
        return view;
    }

    public interface OnItemClickListener {
        void viewDataList(String type);
    }
}
