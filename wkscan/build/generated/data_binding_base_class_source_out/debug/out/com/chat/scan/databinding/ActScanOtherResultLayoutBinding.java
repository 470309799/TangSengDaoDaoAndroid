// Generated by view binder compiler. Do not edit!
package com.chat.scan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.scan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActScanOtherResultLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView resultTv;

  private ActScanOtherResultLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull TextView resultTv) {
    this.rootView = rootView;
    this.resultTv = resultTv;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActScanOtherResultLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActScanOtherResultLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.act_scan_other_result_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActScanOtherResultLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.resultTv;
      TextView resultTv = ViewBindings.findChildViewById(rootView, id);
      if (resultTv == null) {
        break missingId;
      }

      return new ActScanOtherResultLayoutBinding((LinearLayout) rootView, resultTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
