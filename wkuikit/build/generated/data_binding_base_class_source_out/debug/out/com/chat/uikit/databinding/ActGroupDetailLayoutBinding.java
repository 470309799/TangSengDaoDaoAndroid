// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.ui.components.SwitchView;
import com.chat.uikit.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActGroupDetailLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final SwitchView chatPwdSwitchView;

  @NonNull
  public final LinearLayout clearChatMsgLayout;

  @NonNull
  public final TextView exitBtn;

  @NonNull
  public final LinearLayout findContentLayout;

  @NonNull
  public final View findContentView;

  @NonNull
  public final LinearLayout groupAvatarLayout;

  @NonNull
  public final LinearLayout groupManageLayout;

  @NonNull
  public final View groupManageView;

  @NonNull
  public final LinearLayout groupNameLayout;

  @NonNull
  public final TextView groupNoticeTv;

  @NonNull
  public final LinearLayout groupQrLayout;

  @NonNull
  public final LinearLayout inGroupNameLayout;

  @NonNull
  public final TextView inGroupNameTv;

  @NonNull
  public final LinearLayout msgReceiptLayout;

  @NonNull
  public final LinearLayout msgRemindLayout;

  @NonNull
  public final SwitchView muteSwitchView;

  @NonNull
  public final TextView nameTv;

  @NonNull
  public final LinearLayout noticeLayout;

  @NonNull
  public final SmartRefreshLayout refreshLayout;

  @NonNull
  public final LinearLayout remarkLayout;

  @NonNull
  public final TextView remarkTv;

  @NonNull
  public final LinearLayout reportLayout;

  @NonNull
  public final SwitchView saveSwitchView;

  @NonNull
  public final TextView showAllMembersTv;

  @NonNull
  public final SwitchView showNickSwitchView;

  @NonNull
  public final SwitchView stickSwitchView;

  @NonNull
  public final LinearLayout unsetNoticeLayout;

  @NonNull
  public final RecyclerView userRecyclerView;

  private ActGroupDetailLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull SwitchView chatPwdSwitchView, @NonNull LinearLayout clearChatMsgLayout,
      @NonNull TextView exitBtn, @NonNull LinearLayout findContentLayout,
      @NonNull View findContentView, @NonNull LinearLayout groupAvatarLayout,
      @NonNull LinearLayout groupManageLayout, @NonNull View groupManageView,
      @NonNull LinearLayout groupNameLayout, @NonNull TextView groupNoticeTv,
      @NonNull LinearLayout groupQrLayout, @NonNull LinearLayout inGroupNameLayout,
      @NonNull TextView inGroupNameTv, @NonNull LinearLayout msgReceiptLayout,
      @NonNull LinearLayout msgRemindLayout, @NonNull SwitchView muteSwitchView,
      @NonNull TextView nameTv, @NonNull LinearLayout noticeLayout,
      @NonNull SmartRefreshLayout refreshLayout, @NonNull LinearLayout remarkLayout,
      @NonNull TextView remarkTv, @NonNull LinearLayout reportLayout,
      @NonNull SwitchView saveSwitchView, @NonNull TextView showAllMembersTv,
      @NonNull SwitchView showNickSwitchView, @NonNull SwitchView stickSwitchView,
      @NonNull LinearLayout unsetNoticeLayout, @NonNull RecyclerView userRecyclerView) {
    this.rootView = rootView;
    this.chatPwdSwitchView = chatPwdSwitchView;
    this.clearChatMsgLayout = clearChatMsgLayout;
    this.exitBtn = exitBtn;
    this.findContentLayout = findContentLayout;
    this.findContentView = findContentView;
    this.groupAvatarLayout = groupAvatarLayout;
    this.groupManageLayout = groupManageLayout;
    this.groupManageView = groupManageView;
    this.groupNameLayout = groupNameLayout;
    this.groupNoticeTv = groupNoticeTv;
    this.groupQrLayout = groupQrLayout;
    this.inGroupNameLayout = inGroupNameLayout;
    this.inGroupNameTv = inGroupNameTv;
    this.msgReceiptLayout = msgReceiptLayout;
    this.msgRemindLayout = msgRemindLayout;
    this.muteSwitchView = muteSwitchView;
    this.nameTv = nameTv;
    this.noticeLayout = noticeLayout;
    this.refreshLayout = refreshLayout;
    this.remarkLayout = remarkLayout;
    this.remarkTv = remarkTv;
    this.reportLayout = reportLayout;
    this.saveSwitchView = saveSwitchView;
    this.showAllMembersTv = showAllMembersTv;
    this.showNickSwitchView = showNickSwitchView;
    this.stickSwitchView = stickSwitchView;
    this.unsetNoticeLayout = unsetNoticeLayout;
    this.userRecyclerView = userRecyclerView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActGroupDetailLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActGroupDetailLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.act_group_detail_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActGroupDetailLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chatPwdSwitchView;
      SwitchView chatPwdSwitchView = ViewBindings.findChildViewById(rootView, id);
      if (chatPwdSwitchView == null) {
        break missingId;
      }

      id = R.id.clearChatMsgLayout;
      LinearLayout clearChatMsgLayout = ViewBindings.findChildViewById(rootView, id);
      if (clearChatMsgLayout == null) {
        break missingId;
      }

      id = R.id.exitBtn;
      TextView exitBtn = ViewBindings.findChildViewById(rootView, id);
      if (exitBtn == null) {
        break missingId;
      }

      id = R.id.findContentLayout;
      LinearLayout findContentLayout = ViewBindings.findChildViewById(rootView, id);
      if (findContentLayout == null) {
        break missingId;
      }

      id = R.id.findContentView;
      View findContentView = ViewBindings.findChildViewById(rootView, id);
      if (findContentView == null) {
        break missingId;
      }

      id = R.id.groupAvatarLayout;
      LinearLayout groupAvatarLayout = ViewBindings.findChildViewById(rootView, id);
      if (groupAvatarLayout == null) {
        break missingId;
      }

      id = R.id.groupManageLayout;
      LinearLayout groupManageLayout = ViewBindings.findChildViewById(rootView, id);
      if (groupManageLayout == null) {
        break missingId;
      }

      id = R.id.groupManageView;
      View groupManageView = ViewBindings.findChildViewById(rootView, id);
      if (groupManageView == null) {
        break missingId;
      }

      id = R.id.groupNameLayout;
      LinearLayout groupNameLayout = ViewBindings.findChildViewById(rootView, id);
      if (groupNameLayout == null) {
        break missingId;
      }

      id = R.id.groupNoticeTv;
      TextView groupNoticeTv = ViewBindings.findChildViewById(rootView, id);
      if (groupNoticeTv == null) {
        break missingId;
      }

      id = R.id.groupQrLayout;
      LinearLayout groupQrLayout = ViewBindings.findChildViewById(rootView, id);
      if (groupQrLayout == null) {
        break missingId;
      }

      id = R.id.inGroupNameLayout;
      LinearLayout inGroupNameLayout = ViewBindings.findChildViewById(rootView, id);
      if (inGroupNameLayout == null) {
        break missingId;
      }

      id = R.id.inGroupNameTv;
      TextView inGroupNameTv = ViewBindings.findChildViewById(rootView, id);
      if (inGroupNameTv == null) {
        break missingId;
      }

      id = R.id.msgReceiptLayout;
      LinearLayout msgReceiptLayout = ViewBindings.findChildViewById(rootView, id);
      if (msgReceiptLayout == null) {
        break missingId;
      }

      id = R.id.msgRemindLayout;
      LinearLayout msgRemindLayout = ViewBindings.findChildViewById(rootView, id);
      if (msgRemindLayout == null) {
        break missingId;
      }

      id = R.id.muteSwitchView;
      SwitchView muteSwitchView = ViewBindings.findChildViewById(rootView, id);
      if (muteSwitchView == null) {
        break missingId;
      }

      id = R.id.nameTv;
      TextView nameTv = ViewBindings.findChildViewById(rootView, id);
      if (nameTv == null) {
        break missingId;
      }

      id = R.id.noticeLayout;
      LinearLayout noticeLayout = ViewBindings.findChildViewById(rootView, id);
      if (noticeLayout == null) {
        break missingId;
      }

      id = R.id.refreshLayout;
      SmartRefreshLayout refreshLayout = ViewBindings.findChildViewById(rootView, id);
      if (refreshLayout == null) {
        break missingId;
      }

      id = R.id.remarkLayout;
      LinearLayout remarkLayout = ViewBindings.findChildViewById(rootView, id);
      if (remarkLayout == null) {
        break missingId;
      }

      id = R.id.remarkTv;
      TextView remarkTv = ViewBindings.findChildViewById(rootView, id);
      if (remarkTv == null) {
        break missingId;
      }

      id = R.id.reportLayout;
      LinearLayout reportLayout = ViewBindings.findChildViewById(rootView, id);
      if (reportLayout == null) {
        break missingId;
      }

      id = R.id.saveSwitchView;
      SwitchView saveSwitchView = ViewBindings.findChildViewById(rootView, id);
      if (saveSwitchView == null) {
        break missingId;
      }

      id = R.id.showAllMembersTv;
      TextView showAllMembersTv = ViewBindings.findChildViewById(rootView, id);
      if (showAllMembersTv == null) {
        break missingId;
      }

      id = R.id.showNickSwitchView;
      SwitchView showNickSwitchView = ViewBindings.findChildViewById(rootView, id);
      if (showNickSwitchView == null) {
        break missingId;
      }

      id = R.id.stickSwitchView;
      SwitchView stickSwitchView = ViewBindings.findChildViewById(rootView, id);
      if (stickSwitchView == null) {
        break missingId;
      }

      id = R.id.unsetNoticeLayout;
      LinearLayout unsetNoticeLayout = ViewBindings.findChildViewById(rootView, id);
      if (unsetNoticeLayout == null) {
        break missingId;
      }

      id = R.id.userRecyclerView;
      RecyclerView userRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (userRecyclerView == null) {
        break missingId;
      }

      return new ActGroupDetailLayoutBinding((LinearLayout) rootView, chatPwdSwitchView,
          clearChatMsgLayout, exitBtn, findContentLayout, findContentView, groupAvatarLayout,
          groupManageLayout, groupManageView, groupNameLayout, groupNoticeTv, groupQrLayout,
          inGroupNameLayout, inGroupNameTv, msgReceiptLayout, msgRemindLayout, muteSwitchView,
          nameTv, noticeLayout, refreshLayout, remarkLayout, remarkTv, reportLayout, saveSwitchView,
          showAllMembersTv, showNickSwitchView, stickSwitchView, unsetNoticeLayout,
          userRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
