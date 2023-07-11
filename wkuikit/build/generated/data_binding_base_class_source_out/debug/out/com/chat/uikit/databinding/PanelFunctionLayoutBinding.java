// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import net.lucode.hackware.magicindicator.MagicIndicator;

public final class PanelFunctionLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MagicIndicator magicIndicator;

  @NonNull
  public final ViewPager2 viewPage;

  private PanelFunctionLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull MagicIndicator magicIndicator, @NonNull ViewPager2 viewPage) {
    this.rootView = rootView;
    this.magicIndicator = magicIndicator;
    this.viewPage = viewPage;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PanelFunctionLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PanelFunctionLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.panel_function_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PanelFunctionLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.magicIndicator;
      MagicIndicator magicIndicator = ViewBindings.findChildViewById(rootView, id);
      if (magicIndicator == null) {
        break missingId;
      }

      id = R.id.viewPage;
      ViewPager2 viewPage = ViewBindings.findChildViewById(rootView, id);
      if (viewPage == null) {
        break missingId;
      }

      return new PanelFunctionLayoutBinding((LinearLayout) rootView, magicIndicator, viewPage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
