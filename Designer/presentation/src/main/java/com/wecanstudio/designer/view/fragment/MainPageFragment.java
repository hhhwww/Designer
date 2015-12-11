package com.wecanstudio.designer.view.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;

import com.wecanstudio.designer.R;
import com.wecanstudio.designer.databinding.FragmentMainpageBinding;
import com.wecanstudio.designer.viewModel.MainPageFrViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 首页
 * Created by xdsjs on 2015/12/10.
 */
public class MainPageFragment extends BaseFragment<MainPageFrViewModel, FragmentMainpageBinding> implements SwipeRefreshLayout.OnRefreshListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("--------------->", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("--------------->", "onCreateView");
        setViewModel(new MainPageFrViewModel());
        setBinding(DataBindingUtil.<FragmentMainpageBinding>inflate(inflater, R.layout.fragment_mainpage, container, false));
        getBinding().setMainPageViewModel(getViewModel());

        return getBinding().getRoot();
    }

    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json",
            "HTML"));

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("--------------->", "onViewCreated");
        getBinding().refresh.setOnRefreshListener(this);
        getBinding().refresh.setColorSchemeColors(Color.parseColor("#9C27B0"));
        mAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, mDatas);
        getBinding().idListview.setAdapter(mAdapter);

        //保证切换界面后刷新
//        getBinding().refresh.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getBinding().refresh.setRefreshing(true);
//            }
//        }, 100);

        getBinding().refresh.setChildView(getBinding().idListview);

        getBinding().refresh.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                Log.e("--------------->", "onGlobalLayout");
                getBinding().refresh.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                getBinding().refresh.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
                    mAdapter.notifyDataSetChanged();
                    getBinding().refresh.setRefreshing(false);
                    break;
            }
        }
    };

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        Log.e("-------------->", "onRefresh");
//        getBinding().refresh.setRefreshing(true);
        handler.sendEmptyMessageDelayed(1, 3000);
    }
}
