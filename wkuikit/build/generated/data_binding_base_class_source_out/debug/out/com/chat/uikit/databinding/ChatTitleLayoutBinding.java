// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.ui.components.AvatarView;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ChatTitleLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AvatarView avatarView;

  @NonNull
  public final AppCompatImageView backIv;

  @NonNull
  public final LinearLayout categoryLayout;

  @NonNull
  public final FrameLayout rightView;

  @NonNull
  public final TextView subtitleCountTv;

  @NonNull
  public final TextView subtitleTv;

  @NonNull
  public final LinearLayout subtitleView;

  @NonNull
  public final TextView titleCenterTv;

  @NonNull
  public final LinearLayout titleView;

  private ChatTitleLayoutBinding(@NonNull LinearLayout rootView, @NonNull AvatarView avatarView,
      @NonNull AppCompatImageView backIv, @NonNull LinearLayout categoryLayout,
      @NonNull FrameLayout rightView, @NonNull TextView subtitleCountTv,
      @NonNull TextView subtitleTv, @NonNull LinearLayout subtitleView,
      @NonNull TextView titleCenterTv, @NonNull LinearLayout titleView) {
    this.rootView = rootView;
    this.avatarView = avatarView;
    this.backIv = backIv;
    this.categoryLayout = categoryLayout;
    this.rightView = rightView;
    this.subtitleCountTv = subtitleCountTv;
    this.subtitleTv = subtitleTv;
    this.subtitleView = subtitleView;
    this.titleCenterTv = titleCenterTv;
    this.titleView = titleView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ChatTitleLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ChatTitleLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.chat_title_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ChatTitleLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.avatarView;
      AvatarView avatarView = ViewBindings.findChildViewById(rootView, id);
      if (avatarView == null) {
        break missingId;
      }

      id = R.id.backIv;
      AppCompatImageView backIv = ViewBindings.findChildViewById(rootView, id);
      if (backIv == null) {
        break missingId;
      }

      id = R.id.categoryLayout;
      LinearLayout categoryLayout = ViewBindings.findChildViewById(rootView, id);
      if (categoryLayout == null) {
        break missingId;
      }

      id = R.id.rightView;
      FrameLayout rightView = ViewBindings.findChildViewById(rootView, id);
      if (rightView == null) {
        break missingId;
      }

      id = R.id.subtitleCountTv;
      TextView subtitleCountTv = ViewBindings.findChildViewById(rootView, id);
      if (subtitleCountTv == null) {
        break missingId;
      }

      id = R.id.subtitleTv;
      TextView subtitleTv = ViewBindings.findChildViewById(rootView, id);
      if (subtitleTv == null) {
        break missingId;
      }

      id = R.id.subtitleView;
      LinearLayout subtitleView = ViewBindings.findChildViewById(rootView, id);
      if (subtitleView == null) {
        break missingId;
      }

      id = R.id.titleCenterTv;
      TextView titleCenterTv = ViewBindings.findChildViewById(rootView, id);
      if (titleCenterTv == null) {
        break missingId;
      }

      LinearLayout titleView = (LinearLayout) rootView;

      return new ChatTitleLayoutBinding((LinearLayout) rootView, avatarView, backIv, categoryLayout,
          rightView, subtitleCountTv, subtitleTv, subtitleView, titleCenterTv, titleView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
