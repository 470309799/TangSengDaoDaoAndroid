// Generated by view binder compiler. Do not edit!
package com.chat.base.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.chat.base.R;
import com.chat.base.views.swipeback.SwipeBackLayout;
import java.lang.NullPointerException;
import java.lang.Override;

public final class WkSwipebackLayoutBinding implements ViewBinding {
  @NonNull
  private final SwipeBackLayout rootView;

  @NonNull
  public final SwipeBackLayout swipe;

  private WkSwipebackLayoutBinding(@NonNull SwipeBackLayout rootView,
      @NonNull SwipeBackLayout swipe) {
    this.rootView = rootView;
    this.swipe = swipe;
  }

  @Override
  @NonNull
  public SwipeBackLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static WkSwipebackLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WkSwipebackLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.wk_swipeback_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WkSwipebackLayoutBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    SwipeBackLayout swipe = (SwipeBackLayout) rootView;

    return new WkSwipebackLayoutBinding((SwipeBackLayout) rootView, swipe);
  }
}
