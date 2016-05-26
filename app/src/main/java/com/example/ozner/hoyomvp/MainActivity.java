package com.example.ozner.hoyomvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.ozner.hoyomvp.Bean.FagmentListener;
import com.example.ozner.hoyomvp.Home.HomeFragment;
import com.example.ozner.hoyomvp.Manager.ManagerFragment;
import com.example.ozner.hoyomvp.Message.MessageFragment;
import com.example.ozner.hoyomvp.MyCenter.CenterFragment;
import com.githang.viewpagerindicator.IconPagerAdapter;
import com.githang.viewpagerindicator.IconTabPageIndicator;

import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements FagmentListener {

    IconTabPageIndicator mIndicator;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
//        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        List<BaseFragment> fragments = initFraments();
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
    }

    //初始化页面列表
    private List<BaseFragment> initFraments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment homeFragment = new HomeFragment();
        homeFragment.setTitle("首页");
        homeFragment.setIconId(R.drawable.guide_home_selector);
        fragments.add(homeFragment);

        BaseFragment managerFragment = new ManagerFragment();
        managerFragment.setTitle("管理");
        managerFragment.setIconId(R.drawable.guide_manager_selector);
        fragments.add(managerFragment);

        BaseFragment messageFragment = new MessageFragment();
        messageFragment.setTitle("消息");
        messageFragment.setIconId(R.drawable.guide_msg_selector);
        fragments.add(messageFragment);

        BaseFragment centerFragment = new CenterFragment();
        centerFragment.setTitle("我的");
        centerFragment.setIconId(R.drawable.guide_my_selector);
        fragments.add(centerFragment);

        return fragments;
    }

    @Override
    public void finishAcitity() {
        finishAll();
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<BaseFragment> mFragments;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }
    }
}
