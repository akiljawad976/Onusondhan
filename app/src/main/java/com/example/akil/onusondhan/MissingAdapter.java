package com.example.akil.onusondhan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class MissingAdapter extends ArrayAdapter<Missing> {
    private Activity context;
    private List<Missing> listima;

    public MissingAdapter(@NonNull Activity context, List<Missing> objects) {
        super(context, R.layout.item_missing_list, objects);
        this.context = context;
        listima = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInFlaterr = context.getLayoutInflater();
        View vi = layoutInFlaterr.inflate(R.layout.item_missing_list,null,true);
        TextView tvNam = (TextView) vi.findViewById(R.id.imageNam);
        ImageView im = (ImageView) vi.findViewById(R.id.imageVie);

        Missing missing = listima.get(position);
        Uri temp = Uri.parse(missing.getPath());
        String url = missing.getPath();
        final String missingName=missing.getMissingName();
        final String missingHeight=missing.getHght();
        final String missingWeight=missing.getWght();
        final int missingAge=missing.getAge();
        final String missingDate=missing.getFnddat();
        final String missingPlace=missing.getFndplac();
        final String missingDesc=missing.getDesc();
        final String missingColor=missing.getSkcolor();
        final String missingNumb=missing.getNumbr();
        final String missingMark=missing.getMark();

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),MissingPerson.class);
                i.putExtra("Name",missingName);
                i.putExtra("Age",missingAge);
                i.putExtra("Height",missingHeight);
                i.putExtra("Weight",missingWeight);
                i.putExtra("Desc",missingDesc);
                i.putExtra("Place",missingPlace);
                i.putExtra("Date",missingDate);
                i.putExtra("Mark",missingMark);
                i.putExtra("Skin",missingColor);
                i.putExtra("Numb",missingNumb);
                getContext().startActivity(i);
            }
        });

        tvNam.setText(missing.getMissingName());
        //Glide.with(context).load(listimage.get(position).getPath()).into(img);
        Glide.with(getContext()).load(url).into(im);


        return vi;
    }
}
