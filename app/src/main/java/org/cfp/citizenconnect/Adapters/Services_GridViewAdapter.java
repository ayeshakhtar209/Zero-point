package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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

        switch (layout.getName().substring(0, 1)) {
            case "A":
                titleAlphabet.setBackgroundColor(Color.parseColor("#DE5E56"));
                break;
            case "B":
                titleAlphabet.setBackgroundColor(Color.parseColor("#fff34c"));
                break;
            case "C":
                titleAlphabet.setBackgroundColor(Color.parseColor("#60f945"));
                break;
            case "D":
                titleAlphabet.setBackgroundColor(Color.parseColor("#40edd8"));
                break;
            case "E":
                titleAlphabet.setBackgroundColor(Color.parseColor("#f75d16"));
                break;
            case "F":
                titleAlphabet.setBackgroundColor(Color.parseColor("#54a51a"));
                break;
            case "G":
                titleAlphabet.setBackgroundColor(Color.parseColor("#cdbbf9"));
                break;
            case "H":
                titleAlphabet.setBackgroundColor(Color.parseColor("#6b2987"));
                break;
            case "I":
                titleAlphabet.setBackgroundColor(Color.parseColor("#d622e2"));
                break;
            case "J":
                titleAlphabet.setBackgroundColor(Color.parseColor("#3da0e2"));
                break;
            case "K":
                titleAlphabet.setBackgroundColor(Color.parseColor("#499b4f"));
                break;
            case "L":
                titleAlphabet.setBackgroundColor(Color.parseColor("#4f7d96"));
                break;
            case "M":
                titleAlphabet.setBackgroundColor(Color.parseColor("#fcd1c2"));
                break;
            case "N":
                titleAlphabet.setBackgroundColor(Color.parseColor("#bac1ba"));
                break;
            case "O":
                titleAlphabet.setBackgroundColor(Color.parseColor("#7944ff"));
                break;
            case "P":
                titleAlphabet.setBackgroundColor(Color.parseColor("#996fe2"));
                break;
            case "Q":
                titleAlphabet.setBackgroundColor(Color.parseColor("#60e5a0"));
                break;
            case "R":
                titleAlphabet.setBackgroundColor(Color.parseColor("#872504"));
                break;
            case "S":
                titleAlphabet.setBackgroundColor(Color.parseColor("#e5e360"));
                break;
            case "T":
                titleAlphabet.setBackgroundColor(Color.parseColor("#116017"));
                break;
            case "U":
                titleAlphabet.setBackgroundColor(Color.parseColor("#ebb3ef"));
                break;
            case "V":
                titleAlphabet.setBackgroundColor(Color.parseColor("#626d62"));
                break;
            case "W":
                titleAlphabet.setBackgroundColor(Color.parseColor("#4f7d96"));
                break;
            case "X":
                titleAlphabet.setBackgroundColor(Color.parseColor("#50683d"));
                break;
            case "Y":
                titleAlphabet.setBackgroundColor(Color.parseColor("#e05131"));
                break;
            case "Z":
                titleAlphabet.setBackgroundColor(Color.parseColor("#ffe41c"));
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
