// Generated by view binder compiler. Do not edit!
package com.chat.base.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.R;
import com.chat.base.views.ShadowLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragEmojiLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatImageView deleteIv;

  @NonNull
  public final RelativeLayout deleteLayout;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final ShadowLayout shadowLayout;

  private FragEmojiLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatImageView deleteIv, @NonNull RelativeLayout deleteLayout,
      @NonNull RecyclerView recyclerView, @NonNull ShadowLayout shadowLayout) {
    this.rootView = rootView;
    this.deleteIv = deleteIv;
    this.deleteLayout = deleteLayout;
    this.recyclerView = recyclerView;
    this.shadowLayout = shadowLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragEmojiLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragEmojiLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.frag_emoji_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragEmojiLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deleteIv;
      AppCompatImageView deleteIv = ViewBindings.findChildViewById(rootView, id);
      if (deleteIv == null) {
        break missingId;
      }

      id = R.id.deleteLayout;
      RelativeLayout deleteLayout = ViewBindings.findChildViewById(rootView, id);
      if (deleteLayout == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.shadowLayout;
      ShadowLayout shadowLayout = ViewBindings.findChildViewById(rootView, id);
      if (shadowLayout == null) {
        break missingId;
      }

      return new FragEmojiLayoutBinding((RelativeLayout) rootView, deleteIv, deleteLayout,
          recyclerView, shadowLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}