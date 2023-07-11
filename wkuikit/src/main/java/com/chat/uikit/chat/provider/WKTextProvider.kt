package com.chat.uikit.chat.provider

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.ContactsContract
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chat.base.WKBaseApplication
import com.chat.base.act.WKWebViewActivity
import com.chat.base.config.WKApiConfig
import com.chat.base.config.WKSharedPreferencesUtil
import com.chat.base.emoji.EmojiManager
import com.chat.base.emoji.MoonUtil
import com.chat.base.endpoint.EndpointManager
import com.chat.base.endpoint.entity.*
import com.chat.base.glide.GlideUtils
import com.chat.base.msg.ChatAdapter
import com.chat.base.msg.model.WKGifContent
import com.chat.base.msgitem.*
import com.chat.base.ui.components.AlignImageSpan
import com.chat.base.ui.components.AvatarView
import com.chat.base.ui.components.NormalClickableContent
import com.chat.base.ui.components.NormalClickableSpan
import com.chat.base.utils.SoftKeyboardUtils
import com.chat.base.utils.StringUtils
import com.chat.base.utils.WKDialogUtils
import com.chat.base.utils.WKToastUtils
import com.chat.base.views.BottomEntity
import com.chat.base.views.BubbleLayout
import com.chat.base.views.CommonBottomView
import com.chat.uikit.R
import com.chat.uikit.user.UserDetailActivity
import com.google.android.material.snackbar.Snackbar
import com.xinbida.wukongim.WKIM
import com.xinbida.wukongim.entity.WKChannel
import com.xinbida.wukongim.entity.WKChannelType
import com.xinbida.wukongim.entity.WKMsg
import com.xinbida.wukongim.entity.WKMsgSetting
import com.xinbida.wukongim.msgmodel.WKImageContent
import com.xinbida.wukongim.msgmodel.WKTextContent
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.math.abs

open class WKTextProvider : WKChatBaseProvider() {
    override fun getChatViewItem(parentView: ViewGroup, from: WKChatIteMsgFromType): View? {
        return LayoutInflater.from(context).inflate(R.layout.chat_item_text, parentView, false)
    }

    override fun setData(
        adapterPosition: Int,
        parentView: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        from: WKChatIteMsgFromType
    ) {
//        val textContentLayout = parentView.findViewById<View>(R.id.textContentLayout)
        val linkView = parentView.findViewById<LinearLayout>(R.id.linkView)
        val contentTv = parentView.findViewById<TextView>(R.id.contentTv)
        val receivedTextNameTv = parentView.findViewById<TextView>(R.id.receivedTextNameTv)
        val msgTimeView = parentView.findViewById<View>(R.id.msgTimeView)
        val replyLayout = parentView.findViewById<View>(R.id.replyLayout)
        val replyTv = parentView.findViewById<TextView>(R.id.replyTv)
        val replyNameTv = parentView.findViewById<TextView>(R.id.replyNameTv)
        val replyAvatarIv = parentView.findViewById<AvatarView>(R.id.replyAvatarIv)
        replyAvatarIv.setSize(20f)
        val contentTvLayout = parentView.findViewById<BubbleLayout>(R.id.contentTvLayout)
        val replyIv = parentView.findViewById<ImageView>(R.id.replyIv)
        val contentLayout = parentView.findViewById<LinearLayout>(R.id.contentLayout)
        val mTextContent = uiChatMsgItemEntity.wkMsg.baseContentMsgModel as WKTextContent
        // 这里要指定文本宽度 - padding的距离
//        textContentLayout.layoutParams.width = getViewWidth(from, uiChatMsgItemEntity)
        val bgType = getMsgBgType(
            uiChatMsgItemEntity.previousMsg, uiChatMsgItemEntity.wkMsg, uiChatMsgItemEntity.nextMsg
        )
//        contentTvLayout.setAll(bgType, from, WKContentType.WK_TEXT)
//        if (textContentLayout.layoutParams.width < msgTimeView.layoutParams.width) {
//            textContentLayout.layoutParams.width = msgTimeView.layoutParams.width
//        }
        val textColor: Int
        if (from == WKChatIteMsgFromType.SEND) {
            contentTv.setBackgroundResource(R.drawable.send_chat_text_bg)
            contentLayout.gravity = Gravity.END
            receivedTextNameTv.visibility = View.GONE
            textColor = ContextCompat.getColor(context, R.color.colorDark)
        } else {
            contentTv.setBackgroundResource(R.drawable.received_chat_text_bg)
            val isShowNickName: Boolean =
                (bgType == WKMsgBgType.single || bgType == WKMsgBgType.top) && uiChatMsgItemEntity.showNickName && uiChatMsgItemEntity.wkMsg.channelType == WKChannelType.GROUP
            if (isShowNickName) {
                var showName = ""
                var channelName = ""
                if (uiChatMsgItemEntity.wkMsg.from != null) {
                    if (!TextUtils.isEmpty(uiChatMsgItemEntity.wkMsg.from.channelRemark))
                        showName = uiChatMsgItemEntity.wkMsg.from.channelRemark
                    channelName = uiChatMsgItemEntity.wkMsg.from.channelName
                }
                if (TextUtils.isEmpty(showName)) {
                    if (uiChatMsgItemEntity.wkMsg.memberOfFrom != null) {
                        showName =
                            if (TextUtils.isEmpty(uiChatMsgItemEntity.wkMsg.memberOfFrom.memberRemark)) uiChatMsgItemEntity.wkMsg.memberOfFrom.memberName else uiChatMsgItemEntity.wkMsg.memberOfFrom.memberRemark
                    } else {
                        if (TextUtils.isEmpty(showName)) {
                            showName = channelName
                        }
                    }
                }

                if (TextUtils.isEmpty(showName)) {
                    WKIM.getInstance().channelManager.fetchChannelInfo(
                        uiChatMsgItemEntity.wkMsg.fromUID, WKChannelType.PERSONAL
                    )
                    receivedTextNameTv.visibility = View.GONE
                } else {
                    receivedTextNameTv.text = showName
                    receivedTextNameTv.visibility = View.VISIBLE
                    if (!TextUtils.isEmpty(uiChatMsgItemEntity.wkMsg.fromUID)) {
                        val colors =
                            WKBaseApplication.getInstance().context.resources.getIntArray(com.chat.base.R.array.name_colors)
                        val index = abs(uiChatMsgItemEntity.wkMsg.fromUID.hashCode()) % colors.size
                        receivedTextNameTv.setTextColor(colors[index])
                    }
                }
            } else {
                receivedTextNameTv.visibility = View.GONE
            }
            contentLayout.gravity = Gravity.START
            textColor = ContextCompat.getColor(context, R.color.receive_text_color)
        }
        contentTv.setTextColor(textColor)
        contentTv.text = uiChatMsgItemEntity.displaySpans
        contentTv.movementMethod = LinkMovementMethod.getInstance()

        //设置回复内容
        replyLayout.visibility =
            if (mTextContent.reply == null || mTextContent.reply.payload == null) View.GONE else View.VISIBLE
        if (mTextContent.reply != null && mTextContent.reply.payload != null) {
            if (mTextContent.reply.revoke == 1) {
                replyTv.setText(R.string.reply_msg_is_revoked)
                replyIv.visibility = View.GONE
                replyNameTv.visibility = View.GONE
                replyAvatarIv.visibility = View.GONE
            } else {
                replyIv.visibility = View.VISIBLE
                replyNameTv.visibility = View.VISIBLE
                replyAvatarIv.visibility = View.VISIBLE
                replyAvatarIv.showAvatar(mTextContent.reply.from_uid, WKChannelType.PERSONAL)
                val mChannel = WKIM.getInstance().channelManager.getChannel(
                    mTextContent.reply.from_uid, WKChannelType.PERSONAL
                )
                if (mChannel != null) {
                    replyNameTv.text = mChannel.channelName
                } else {
                    replyNameTv.text = mTextContent.reply.from_name
                    WKIM.getInstance().channelManager.fetchChannelInfo(
                        mTextContent.reply.from_uid, WKChannelType.PERSONAL
                    )
                }
                when (mTextContent.reply.payload.type) {
                    WKContentType.WK_GIF -> {
                        replyIv.visibility = View.VISIBLE
                        replyTv.visibility = View.GONE
                        val gifContent = mTextContent.reply.payload as WKGifContent
                        GlideUtils.getInstance()
                            .showGif(
                                context,
                                WKApiConfig.getShowUrl(gifContent.url),
                                replyIv,
                                null
                            )
                    }

                    WKContentType.WK_IMAGE -> {
                        replyIv.visibility = View.VISIBLE
                        replyTv.visibility = View.GONE
                        val imageContent = mTextContent.reply.payload as WKImageContent
                        var showUrl: String
                        if (!TextUtils.isEmpty(imageContent.localPath)) {
                            showUrl = imageContent.localPath
                            val file = File(showUrl)
                            if (!file.exists()) {
                                //如果本地文件被删除就显示网络图片
                                showUrl = WKApiConfig.getShowUrl(imageContent.url)
                            }
                        } else {
                            showUrl = WKApiConfig.getShowUrl(imageContent.url)
                        }
                        GlideUtils.getInstance().showImg(context, showUrl, replyIv)
                    }

                    else -> {
                        replyIv.visibility = View.GONE
                        replyTv.visibility = View.VISIBLE
                        var content = mTextContent.reply.payload.getDisplayContent()
                        if (mTextContent.reply.contentEditMsgModel != null && !TextUtils.isEmpty(
                                mTextContent.reply.contentEditMsgModel.getDisplayContent()
                            )
                        ) {
                            content = mTextContent.reply.contentEditMsgModel.getDisplayContent()
                        }
                        replyTv.movementMethod = LinkMovementMethod.getInstance()
                        val strUrls = StringUtils.getStrUrls(content)
                        val replySpan = SpannableStringBuilder()
                        replySpan.append(content)
                        if (strUrls.size > 0) {
                            for (url in strUrls) {
                                var fromIndex = 0
                                while (fromIndex >= 0) {
                                    fromIndex = content.indexOf(url, fromIndex)
                                    if (fromIndex >= 0) {
                                        replySpan.setSpan(
                                            StyleSpan(Typeface.BOLD),
                                            fromIndex,
                                            fromIndex + url.length,
                                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                        )
                                        replySpan.setSpan(
                                            NormalClickableSpan(true,
                                                ContextCompat.getColor(context, R.color.blue),
                                                NormalClickableContent(
                                                    NormalClickableContent.NormalClickableTypes.URL,
                                                    url
                                                ),
                                                object : NormalClickableSpan.IClick {
                                                    override fun onClick(view: View) {
                                                        SoftKeyboardUtils.getInstance()
                                                            .hideSoftKeyboard(context as Activity)
                                                        val intent = Intent(
                                                            context, WKWebViewActivity::class.java
                                                        )
                                                        intent.putExtra("url", url)
                                                        context.startActivity(intent)
                                                    }
                                                }),
                                            fromIndex,
                                            fromIndex + url.length,
                                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                        )
                                        fromIndex += url.length
                                    }
                                }
                            }
                        }

                        // emoji
                        val matcher = EmojiManager.getInstance().pattern.matcher(content)
                        while (matcher.find()) {
                            val start = matcher.start()
                            val end = matcher.end()
                            val emoji = content.substring(start, end)
                            val d = MoonUtil.getEmotDrawable(context, emoji, MoonUtil.SMALL_SCALE)
                            if (d != null) {
                                val span: AlignImageSpan =
                                    object : AlignImageSpan(d, ALIGN_CENTER) {
                                        override fun onClick(view: View) {}
                                    }
                                replySpan.setSpan(
                                    span,
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                            }
                        }
                        replyTv.text = replySpan
                    }
                }
                replyLayout.setOnClickListener {
                    shotTipsMsg(
                        mTextContent
                    )
                }
                replyTv.setOnClickListener {
                    shotTipsMsg(
                        mTextContent
                    )
                }
            }

        }
        // 链接识别
        val urls = StringUtils.getStrUrls(contentTv.text.toString())
        if (urls.size > 0) {
            showLinkInfo(uiChatMsgItemEntity, msgTimeView, linkView, from, urls[urls.size - 1])
        } else {
            linkView.visibility = View.GONE
            msgTimeView.visibility = View.VISIBLE
        }
        //setSelectableTextHelper(contentTv,0,true)
        selectText(contentTv, contentTvLayout, uiChatMsgItemEntity)
    }

    private var mSelectableTextHelper: SelectTextHelper? = null
    var selectText: String? = null
    private fun selectText(
        textView: TextView,
        fullLayout: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity
    ) {
//        textMsgBean = msgBean
        val menu = EndpointManager.getInstance()
            .invoke("favorite_item", uiChatMsgItemEntity.wkMsg)
        var favoritePopupMenu: ChatItemPopupMenu? = null
        if (menu != null) {
            favoritePopupMenu = menu as ChatItemPopupMenu
        }

        val builder = SelectTextHelper.Builder(textView, fullLayout) // 放你的textView到这里！！
            .setCursorHandleColor(ContextCompat.getColor(context, R.color.colorAccent)) // 游标颜色
            .setCursorHandleSizeInDp(22f) // 游标大小 单位dp
            .setSelectedColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_text_select_cursor
                )
            ) // 选中文本的颜色
            .setSelectAll(true) // 初次选中是否全选 default true
            .setScrollShow(false) // 滚动时是否继续显示 default true
            .setSelectedAllNoPop(true) // 已经全选无弹窗，设置了监听会回调 onSelectAllShowCustomPop 方法
            .setMagnifierShow(true) // 放大镜 default true
            .setSelectTextLength(2)// 首次选中文本的长度 default 2
            .setPopDelay(100)// 弹窗延迟时间 default 100毫秒
            .setFlame(uiChatMsgItemEntity.wkMsg.flame)
            .addItem(R.mipmap.msg_copy,
                R.string.copy,
                object : SelectTextHelper.Builder.onSeparateItemClickListener {
                    override fun onClick() {
                        EndpointManager.getInstance().invoke("chat_activity_touch", null)
                        // mSelectableTextHelper?.reset()
                        val cm =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val mClipData = ClipData.newPlainText("Label", selectText)
                        cm.setPrimaryClip(mClipData)
                        WKToastUtils.getInstance()
                            .showToastNormal(context.getString(R.string.copyed))
                    }
                }).addItem(
                R.mipmap.msg_forward,
                R.string.base_forward,
                object : SelectTextHelper.Builder.onSeparateItemClickListener {
                    override fun onClick() {
                        EndpointManager.getInstance().invoke("chat_activity_touch", null)
                        if (TextUtils.isEmpty(selectText)) return
                        val textContent = WKTextContent(selectText)
                        val chooseChatMenu =
                            ChooseChatMenu(
                                ChatChooseContacts { channelList: List<WKChannel>? ->
                                    if (!channelList.isNullOrEmpty()) {
                                        for (mChannel in channelList) {
                                            textContent.mentionAll = 0
                                            textContent.mentionInfo = null
                                            val setting = WKMsgSetting()
                                            setting.receipt = mChannel.receipt
//                                            setting.signal = 0
                                            WKIM.getInstance().msgManager.sendMessage(
                                                textContent,
                                                setting,
                                                mChannel.channelID,
                                                mChannel.channelType
                                            )
                                        }
                                        val viewGroup =
                                            (context as Activity).findViewById<View>(android.R.id.content)
                                                .rootView as ViewGroup
                                        Snackbar.make(
                                            viewGroup,
                                            context.getString(com.chat.base.R.string.str_forward),
                                            1000
                                        )
                                            .setAction(
                                                ""
                                            ) { }
                                            .show()
                                    }
                                },
                                textContent
                            )
                        EndpointManager.getInstance()
                            .invoke("chat_show_choose_chat", chooseChatMenu)
                    }

                }).setPopSpanCount(3) // 设置操作弹窗每行个数 default 5
        mSelectableTextHelper = builder.build()
//            .setPopStyle(
//                R.drawable.shape_color_4c4c4c_radius_8 /*操作弹窗背*/, R.mipmap.ic_arrow /*箭头图片*/
//            ) // 设置操作弹窗背景色、箭头图片
        if (favoritePopupMenu != null) {
            builder.addItem(
                favoritePopupMenu.imageResource,
                favoritePopupMenu.text,
                object : SelectTextHelper.Builder.onSeparateItemClickListener {
                    override fun onClick() {
                        EndpointManager.getInstance().invoke("chat_activity_touch", null)

                        if (!TextUtils.isEmpty(selectText)) {
                            val mMsg = WKMsg()
                            mMsg.type = WKContentType.WK_TEXT
                            mMsg.baseContentMsgModel = WKTextContent(selectText)
                            mMsg.from = uiChatMsgItemEntity.wkMsg.from
                            mMsg.channelID = uiChatMsgItemEntity.wkMsg.channelID
                            mMsg.channelType = uiChatMsgItemEntity.wkMsg.channelType
                            if (uiChatMsgItemEntity.wkMsg.remoteExtra != null && uiChatMsgItemEntity.wkMsg.remoteExtra.contentEditMsgModel != null) {
                                mMsg.remoteExtra.contentEditMsgModel = WKTextContent(selectText)
                            }
                            val chatAdapter = getAdapter() as ChatAdapter
                            uiChatMsgItemEntity.wkMsg.baseContentMsgModel.content = selectText
                            favoritePopupMenu.iPopupItemClick.onClick(
                                mMsg,
                                chatAdapter.conversationContext
                            )
                        }
                    }
                })
        }

        mSelectableTextHelper!!.setSelectListener(object : SelectTextHelper.OnSelectListener {
            override fun onClick(v: View?, originalContent: String?) {
            }


            /**
             * 长按回调
             */
            override fun onLongClick(v: View, local: FloatArray) {
                // showPopup(messageContent,textView,local)
            }

            override fun onTextSelected(content: String?) {
                selectText = content
            }


            /**
             * 弹窗关闭回调
             */
            override fun onDismiss() {}
            override fun onClickLink(clickableContent: NormalClickableSpan) {
                if (clickableContent.clickableContent.type == NormalClickableContent.NormalClickableTypes.URL) {
                    val intent = Intent(
                        context, WKWebViewActivity::class.java
                    )
                    intent.putExtra("url", clickableContent.clickableContent.content)
                    context.startActivity(intent)
                } else if (clickableContent.clickableContent.type == NormalClickableContent.NormalClickableTypes.Remind) {
                    val uid: String
                    var groupNo = ""
                    if (clickableContent.clickableContent.content.contains("|")) {
                        uid = clickableContent.clickableContent.content.split("|")[0]
                        groupNo = clickableContent.clickableContent.content.split("|")[1]
                    } else {
                        uid = clickableContent.clickableContent.content
                    }
                    val intent = Intent(context, UserDetailActivity::class.java)
                    intent.putExtra("uid", uid)
                    if (!TextUtils.isEmpty(groupNo)) intent.putExtra("groupID", groupNo)
                    context.startActivity(intent)
                } else {
                    val content = clickableContent.clickableContent.content
                    if (StringUtils.isMobile(content)) {
                        val chatAdapter = getAdapter() as ChatAdapter
                        chatAdapter.hideSoftKeyboard()
                        val bottomEntityList: MutableList<BottomEntity> = ArrayList()
                        val phoneTips = String.format(
                            context.getString(R.string.phone_tips),
                            context.getString(R.string.app_name)
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                content,
                                phoneTips,
                                R.color.colorDark,
                                R.color.color999
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.copy),
                                R.color.colorDark
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.call),
                                R.color.colorDark
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.add_to_phone_book),
                                R.color.colorDark
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.str_search),
                                R.color.colorDark
                            )
                        )
                        WKDialogUtils.getInstance()
                            .showCommonBottomViewDialog(
                                context,
                                bottomEntityList,
                                object : CommonBottomView.IBottomClick {
                                    override fun onClick(
                                        position: Int,
                                        bottomEntity: BottomEntity?
                                    ) {
                                        if (position == 1) {
                                            val cm =
                                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val mClipData = ClipData.newPlainText("Label", content)
                                            cm.setPrimaryClip(mClipData)
                                            WKToastUtils.getInstance()
                                                .showToastNormal(context.getString(R.string.copyed))
                                        } else if (position == 2) {
                                            val intent =
                                                Intent(
                                                    Intent.ACTION_CALL,
                                                    Uri.parse("tel:$content")
                                                )
                                            context.startActivity(intent)
                                        } else if (position == 3) {
                                            val addIntent = Intent(
                                                Intent.ACTION_INSERT,
                                                Uri.withAppendedPath(
                                                    Uri.parse("content://com.android.contacts"),
                                                    "contacts"
                                                )
                                            )
                                            addIntent.type = "vnd.android.cursor.dir/person"
                                            addIntent.type = "vnd.android.cursor.dir/contact"
                                            addIntent.type = "vnd.android.cursor.dir/raw_contact"
                                            addIntent.putExtra(
                                                ContactsContract.Intents.Insert.NAME,
                                                ""
                                            )
                                            addIntent.putExtra(
                                                ContactsContract.Intents.Insert.PHONE,
                                                content
                                            )
                                            context.startActivity(addIntent)
                                        } else if (position == 4) {
                                            if (uiChatMsgItemEntity.iLinkClick != null)
                                                uiChatMsgItemEntity.iLinkClick.onShowSearchUser(
                                                    content
                                                )
                                            // if (iLinkClick != null) iLinkClick.onShowSearchUser(content)
                                        }
                                    }
                                })
                        return
                    }
                    if (StringUtils.isEmail(content)) {
                        val chatAdapter = getAdapter() as ChatAdapter
                        chatAdapter.hideSoftKeyboard()
                        val bottomEntityList: MutableList<BottomEntity> = ArrayList()
                        val emailTips = String.format(
                            context.getString(R.string.email_tips),
                            context.getString(R.string.app_name)
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                content,
                                emailTips,
                                R.color.colorDark,
                                R.color.color999
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.copy),
                                R.color.colorDark
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.str_search),
                                R.color.colorDark
                            )
                        )
                        bottomEntityList.add(
                            BottomEntity(
                                context.getString(R.string.send_email),
                                R.color.colorDark
                            )
                        )
                        WKDialogUtils.getInstance()
                            .showCommonBottomViewDialog(
                                context,
                                bottomEntityList,
                                object : CommonBottomView.IBottomClick {
                                    override fun onClick(
                                        position: Int,
                                        bottomEntity: BottomEntity?
                                    ) {
                                        if (position == 1) {
                                            val cm =
                                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val mClipData = ClipData.newPlainText("Label", content)
                                            cm.setPrimaryClip(mClipData)
                                            WKToastUtils.getInstance()
                                                .showToastNormal(context.getString(R.string.copyed))
                                        } else if (position == 2) {
                                            if (uiChatMsgItemEntity.iLinkClick != null)
                                                uiChatMsgItemEntity.iLinkClick.onShowSearchUser(
                                                    content
                                                )
                                            // if (iLinkClick != null) iLinkClick.onShowSearchUser(content)
                                        } else {
                                            val uri = Uri.parse("mailto:$content")
                                            val email = arrayOf(content)
                                            val intent = Intent(Intent.ACTION_SENDTO, uri)
                                            intent.putExtra(Intent.EXTRA_CC, email) // 抄送人
                                            intent.putExtra(Intent.EXTRA_SUBJECT, "") // 主题
                                            intent.putExtra(Intent.EXTRA_TEXT, "") // 正文
                                            context.startActivity(Intent.createChooser(intent, ""))
                                        }
                                    }

                                })
                        return
                    }
                }
            }


            /**
             * 全选显示自定义弹窗回调
             */
            override fun onSelectAllShowCustomPop(local: FloatArray) {
                showPopup(uiChatMsgItemEntity, textView, local)
            }

            /**
             * 重置回调
             */
            override fun onReset() {
            }

            /**
             * 解除自定义弹窗回调
             */
            override fun onDismissCustomPop() {
            }

            /**
             * 是否正在滚动回调
             */
            override fun onScrolling() {
            }
        })


    }

    private fun showPopup(uiChatMsgItemEntity: WKUIChatMsgItemEntity, v: View, local: FloatArray) {
        val mMsgConfig: MsgConfig = getMsgConfig(uiChatMsgItemEntity.wkMsg.type)
        var isShowReaction = false
        val `object` = EndpointManager.getInstance()
            .invoke(
                "is_show_reaction",
                CanReactionMenu(uiChatMsgItemEntity.wkMsg, mMsgConfig)
            )
        if (`object` != null) {
            isShowReaction = `object` as Boolean
        }
        if (uiChatMsgItemEntity.wkMsg.flame == 1) isShowReaction = false
        val finalIsShowReaction = isShowReaction
        showChatPopup(
            uiChatMsgItemEntity.wkMsg,
            v,
            local,
            finalIsShowReaction,
            getPopupList(uiChatMsgItemEntity.wkMsg)
        )
    }

    //    private fun setSelectableTextHelper(
//        textView: TextView?,
//        position: Int,
//        isEmoji: Boolean
//    ) {
//       val selectableTextHelper = SelectTextHelper.Builder(textView)
//            .setCursorHandleColor(
//                context.getColor(R.color.blue)
//            )
//            .setCursorHandleSizeInDp(16f)
//            .setSelectedColor(
//                context.getColor(R.color.blue)
//            )
//            .setSelectAll(true)
//            .setIsEmoji(isEmoji)
//            .setScrollShow(false)
//            .setSelectedAllNoPop(true)
//            .setMagnifierShow(false)
//            .build()
//        selectableTextHelper.setSelectListener(object : SelectTextHelper.OnSelectListener {
//            override fun onClick(v: View) {}
//            override fun onLongClick(v: View) {}
//            override fun onTextSelected(content: CharSequence) {
//                val selectedText = content.toString()
//               // msg.setSelectText(selectedText)
////                if (onItemClickListener != null) {
////                    onItemClickListener.onTextSelected(msgArea, position, msg)
////                }
//            }
//
//            override fun onDismiss() {
////                msg.setSelectText(msg.getExtra())
//            }
//
//            override fun onClickUrl(url: String) {}
//            override fun onSelectAllShowCustomPop() {}
//            override fun onReset() {
////                msg.setSelectText(null)
////                msg.setSelectText(msg.getExtra())
//            }
//
//            override fun onDismissCustomPop() {}
//            override fun onScrolling() {}
//        })
//    }
    override val itemViewType: Int
        get() = WKContentType.WK_TEXT


    private fun shotTipsMsg(mTextContent: WKTextContent) {
        var clientMsgNo = mTextContent.reply.message_id
        val mMsg =
            WKIM.getInstance().msgManager.getWithMessageID(mTextContent.reply.message_id)
        if (mMsg != null) {
            clientMsgNo = mMsg.clientMsgNO
        }
        (Objects.requireNonNull(getAdapter()) as ChatAdapter).showTipsMsg(clientMsgNo)
    }

    private fun showLinkInfo(
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        msgTimeStatusView: View,
        parentView: LinearLayout,
        from: WKChatIteMsgFromType,
        url: String
    ) {
        uiChatMsgItemEntity.isUpdateStatus = false
        val linkView = LayoutInflater.from(context)
            .inflate(R.layout.chat_text_link_desc_layout, parentView, false)
        val msgTimeView = linkView.findViewById<View>(R.id.msgTimeView)
        setMsgTimeAndStatus(uiChatMsgItemEntity, msgTimeView, from)
        val titleTv = linkView.findViewById<TextView>(R.id.linkTitleTv)
        val nameTv = linkView.findViewById<TextView>(R.id.linkNameTv)
        val contentTv = linkView.findViewById<TextView>(R.id.linkContentTv)
        val logoIv = linkView.findViewById<AppCompatImageView>(R.id.linkLogoIv)
        val coverIv = linkView.findViewById<AppCompatImageView>(R.id.linkCoverIv)
        if (from == WKChatIteMsgFromType.SEND) {
            contentTv.setTextColor(ContextCompat.getColor(context, R.color.send_text_color))
            nameTv.setTextColor(ContextCompat.getColor(context, R.color.send_text_color))
            titleTv.setTextColor(ContextCompat.getColor(context, R.color.send_text_color))
        } else {
            contentTv.setTextColor(ContextCompat.getColor(context, R.color.receive_text_color))
            nameTv.setTextColor(ContextCompat.getColor(context, R.color.receive_text_color))
            titleTv.setTextColor(ContextCompat.getColor(context, R.color.receive_text_color))
        }
        val jsonStr = WKSharedPreferencesUtil.getInstance().getSP(url)
        var jsonObject: JSONObject? = null
        try {
            if (!TextUtils.isEmpty(jsonStr)) jsonObject = JSONObject(jsonStr)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (jsonObject == null) {
            parentView.visibility = View.GONE
            msgTimeStatusView.visibility = View.VISIBLE
        } else {
            val title = jsonObject.optString("title")
            val content = jsonObject.optString("content")
            val coverURL = jsonObject.optString("coverURL")
            val logo = jsonObject.optString("logo")
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                titleTv.text = title
                contentTv.text = content
                Glide.with(context).asBitmap().load(logo)
                    .into(object : CustomTarget<Bitmap?>(SIZE_ORIGINAL, SIZE_ORIGINAL) {
                        override fun onResourceReady(
                            resource: Bitmap, transition: Transition<in Bitmap?>?
                        ) {
                            logoIv.visibility = View.VISIBLE
                            logoIv.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            logoIv.visibility = View.GONE
                        }
                    })
                // GlideUtils.getInstance().showImg(getContext(), logo, logoIv);
                if (!TextUtils.isEmpty(coverURL)) {
                    // GlideUtils.getInstance().showImg(getContext(), coverURL.replaceAll(" ", ""), coverIv);
                    Glide.with(context).asBitmap().load(coverURL.replace(" ".toRegex(), ""))
                        .into(object : CustomTarget<Bitmap?>(SIZE_ORIGINAL, SIZE_ORIGINAL) {
                            override fun onResourceReady(
                                resource: Bitmap, transition: Transition<in Bitmap?>?
                            ) {
                                coverIv.visibility = View.VISIBLE
                                coverIv.setImageBitmap(resource)
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                                coverIv.visibility = View.GONE
                            }

                        })
                } else coverIv.visibility = View.GONE
                val strings = url.split("\\.").toTypedArray()
                if (strings.size > 1) {
                    val stringBuffer = StringBuffer()
                    for (i in 1 until strings.size) {
                        if (!TextUtils.isEmpty(stringBuffer)) stringBuffer.append(".")
                        stringBuffer.append(strings[i])
                    }
                    nameTv.text = stringBuffer
                }
                parentView.removeAllViews()
                parentView.addView(linkView)
                parentView.visibility = View.VISIBLE
                msgTimeStatusView.visibility = View.GONE
            } else {
                parentView.visibility = View.GONE
                msgTimeStatusView.visibility = View.VISIBLE
            }
        }
    }

    override fun resetCellListener(
        position: Int,
        parentView: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        from: WKChatIteMsgFromType
    ) {
        super.resetCellListener(position, parentView, uiChatMsgItemEntity, from)
        val linkView = parentView.findViewById<LinearLayout>(R.id.linkView)
        if (linkView != null && linkView.childCount > 0) {
            val msgTimeView = linkView.getChildAt(0)
            setMsgTimeAndStatus(uiChatMsgItemEntity, msgTimeView, from)
        }
    }

    override fun resetCellBackground(
        parentView: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        from: WKChatIteMsgFromType
    ) {
        super.resetCellBackground(parentView, uiChatMsgItemEntity, from)
        val contentTvLayout = parentView.findViewById<BubbleLayout>(R.id.contentTvLayout)
        val textContentLayout = parentView.findViewById<View>(R.id.textContentLayout)
        val msgTimeView = parentView.findViewById<View>(R.id.msgTimeView)
        // 这里要指定文本宽度 - padding的距离
        textContentLayout.layoutParams.width = getViewWidth(from, uiChatMsgItemEntity)
        val bgType = getMsgBgType(
            uiChatMsgItemEntity.previousMsg,
            uiChatMsgItemEntity.wkMsg,
            uiChatMsgItemEntity.nextMsg
        )
        contentTvLayout.setAll(bgType, from, WKContentType.WK_TEXT)
        if (textContentLayout.layoutParams.width < msgTimeView.layoutParams.width) {
            textContentLayout.layoutParams.width = msgTimeView.layoutParams.width
        }
    }
}