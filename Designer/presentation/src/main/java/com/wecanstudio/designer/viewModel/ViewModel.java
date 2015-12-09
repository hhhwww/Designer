package com.wecanstudio.designer.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.wecanstudio.designer.MyApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xdsjs on 2015/12/9.
 */
public class ViewModel extends BaseObservable {

    protected Context appContext = MyApplication.getContext();

    /* Just mark a method in ViewModel */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    protected @interface Command {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    protected @interface BindView {
    }
    // ... InstanceState in ViewModel
}
