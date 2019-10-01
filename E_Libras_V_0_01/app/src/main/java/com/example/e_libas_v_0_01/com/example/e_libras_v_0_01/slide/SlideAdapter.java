package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.slide;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.e_libas_v_0_01.R;

public class SlideAdapter extends PagerAdapter
{
    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context)
    {
        this.context = context;
    }

    //Criando os arrays

    public  int[] slide_images =
            {
                    R.drawable.icones,
                    R.drawable.iconeteste,
                    R.drawable.iconecontato

            };

    public String[] slide_title =
            {
                    "Mussun",
                    "Mussun",
                    "Mussun"
            };

    public String[] slide_desc =
            {
                  "Mussum Ipsum, cacilds vidis litro abertis. Nec orci ornare consequat." +
                          "Praesent lacinia ultrices consectetur. Sed non ipsum felis." +
                          "Nullam volutpat.",

                    "Mussum Ipsum, cacilds vidis litro abertis. Nec orci ornare consequat. Praesent lacinia ultrices consectetur."+
                            "Sed non ipsum felis. Nullam volutpat risus nec leo commodo,",

                    "Mussum Ipsum, cacilds vidis litro abertis. Nec orci ornare consequat. Praesent lacinia ultrices consectetur." +
                            "Sed non ipsum felis. Nullam volutpat risus nec leo commodo, ut interdum diam laoreet."


            };


    @Override
    public int getCount()
    {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o)
    {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
       layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view = layoutInflater.inflate(R.layout.slidelayout_01,container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageviewslide);
        TextView slidetitulo = (TextView) view.findViewById(R.id.txtlayouttitulo);
        TextView slideTexto = (TextView) view.findViewById(R.id.txtlayouttexto);

        slideImageView.setImageResource(slide_images[position]);
        slidetitulo.setText(slide_title[position]);
        slideTexto.setText(slide_desc[position]);

        container.addView(view);

       return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((LinearLayout)object);
    }
}
