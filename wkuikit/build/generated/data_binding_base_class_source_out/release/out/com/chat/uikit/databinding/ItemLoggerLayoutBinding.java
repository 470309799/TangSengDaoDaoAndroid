// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemLoggerLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout contentLayout;

  @NonNull
  public final TextView nameTv;

  @NonNull
  public final TextView sizeTv;

  private ItemLoggerLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout contentLayout, @NonNull TextView nameTv, @NonNull TextView sizeTv) {
    this.rootView = rootView;
    this.contentLayout = contentLayout;
    this.nameTv = nameTv;
    this.sizeTv = sizeTv;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemLoggerLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemLoggerLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_logger_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemLoggerLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout contentLayout = (LinearLayout) rootView;

      id = R.id.nameTv;
      TextView nameTv = ViewBindings.findChildViewById(rootView, id);
      if (nameTv == null) {
        break missingId;
      }

      id = R.id.sizeTv;
      TextView sizeTv = ViewBindings.findChildViewById(rootView, id);
      if (sizeTv == null) {
        break missingId;
      }

      return new ItemLoggerLayoutBinding((LinearLayout) rootView, contentLayout, nameTv, sizeTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
