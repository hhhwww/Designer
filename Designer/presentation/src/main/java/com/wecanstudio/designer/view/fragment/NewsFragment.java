package com.wecanstudio.designer.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wecanstudio.designer.R;
import com.wecanstudio.designer.databinding.FragmentNewsBinding;
import com.wecanstudio.designer.viewModel.NewsFrViewModel;

/**
 * Created by xdsjs on 2015/12/10.
 */
public class NewsFragment extends BaseFragment<NewsFrViewModel,FragmentNewsBinding>{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setViewModel(new NewsFrViewModel());
        setBinding(DataBindingUtil.<FragmentNewsBinding>inflate(inflater, R.layout.fragment_news, container, false));
        getBinding().setNewsViewModel(getViewModel());
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
