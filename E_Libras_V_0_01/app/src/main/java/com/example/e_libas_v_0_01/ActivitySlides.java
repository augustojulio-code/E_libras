package com.example.e_libas_v_0_01;

import android.app.slice.SliceItem;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.slide.SlideAdapter;

public class ActivitySlides extends AppCompatActivity implements View.OnClickListener
{

    private ViewPager mviewPager;
    private LinearLayout mlinearLayout;

    private TextView[] mdots;

    private SlideAdapter slideAdapter;

    private Button mProximo, mVoltar;

    private int paginaAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);

        mviewPager = (ViewPager) findViewById(R.id.slideview);
        mlinearLayout = (LinearLayout) findViewById(R.id.dotlayout);

        mProximo = (Button) findViewById(R.id.btnproximoslide);
        mVoltar = (Button) findViewById(R.id.btnvoltarslide);

        slideAdapter = new SlideAdapter(this);

        mviewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mviewPager.addOnPageChangeListener(viewlistener);


        mProximo.setOnClickListener(this);
        mVoltar.setOnClickListener(this);
    }

    public void addDotsIndicator(int position)
    {
        mdots = new  TextView[3];
        mlinearLayout.removeAllViews();

        for (int i = 0; i< mdots.length; i++)
        {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorAccent));

            mlinearLayout.addView(mdots[i]);
        }

        if (mdots.length > 0)
        {
            mdots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int i, float v, int i1)
        {

        }

        @Override
        public void onPageSelected(int i)
        {
            addDotsIndicator(i);
            paginaAtual = i;

            if (i == 0)
        {
            mProximo.setEnabled(true);
            mVoltar.setEnabled(false);
            mVoltar.setVisibility(View.INVISIBLE);
            mProximo.setText("Próximo");
            mVoltar.setText("");

        }
        else if ( i == mdots.length -1 )
        {
            mProximo.setEnabled(true);
            mVoltar.setEnabled(true);
            mVoltar.setVisibility(View.VISIBLE);

            mProximo.setText("Terminar");
            mVoltar.setText("  Voltar ");

        }


        else
        {
            mProximo.setEnabled(true);
            mVoltar.setEnabled(true);
            mVoltar.setVisibility(View.VISIBLE);

            mProximo.setText("Próximo");
            mVoltar.setText(" Voltar ");

        }


        }

        @Override
        public void onPageScrollStateChanged(int i)
        {

        }
    };

    @Override
    public void onClick(View view)
    {
        if (view == mProximo)
        {
            String verificar = mProximo.getText().toString();

            if (verificar.equals("Terminar"))
            {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
            else
            {
                MudarPagina();
            }

        }
        if (view == mVoltar)
        {
            voltaPagina();
        }
    }


    private void voltaPagina()
    {
        mviewPager.setCurrentItem(paginaAtual -1);


    }

    private void MudarPagina()
    {
        mviewPager.setCurrentItem(paginaAtual +1);


    }

}
