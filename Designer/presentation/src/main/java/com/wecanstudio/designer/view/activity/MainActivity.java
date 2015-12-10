package com.wecanstudio.designer.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.wecanstudio.designer.R;
import com.wecanstudio.designer.databinding.ActivityMainBinding;
import com.wecanstudio.designer.databinding.NavHeaderMainBinding;
import com.wecanstudio.designer.view.fragment.FollowFragment;
import com.wecanstudio.designer.view.fragment.MainPageFragment;
import com.wecanstudio.designer.view.fragment.NewsFragment;
import com.wecanstudio.designer.viewModel.MainViewModel;
import com.wecanstudio.designer.viewModel.UserInfoViewModel;


public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment mainPageFragment, followFragment, newsFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModel(new MainViewModel());
        setBinding(DataBindingUtil.<ActivityMainBinding>setContentView(this, R.layout.activity_main));
        getBinding().setMainViewModel(getViewModel());
        initView();
    }

    private void initView() {
        //toolbar设置
        setSupportActionBar(getBinding().toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, getBinding().drawerLayout, getBinding().toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getBinding().drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getBinding().navView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("爱社团");

        //headerView绑定
        NavHeaderMainBinding header = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, null, false);
        getBinding().navView.addHeaderView(header.getRoot());
        header.setUserInfoViewModel(new UserInfoViewModel());

        //默认选中抽屉第一项
        getBinding().navView.setCheckedItem(R.id.nav_mainpage);
        mainPageFragment = new MainPageFragment();
        addFragment(R.id.content, mainPageFragment, "mainPage");

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_mainpage:
                hideFragments(transaction);
                if (mainPageFragment == null) {
                    mainPageFragment = new MainPageFragment();
                    transaction.add(R.id.content, mainPageFragment);
                    currentFragment = mainPageFragment;
                    break;
                }
                transaction.show(mainPageFragment);
                currentFragment = mainPageFragment;
                break;
            case R.id.nav_follow:
                hideFragments(transaction);
                if (followFragment == null) {
                    followFragment = new FollowFragment();
                    transaction.add(R.id.content, followFragment);
                    currentFragment = followFragment;
                }
                transaction.show(followFragment);
                currentFragment = followFragment;
                break;
            case R.id.nav_news:
                hideFragments(transaction);
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment);
                    currentFragment = newsFragment;
                }
                transaction.show(newsFragment);
                currentFragment = newsFragment;
                break;
            case R.id.nav_order:
                break;
            case R.id.nav_theme:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_setting:
                break;
        }
        transaction.commit();
        getBinding().drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mainPageFragment != null)
            transaction.hide(mainPageFragment);
        if (followFragment != null)
            transaction.hide(followFragment);
        if (newsFragment != null)
            transaction.hide(newsFragment);
    }

    @Override
    public void onBackPressed() {
        if (getBinding().drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getBinding().drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (currentFragment != mainPageFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideFragments(transaction);
            if (mainPageFragment == null) {
                mainPageFragment = new MainPageFragment();
                transaction.add(R.id.content, mainPageFragment).commit();
                currentFragment = mainPageFragment;
                getBinding().navView.setCheckedItem(R.id.nav_mainpage);
                return;
            }
            transaction.show(mainPageFragment).commit();
            currentFragment = mainPageFragment;
            getBinding().navView.setCheckedItem(R.id.nav_mainpage);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        menu.findItem(R.id.action_inform).setIcon(R.mipmap.home);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_inform) {
            showMiddleToast("inform");
        }

        return super.onOptionsItemSelected(item);
    }
}
