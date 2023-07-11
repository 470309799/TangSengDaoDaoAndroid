// Generated by view binder compiler. Do not edit!
package com.chat.base.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewEmojiPanelBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatEditText editText;

  @NonNull
  public final FrameLayout emojiLayout;

  @NonNull
  public final AppCompatImageView imgSwitch;

  @NonNull
  public final LinearLayout layoutInputPanel;

  @NonNull
  public final FrameLayout layoutNull;

  @NonNull
  public final LinearLayout layoutPanel;

  @NonNull
  public final MaterialButton sendBtn;

  private ViewEmojiPanelBinding(@NonNull LinearLayout rootView, @NonNull AppCompatEditText editText,
      @NonNull FrameLayout emojiLayout, @NonNull AppCompatImageView imgSwitch,
      @NonNull LinearLayout layoutInputPanel, @NonNull FrameLayout layoutNull,
      @NonNull LinearLayout layoutPanel, @NonNull MaterialButton sendBtn) {
    this.rootView = rootView;
    this.editText = editText;
    this.emojiLayout = emojiLayout;
    this.imgSwitch = imgSwitch;
    this.layoutInputPanel = layoutInputPanel;
    this.layoutNull = layoutNull;
    this.layoutPanel = layoutPanel;
    this.sendBtn = sendBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewEmojiPanelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewEmojiPanelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_emoji_panel, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewEmojiPanelBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edit_text;
      AppCompatEditText editText = ViewBindings.findChildViewById(rootView, id);
      if (editText == null) {
        break missingId;
      }

      id = R.id.emojiLayout;
      FrameLayout emojiLayout = ViewBindings.findChildViewById(rootView, id);
      if (emojiLayout == null) {
        break missingId;
      }

      id = R.id.img_switch;
      AppCompatImageView imgSwitch = ViewBindings.findChildViewById(rootView, id);
      if (imgSwitch == null) {
        break missingId;
      }

      id = R.id.layout_input_panel;
      LinearLayout layoutInputPanel = ViewBindings.findChildViewById(rootView, id);
      if (layoutInputPanel == null) {
        break missingId;
      }

      id = R.id.layout_null;
      FrameLayout layoutNull = ViewBindings.findChildViewById(rootView, id);
      if (layoutNull == null) {
        break missingId;
      }

      LinearLayout layoutPanel = (LinearLayout) rootView;

      id = R.id.sendBtn;
      MaterialButton sendBtn = ViewBindings.findChildViewById(rootView, id);
      if (sendBtn == null) {
        break missingId;
      }

      return new ViewEmojiPanelBinding((LinearLayout) rootView, editText, emojiLayout, imgSwitch,
          layoutInputPanel, layoutNull, layoutPanel, sendBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
