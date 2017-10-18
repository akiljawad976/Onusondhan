package com.example.akil.onusondhan;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Valociraptor on 9/25/2017.
 */

public class FoundAdapter extends ArrayAdapter<Found> {
    private Activity context;
    private List<Found> listimage;

    public FoundAdapter(@NonNull Activity context, List<Found> objects) {
        super(context, R.layout.item_found_list, objects);
        this.context = context;
        listimage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInFlater = context.getLayoutInflater();
        View v = layoutInFlater.inflate(R.layout.item_found_list,null,true);
        TextView tvName = (TextView) v.findViewById(R.id.imageName);
        TextView tvAge = (TextView) v.findViewById(R.id.imageAge);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);

        Found found = listimage.get(position);
        Uri temp = Uri.parse(found.getPath());
        String url = found.getPath();

        tvName.setText(found.getFoundName());
        //Glide.with(context).load(listimage.get(position).getPath()).into(img);
        Glide.with(getContext()).load(url).into(img);


        return v;
    }
}
