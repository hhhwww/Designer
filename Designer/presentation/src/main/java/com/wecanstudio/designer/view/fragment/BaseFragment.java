package com.wecanstudio.designer.view.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.wecanstudio.designer.view.widget.SingleToast;
import com.wecanstudio.designer.viewModel.ViewModel;


/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment<VM extends ViewModel, B extends ViewDataBinding> extends Fragment {

	private VM viewModel;
	private B binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mSingleToast = new SingleToast(this.getActivity());
	}

	/**
	 * Shows a {@link Toast} message.
	 *
	 * @param message An string representing a message to be shown.
	 */
	protected void showToastMessage(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}


	public void setViewModel(@NonNull VM viewModel) {
		this.viewModel = viewModel;
	}

	public VM getViewModel() {
		if (viewModel == null) {
			throw new NullPointerException("You should setViewModel first!");
		}
		return viewModel;
	}

	public void setBinding(@NonNull B binding) {
		this.binding = binding;
	}

	public B getBinding() {
		if (binding == null) {
			throw new NullPointerException("You should setBinding first!");
		}
		return binding;
	}

	/**
	 * 通过类名启动Activity
	 *
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 *
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this.getActivity(), pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 显示吐司
	 */
	SingleToast mSingleToast;

	public void showBottomToast(String msg) {
		if (!isResumed() || mSingleToast == null) {
			return;
		}

		mSingleToast.showBottomToast(msg);
	}

	public void showMiddleToast(int id) {
		if (!isResumed() || mSingleToast == null) {
			return;
		}
		mSingleToast.showMiddleToast(id);
	}

	public void showMiddleToast(String msg) {
		if (!isResumed() || mSingleToast == null) {
			return;
		}
		mSingleToast.showMiddleToast(msg);
	}

	public void showBottomToast(int messageId) {
		if (!isResumed() || mSingleToast == null) {
			return;
		}

		mSingleToast.showBottomToast(messageId);
	}
}
