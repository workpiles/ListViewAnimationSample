package com.example.list_remove_anime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SampleListAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;

    public SampleListAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder {
        TextView mText;
        Button mBtn;

        public ViewHolder(View v) {
            mText = (TextView) v.findViewById(R.id.textView);
            mBtn = (Button) v.findViewById(R.id.button);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mText.setText(getItem(position));
        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anime = AnimationUtils.loadAnimation(getContext(), R.anim.list_item_drop);
                anime.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("test", "remove:" + getItem(position));
                        remove(getItem(position));
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(anime);
            }
        });

        return view;
    }
}
