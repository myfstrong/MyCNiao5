package com.myfstrong.mycniao5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.myfstrong.mycniao5.bean.Tab;
import com.myfstrong.mycniao5.fragment.CartFragment;
import com.myfstrong.mycniao5.fragment.CategoryFragment;
import com.myfstrong.mycniao5.fragment.HomeFragment;
import com.myfstrong.mycniao5.fragment.HotFragment;
import com.myfstrong.mycniao5.fragment.MineFragment;
import com.myfstrong.mycniao5.weiget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<Tab>(5);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化FragmentTabHost，及其中的FragmentTab
        initTab();

    }

    /*
     * 创建FragmentTabHost，及其中的FragmentTab
     */
    private void initTab() {
        Tab tab_home = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab tab_hot = new Tab(R.string.hot,R.drawable.selector_icon_hot, HotFragment.class);
        Tab tab_category = new Tab(R.string.catagory,R.drawable.selector_icon_category, CategoryFragment.class);
        Tab tab_cart = new Tab(R.string.cart,R.drawable.selector_icon_cart, CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine,R.drawable.selector_icon_mine, MineFragment.class);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        //step 1:将FragmentTabHost对象与XML文件中的对应FragmentTabHost资源绑定
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //step 2:调用setup()方法
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        //step 3:以for循环的方式，创建多个TabSpec对象，命名，绑定资源控件id，设置资源控件的内容
        mInflater = LayoutInflater.from(this);
        for (Tab tab :mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));
            //step 4:添加一个TabSpec对象到FragmentTabHost对象中
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }
        //去掉各个TabSpec之间的分割线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //默认选中第一个TabSpec
        mTabHost.setCurrentTab(0);
    }

    /*
    * 在TabSpec中设置它的Indicator
    */
    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView txt = (TextView) view.findViewById(R.id.txt_indicator);

        img.setImageResource(tab.getIcon());
        txt.setText(tab.getTitle());
        return view;
    }
}
