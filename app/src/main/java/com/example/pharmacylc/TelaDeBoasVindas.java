package com.example.pharmacylc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TelaDeBoasVindas extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;

    Button btn;
    TelaAdaptativa telaAdaptativa;

    TextView[] dots;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tela_de_boas_vindas);


        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        btn = findViewById(R.id.get_started_btn);

        addDots(0);

        viewPager.addOnPageChangeListener(changeListener);


        //call Adapter
        telaAdaptativa = new TelaAdaptativa(this);
        viewPager.setAdapter(telaAdaptativa);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaDeBoasVindas.this, CadastroActivity.class));
                finish();
            }
        });

    }
    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.teal_700));
        }
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);

            if (position == 0) {
                btn.setVisibility(View.INVISIBLE);
            } else if(position == 1){
                btn.setVisibility(View.INVISIBLE);
            }else{
                btn.setVisibility(View.VISIBLE);
                animation = AnimationUtils.loadAnimation(TelaDeBoasVindas.this, R.anim.slide_animation);
                btn.setAnimation(animation);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}