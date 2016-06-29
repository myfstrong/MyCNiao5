package com.myfstrong.mycniao5.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.myfstrong.mycniao5.adapter.HomeCategoryAdapter;
import com.myfstrong.mycniao5.bean.Banner;
import com.myfstrong.mycniao5.bean.HomeCategory;
import com.myfstrong.mycniao5.http.BaseCallBack;
import com.myfstrong.mycniao5.http.OkHttpHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends Fragment {

    private SliderLayout mSliderLayout;
    private PagerIndicator indicator;
    private RecyclerView mRecyclerView;
    private HomeCategoryAdapter mAdapter;
    private List<Banner> mBanner;

    private OkHttpHelper mOkHttpHelper = OkHttpHelper.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        /*
        * AndroidImageSlider框架应用于头部Banner的展示
        */
        initSlider(view);

        /*
        * RecycleView
        */
        initRecyclerView(view);


        return  view;
    }

    public void doRequest() {
        String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";

        mOkHttpHelper.get(url, new BaseCallBack<List<Banner>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onSuccess(Response response, List<Banner> banners) {

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        }
    }

    public void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        List<HomeCategory> datas = new ArrayList<HomeCategory>(15);

        HomeCategory category = new HomeCategory("热门活动",R.drawable.img_big_1,R.drawable.img_1_small1,R.drawable.img_1_small2);
        datas.add(category);

        category = new HomeCategory("有利可图",R.drawable.img_big_4,R.drawable.img_4_small1,R.drawable.img_4_small2);
        datas.add(category);

        category = new HomeCategory("品牌街",R.drawable.img_big_2,R.drawable.img_2_small1,R.drawable.img_2_small2);
        datas.add(category);

        category = new HomeCategory("金融街 包赚翻",R.drawable.img_big_1,R.drawable.img_3_small1,R.drawable.imag_3_small2);
        datas.add(category);

        category = new HomeCategory("超值购",R.drawable.img_big_0,R.drawable.img_0_small1,R.drawable.img_0_small2);
        datas.add(category);

        mAdapter = new HomeCategoryAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    public void initSlider(View view) {
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        //将indicator对象与custom_indicator资源绑定
        indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);

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
