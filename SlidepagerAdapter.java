package com.pipe.www.pipe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pipe.www.pipe.R;
import com.pipe.www.pipe.model.SlideModel;

import java.util.ArrayList;

public class SlidepagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutoinflater;
    ArrayList<SlideModel> slideModels;

    public SlidepagerAdapter(Context context, ArrayList<SlideModel> modelArrayList){
        this.context = context;
        slideModels = modelArrayList;
        layoutoinflater = LayoutInflater.from(context);

    }





    @Override
    public int getCount() {
        return slideModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutoinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutoinflater.inflate(R.layout.slide_item, container, false);

        ImageView slideimage = (ImageView) view.findViewById(R.id.imagemessage);
        TextView title = (TextView)view.findViewById(R.id.slidetitle);
        TextView subtext = (TextView)view.findViewById(R.id.subtext);


        slideimage.setImageResource(slideModels.get(position).getImageres());
        title.setText(slideModels.get(position).getTitle());
        subtext.setText(slideModels.get(position).getSubtext());

        container.addView(view);


        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);

        //super.destroyItem(container, position, object);
    }
}
