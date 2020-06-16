package com.example.connect4app.Activities.Game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.connect4app.R;

public class ImageAdapterInteractive extends BaseAdapter {

    public static int fitxaSize;
    public static int width = 0;
    static int totalSize;
    private Context mcontext;

    public ImageAdapterInteractive(Context c){
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
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mcontext);
            imageView.setPadding(0,0,0,0);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT+(fitxaSize*2)+width,70+fitxaSize));

        }else {
            imageView = (ImageView) convertView;
        }
        imageView.setBackgroundResource(R.drawable.selectred);
        return imageView;
    }

    public static void setData(int size){
        totalSize = size;
    }
}
