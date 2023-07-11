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
import com.chat.base.ui.components.AvatarView;
import com.chat.base.ui.components.RoundTextView;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemAllMembersLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AvatarView avatarView;

  @NonNull
  public final LinearLayout contentLayout;

  @NonNull
  public final TextView nameTv;

  @NonNull
  public final RoundTextView roleTv;

  @NonNull
  public final TextView timeTv;

  private ItemAllMembersLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull AvatarView avatarView, @NonNull LinearLayout contentLayout, @NonNull TextView nameTv,
      @NonNull RoundTextView roleTv, @NonNull TextView timeTv) {
    this.rootView = rootView;
    this.avatarView = avatarView;
    this.contentLayout = contentLayout;
    this.nameTv = nameTv;
    this.roleTv = roleTv;
    this.timeTv = timeTv;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemAllMembersLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemAllMembersLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_all_members_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemAllMembersLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.avatarView;
      AvatarView avatarView = ViewBindings.findChildViewById(rootView, id);
      if (avatarView == null) {
        break missingId;
      }

      LinearLayout contentLayout = (LinearLayout) rootView;

      id = R.id.nameTv;
      TextView nameTv = ViewBindings.findChildViewById(rootView, id);
      if (nameTv == null) {
        break missingId;
      }

      id = R.id.roleTv;
      RoundTextView roleTv = ViewBindings.findChildViewById(rootView, id);
      if (roleTv == null) {
        break missingId;
      }

      id = R.id.timeTv;
      TextView timeTv = ViewBindings.findChildViewById(rootView, id);
      if (timeTv == null) {
        break missingId;
      }

      return new ItemAllMembersLayoutBinding((LinearLayout) rootView, avatarView, contentLayout,
          nameTv, roleTv, timeTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
