// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActUpdateUserInfoLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatEditText contentEt;

  @NonNull
  public final TextView descTv;

  private ActUpdateUserInfoLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull AppCompatEditText contentEt, @NonNull TextView descTv) {
    this.rootView = rootView;
    this.contentEt = contentEt;
    this.descTv = descTv;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActUpdateUserInfoLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActUpdateUserInfoLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.act_update_user_info_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActUpdateUserInfoLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.contentEt;
      AppCompatEditText contentEt = ViewBindings.findChildViewById(rootView, id);
      if (contentEt == null) {
        break missingId;
      }

      id = R.id.descTv;
      TextView descTv = ViewBindings.findChildViewById(rootView, id);
      if (descTv == null) {
        break missingId;
      }

      return new ActUpdateUserInfoLayoutBinding((LinearLayout) rootView, contentEt, descTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}