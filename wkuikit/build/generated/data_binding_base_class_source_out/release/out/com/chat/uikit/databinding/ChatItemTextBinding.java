// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.databinding.WkMsgStatusLayoutBinding;
import com.chat.base.ui.components.AvatarView;
import com.chat.base.views.BubbleLayout;
import com.chat.uikit.R;
import com.chat.uikit.view.ChatTextTimeLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ChatItemTextBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout contentLayout;

  @NonNull
  public final TextView contentTv;

  @NonNull
  public final BubbleLayout contentTvLayout;

  @NonNull
  public final LinearLayout linkView;

  @NonNull
  public final WkMsgStatusLayoutBinding msgTimeView;

  @NonNull
  public final TextView receivedTextNameTv;

  @NonNull
  public final AvatarView replyAvatarIv;

  @NonNull
  public final AppCompatImageView replyIv;

  @NonNull
  public final LinearLayout replyLayout;

  @NonNull
  public final TextView replyNameTv;

  @NonNull
  public final TextView replyTv;

  @NonNull
  public final ChatTextTimeLayout textContentLayout;

  private ChatItemTextBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout contentLayout,
      @NonNull TextView contentTv, @NonNull BubbleLayout contentTvLayout,
      @NonNull LinearLayout linkView, @NonNull WkMsgStatusLayoutBinding msgTimeView,
      @NonNull TextView receivedTextNameTv, @NonNull AvatarView replyAvatarIv,
      @NonNull AppCompatImageView replyIv, @NonNull LinearLayout replyLayout,
      @NonNull TextView replyNameTv, @NonNull TextView replyTv,
      @NonNull ChatTextTimeLayout textContentLayout) {
    this.rootView = rootView;
    this.contentLayout = contentLayout;
    this.contentTv = contentTv;
    this.contentTvLayout = contentTvLayout;
    this.linkView = linkView;
    this.msgTimeView = msgTimeView;
    this.receivedTextNameTv = receivedTextNameTv;
    this.replyAvatarIv = replyAvatarIv;
    this.replyIv = replyIv;
    this.replyLayout = replyLayout;
    this.replyNameTv = replyNameTv;
    this.replyTv = replyTv;
    this.textContentLayout = textContentLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ChatItemTextBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ChatItemTextBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.chat_item_text, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ChatItemTextBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout contentLayout = (LinearLayout) rootView;

      id = R.id.contentTv;
      TextView contentTv = ViewBindings.findChildViewById(rootView, id);
      if (contentTv == null) {
        break missingId;
      }

      id = R.id.contentTvLayout;
      BubbleLayout contentTvLayout = ViewBindings.findChildViewById(rootView, id);
      if (contentTvLayout == null) {
        break missingId;
      }

      id = R.id.linkView;
      LinearLayout linkView = ViewBindings.findChildViewById(rootView, id);
      if (linkView == null) {
        break missingId;
      }

      id = R.id.msgTimeView;
      View msgTimeView = ViewBindings.findChildViewById(rootView, id);
      if (msgTimeView == null) {
        break missingId;
      }
      WkMsgStatusLayoutBinding binding_msgTimeView = WkMsgStatusLayoutBinding.bind(msgTimeView);

      id = R.id.receivedTextNameTv;
      TextView receivedTextNameTv = ViewBindings.findChildViewById(rootView, id);
      if (receivedTextNameTv == null) {
        break missingId;
      }

      id = R.id.replyAvatarIv;
      AvatarView replyAvatarIv = ViewBindings.findChildViewById(rootView, id);
      if (replyAvatarIv == null) {
        break missingId;
      }

      id = R.id.replyIv;
      AppCompatImageView replyIv = ViewBindings.findChildViewById(rootView, id);
      if (replyIv == null) {
        break missingId;
      }

      id = R.id.replyLayout;
      LinearLayout replyLayout = ViewBindings.findChildViewById(rootView, id);
      if (replyLayout == null) {
        break missingId;
      }

      id = R.id.replyNameTv;
      TextView replyNameTv = ViewBindings.findChildViewById(rootView, id);
      if (replyNameTv == null) {
        break missingId;
      }

      id = R.id.replyTv;
      TextView replyTv = ViewBindings.findChildViewById(rootView, id);
      if (replyTv == null) {
        break missingId;
      }

      id = R.id.textContentLayout;
      ChatTextTimeLayout textContentLayout = ViewBindings.findChildViewById(rootView, id);
      if (textContentLayout == null) {
        break missingId;
      }

      return new ChatItemTextBinding((LinearLayout) rootView, contentLayout, contentTv,
          contentTvLayout, linkView, binding_msgTimeView, receivedTextNameTv, replyAvatarIv,
          replyIv, replyLayout, replyNameTv, replyTv, textContentLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
