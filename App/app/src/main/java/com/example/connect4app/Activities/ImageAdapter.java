package com.example.connect4app.Activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.connect4app.R;

public class ImageAdapter extends BaseAdapter {

    static int totalSize;
    private Context mcontext;

    public ImageAdapter(Context c) {
        mcontext = c;
    }

    @Override
    public int getCount() {
        return totalSize;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if (convertView == null) {

            switch (totalSize) {
                case (25):
                    imageView = new ImageView(mcontext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 65));
                    break;

                case (36):
                    imageView = new ImageView(mcontext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70));
                    break;

                case (49):
                    imageView = new ImageView(mcontext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 72));
                    break;
            }
        
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setBackgroundResource(grill[position]);
        return imageView;
    }

    public static void setData(int size) {
        totalSize = size;
    }

    private Integer[] grill = {
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
            R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela, R.drawable.cela,
    };
}
