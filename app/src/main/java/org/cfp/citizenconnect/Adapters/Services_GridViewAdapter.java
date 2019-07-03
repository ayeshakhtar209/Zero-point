package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.cfp.citizenconnect.Model.Layout;
import org.cfp.citizenconnect.R;

import java.util.List;

public class Services_GridViewAdapter extends BaseAdapter {
    Context mContext;
    List<Layout> gridViewList;
    GridViewAdapter.OnItemClickListener mListener;

    public Services_GridViewAdapter(Context mContext, List<Layout> gridViewList, GridViewAdapter.OnItemClickListener mListener) {
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
        TextView titleAlphabet = view.findViewById(R.id.titleAlphabet);
        CardView mCardView = view.findViewById(R.id.mainCV);
        LinearLayout background = view.findViewById(R.id.background);
        final Layout layout = (Layout) this.getItem(i);
        icons.setImageURI(Uri.parse(layout.getIcon()));
        //background.setBackgroundColor(Color.parseColor(layout.getColor()));
        title.setText(layout.getName());
        titleAlphabet.setText(layout.getName().substring(0,1));
        mCardView.setOnClickListener(view1 -> mListener.viewDataList(title.getText().toString()));

        Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.circle_shape);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

        switch (layout.getName().substring(0, 1)) {
            case "A":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#DE5E56"));
                break;
            case "B":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#fff34c"));
                break;
            case "C":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#60f945"));
                break;
            case "D":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#40edd8"));
                break;
            case "E":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#f75d16"));
                break;
            case "F":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#54a51a"));
                break;
            case "G":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#cdbbf9"));
                break;
            case "H":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#6b2987"));
                break;
            case "I":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#d622e2"));
                break;
            case "J":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#3da0e2"));
                break;
            case "K":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#499b4f"));;
                break;
            case "L":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                break;
            case "M":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#fcd1c2"));
                break;
            case "N":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#bac1ba"));

                break;
            case "O":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#7944ff"));
                break;
            case "P":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#996fe2"));
                break;
            case "Q":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#60e5a0"));
                break;
            case "R":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#872504"));
                break;
            case "S":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#e5e360"));
                break;
            case "T":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#116017"));
                break;
            case "U":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#ebb3ef"));
                break;
            case "V":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#DE5E56"));
                break;
            case "W":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                break;
            case "X":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#50683d"));
                break;
            case "Y":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#e05131"));
                break;
            case "Z":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#ffe41c"));
                break;
            default:
                break;
        }

        return view;
    }

    public interface OnItemClickListener {
        void viewDataList(String type);
    }
}
