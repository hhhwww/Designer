package com.wecanstudio.designer.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wecanstudio.designer.R;
import com.wecanstudio.designer.databinding.FragmentFollowBinding;
import com.wecanstudio.designer.viewModel.FollowFrViewModel;

/**
 * 收藏
 * Created by xdsjs on 2015/12/10.
 */
public class FollowFragment extends BaseFragment<FollowFrViewModel, FragmentFollowBinding> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setViewModel(new FollowFrViewModel());
        setBinding(DataBindingUtil.<FragmentFollowBinding>inflate(inflater, R.layout.fragment_follow, container, false));
        getBinding().setFollowViewModel(getViewModel());
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
