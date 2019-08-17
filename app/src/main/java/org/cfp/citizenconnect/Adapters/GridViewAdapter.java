package org.cfp.citizenconnect.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.cfp.citizenconnect.Model.Layout;
import org.cfp.citizenconnect.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridViewAdapter extends BaseAdapter {
    Context mContext;
    List<Layout> gridViewList;
    OnItemClickListener mListener;

    public GridViewAdapter(Context mContext, List<Layout> gridViewList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.gridViewList = gridViewList;
        this.mListener = mListener;
    }

    public interface OnItemClickListener {
        void viewDataList(String type);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.tiles_layout, viewGroup, false);
        }
        SimpleDraweeView icons = view.findViewById(R.id.iconHolder);
        TextView title = view.findViewById(R.id.titleGV);
        TextView titleAlphabet = view.findViewById(R.id.titleAlphabet);
        CardView mCardView = view.findViewById(R.id.mainCV);
        LinearLayout background = view.findViewById(R.id.background);
        final Layout layout = (Layout) this.getItem(i);
        icons.setImageURI(Uri.parse(layout.getIcon()));
        title.setText(layout.getName());
        titleAlphabet.setText(layout.getName().substring(0,1));

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.viewDataList(title.getText().toString());
            }
        });
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.circle_img);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

        switch (layout.getName().substring(0, 1)) {
            case "A":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#6640b8"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "B":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#335ca3"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "C":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#731832"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "D":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#731818"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "E":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "F":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "G":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#76de56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "H":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#266347"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "I":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#296326"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "J":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#b8b800"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "K":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#b86200"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "L":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("##fcba03"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "M":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#DE5E56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "N":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#059c1b"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "O":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#76de56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "P":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#76de56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "Q":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#76de56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "R":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#872504"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "S":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#e5e360"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "T":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#116017"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "U":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#ebb3ef"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "V":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#76de56"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "W":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#4f7d96"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "X":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#50683d"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "Y":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#e05131"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case "Z":
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#ffe41c"));
                titleAlphabet.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                break;
            default:
                break;
        }
        return view;
    }
}
