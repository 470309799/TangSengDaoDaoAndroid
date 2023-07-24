package com.chat.uikit;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chat.base.adapter.WKFragmentStateAdapter;
import com.chat.base.base.WKBaseActivity;
import com.chat.base.common.WKCommonModel;
import com.chat.base.config.WKConfig;
import com.chat.base.config.WKSharedPreferencesUtil;
import com.chat.base.endpoint.EndpointCategory;
import com.chat.base.endpoint.EndpointManager;
import com.chat.base.endpoint.entity.MailListDot;
import com.chat.base.net.HttpResponseCode;
import com.chat.base.ui.Theme;
import com.chat.base.ui.components.CounterView;
import com.chat.base.utils.ActManagerUtils;
import com.chat.base.utils.LayoutHelper;
import com.chat.base.utils.WKDialogUtils;
import com.chat.base.utils.WKPermissions;
import com.chat.base.utils.language.WKMultiLanguageUtil;
import com.chat.uikit.contacts.service.FriendModel;
import com.chat.uikit.databinding.ActTabMainBinding;
import com.chat.uikit.fragment.ChatFragment;
import com.chat.uikit.fragment.ContactsFragment;
import com.chat.uikit.fragment.MyFragment;

import org.telegram.ui.Components.RLottieImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * 2019-11-12 13:57
 * tab导航栏
 */
public class TabActivity extends WKBaseActivity<ActTabMainBinding> {
    CounterView msgCounterView;
    CounterView contactsCounterView;
    View contactsSpotView;
    RLottieImageView chatIV, contactsIV, meIV;

    @Override
    protected ActTabMainBinding getViewBinding() {
        return ActTabMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setTitle(TextView titleTv) {

    }

    @Override
    protected void initPresenter() {
        ActManagerUtils.getInstance().clearAllActivity();
    }

    @Override
    public boolean supportSlideBack() {
        return false;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String desc = String.format(getString(R.string.notification_permissions_desc), getString(R.string.app_name));
            WKPermissions.getInstance().checkPermissions(new WKPermissions.IPermissionResult() {
                @Override
                public void onResult(boolean result) {

                }

                @Override
                public void clickResult(boolean isCancel) {
                    finish();
                }
            }, this, desc, Manifest.permission.POST_NOTIFICATIONS);
        }else {
            boolean isEnabled = NotificationManagerCompat.from(this).areNotificationsEnabled();
            if (!isEnabled) {
                EndpointManager.getInstance().invoke("show_open_notification_dialog", this);
            }
        }
        chatIV = new RLottieImageView(this);
        contactsIV = new RLottieImageView(this);
        meIV = new RLottieImageView(this);

        List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(new ChatFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new MyFragment());

        wkVBinding.vp.setAdapter(new WKFragmentStateAdapter(this, fragments));
        WKCommonModel.getInstance().getAppNewVersion(false, version -> {
            if (version != null && !TextUtils.isEmpty(version.download_url)) {
                WKDialogUtils.getInstance().showNewVersionDialog(TabActivity.this, version);
            }
        });
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        WKCommonModel.getInstance().getAppConfig();
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_chat).setVisible(false);
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_my).setVisible(false);
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_chat).setVisible(false);

        FrameLayout view = wkVBinding.bottomNavigation.findViewById(R.id.i_chat);
        msgCounterView = new CounterView(this);
        msgCounterView.setColors(R.color.white, R.color.reminderColor);
        view.addView(chatIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        view.addView(msgCounterView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 20, 5, 0, 15));

        FrameLayout contactsView = wkVBinding.bottomNavigation.findViewById(R.id.i_contacts);
        contactsCounterView = new CounterView(this);
        contactsCounterView.setColors(R.color.white, R.color.reminderColor);
        contactsView.addView(contactsIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        contactsView.addView(contactsCounterView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 20, 5, 0, 15));
        contactsSpotView = new View(this);
        contactsSpotView.setBackgroundResource(R.drawable.msg_bg);
        contactsView.addView(contactsSpotView, LayoutHelper.createFrame(10, 10, Gravity.CENTER_HORIZONTAL, 10, 10, 0, 0));

        FrameLayout meView = wkVBinding.bottomNavigation.findViewById(R.id.i_my);
        meView.addView(meIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        contactsSpotView.setVisibility(View.GONE);
        contactsCounterView.setVisibility(View.GONE);
        msgCounterView.setVisibility(View.GONE);
        playAnimation(0);
    }

    @Override
    protected void initListener() {
        wkVBinding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    playAnimation(0);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_chat);
                } else if (position == 1) {
                    playAnimation(1);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_contacts);
                } else if (position == 2) {
                    playAnimation(2);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_my);
                }
            }
        });
        wkVBinding.bottomNavigation.setItemIconTintList(null);
        wkVBinding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.i_chat) {
                wkVBinding.vp.setCurrentItem(0);
                playAnimation(0);
            } else if (item.getItemId() == R.id.i_contacts) {
                wkVBinding.vp.setCurrentItem(1);
                playAnimation(1);
            } else if (item.getItemId() == R.id.i_my) {
                wkVBinding.vp.setCurrentItem(2);
                playAnimation(2);
            }
            return true;
        });
        EndpointManager.getInstance().setMethod("tab_activity", EndpointCategory.wkRefreshMailList, object -> {
            getAllRedDot();
            return null;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRedDot();
        boolean sync_friend = WKSharedPreferencesUtil.getInstance().getBoolean("sync_friend");
        if (sync_friend) {
            FriendModel.getInstance().syncFriends((code, msg) -> {
                if (code != HttpResponseCode.success && !TextUtils.isEmpty(msg)) {
                    showToast(msg);
                }
                if (code == HttpResponseCode.success) {
                    WKSharedPreferencesUtil.getInstance().putBoolean("sync_friend", false);
                }
            });
        }
    }

    public void setMsgCount(int number) {
        WKUIKitApplication.getInstance().totalMsgCount = number;
        if (number > 0) {
            msgCounterView.setCount(number, true);
            msgCounterView.setVisibility(View.VISIBLE);
        } else {
            msgCounterView.setCount(0, true);
            msgCounterView.setVisibility(View.GONE);
        }
    }

    public void setContactCount(int number, boolean showDot) {
        if (number > 0 || showDot) {
            if (number > 0) {
                contactsCounterView.setCount(number, true);
                contactsCounterView.setVisibility(View.VISIBLE);
                contactsSpotView.setVisibility(View.GONE);
            } else {
                contactsCounterView.setVisibility(View.GONE);
                contactsSpotView.setVisibility(View.VISIBLE);
                contactsCounterView.setCount(0, true);
            }
        } else {
            contactsCounterView.setVisibility(View.GONE);
            contactsSpotView.setVisibility(View.GONE);
        }
    }

    private void getAllRedDot() {
        boolean showDot = false;
        int totalCount = 0;
        int newFriendCount = WKSharedPreferencesUtil.getInstance().getInt(WKConfig.getInstance().getUid() + "_new_friend_count");
        totalCount = totalCount + newFriendCount;
        List<MailListDot> list = EndpointManager.getInstance().invokes(EndpointCategory.wkGetMailListRedDot, null);
        if (list != null && list.size() > 0) {
            for (MailListDot MailListDot : list) {
                if (MailListDot != null) {
                    totalCount += MailListDot.numCount;
                    if (!showDot) showDot = MailListDot.showDot;
                }
            }
        }
        setContactCount(totalCount, showDot);
    }

    @Override
    public Resources getResources() {
        float fontScale = WKSharedPreferencesUtil.getInstance().getFloat("font_scale", 1);
        if (fontScale <= 0) {
            fontScale = 1;
        }
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = fontScale; //1 设置正常字体大小的倍数
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    private void playAnimation(int index) {
        if (index == 0) {
            meIV.setImageResource(R.mipmap.ic_mine_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_n);
            chatIV.setImageResource(R.mipmap.ic_chat_s);
        } else if (index == 1) {
            meIV.setImageResource(R.mipmap.ic_mine_n);
            chatIV.setImageResource(R.mipmap.ic_chat_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_s);
        } else {
            chatIV.setImageResource(R.mipmap.ic_chat_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_n);
            meIV.setImageResource(R.mipmap.ic_mine_s);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WKMultiLanguageUtil.getInstance().setConfiguration();
        String themePref =
                WKSharedPreferencesUtil.getInstance().getSP(Theme.wk_theme_pref, Theme.DEFAULT_MODE);
        Theme.applyTheme(themePref);
    }

    @Override
    public void finish() {
        super.finish();
        EndpointManager.getInstance().remove("tab_activity");
    }
}
