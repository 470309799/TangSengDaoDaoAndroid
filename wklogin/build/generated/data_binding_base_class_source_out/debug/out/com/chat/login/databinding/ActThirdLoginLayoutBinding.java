// Generated by view binder compiler. Do not edit!
package com.chat.login.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.login.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActThirdLoginLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatImageView giteeIV;

  @NonNull
  public final AppCompatImageView githubIV;

  @NonNull
  public final TextView loginTitleTv;

  private ActThirdLoginLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatImageView giteeIV, @NonNull AppCompatImageView githubIV,
      @NonNull TextView loginTitleTv) {
    this.rootView = rootView;
    this.giteeIV = giteeIV;
    this.githubIV = githubIV;
    this.loginTitleTv = loginTitleTv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActThirdLoginLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActThirdLoginLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.act_third_login_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActThirdLoginLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.giteeIV;
      AppCompatImageView giteeIV = ViewBindings.findChildViewById(rootView, id);
      if (giteeIV == null) {
        break missingId;
      }

      id = R.id.githubIV;
      AppCompatImageView githubIV = ViewBindings.findChildViewById(rootView, id);
      if (githubIV == null) {
        break missingId;
      }

      id = R.id.loginTitleTv;
      TextView loginTitleTv = ViewBindings.findChildViewById(rootView, id);
      if (loginTitleTv == null) {
        break missingId;
      }

      return new ActThirdLoginLayoutBinding((RelativeLayout) rootView, giteeIV, githubIV,
          loginTitleTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
