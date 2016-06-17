package com.myfstrong.mycniao5.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.myfstrong.mycniao5.R;


/**
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends Fragment {

    private SliderLayout mSliderLayout;
    private PagerIndicator indicator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        /*
        * AndroidImageSlider框架应用于头部Banner的展示
        */
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        //将indicator对象与custom_indicator资源绑定
        indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        initSlider();



        return  view;
    }

    public void initSlider() {
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t2416/102/20949846/13425/a3027ebc/55e6d1b9Ne6fd6d8f.jpg")
                .description("新品推荐");
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"新品推荐",Toast.LENGTH_SHORT).show();
            }
        });


        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1507/64/486775407/55927/d72d78cb/558d2fbaNb3c2f349.jpg")
                .description("时尚男装");
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"时尚男装",Toast.LENGTH_SHORT).show();
            }
        });


        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg")
                .description("家电秒杀");
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"家电秒杀",Toast.LENGTH_SHORT).show();
            }
        });

        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);

//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //设置自定义的indicator
        mSliderLayout.setCustomIndicator(indicator);
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("<<<<<<<<<<<<","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("<<<<<<<<<<<<","onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("<<<<<<<<<<<<","onPageScrollStateChanged");
            }
        });
    }
}
