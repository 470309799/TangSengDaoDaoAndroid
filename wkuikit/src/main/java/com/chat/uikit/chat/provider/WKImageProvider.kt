package com.chat.uikit.chat.provider

import android.app.Activity
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.fastjson.JSONObject
import com.chat.base.config.WKApiConfig
import com.chat.base.endpoint.EndpointManager
import com.chat.base.endpoint.entity.ChatChooseContacts
import com.chat.base.endpoint.entity.ChooseChatMenu
import com.chat.base.glide.GlideUtils
import com.chat.base.msg.ChatAdapter
import com.chat.base.msgitem.WKChatBaseProvider
import com.chat.base.msgitem.WKChatIteMsgFromType
import com.chat.base.msgitem.WKContentType
import com.chat.base.msgitem.WKUIChatMsgItemEntity
import com.chat.base.okgo.OkGoUploadOrDownloadProgress
import com.chat.base.okgo.OkGoUploadOrDownloadProgress.IProgress
import com.chat.base.ui.Theme
import com.chat.base.ui.components.FilterImageView
import com.chat.base.ui.components.SecretDeleteTimer
import com.chat.base.utils.AndroidUtilities
import com.chat.base.utils.ImageUtils
import com.chat.base.utils.LayoutHelper
import com.chat.base.utils.WKDialogUtils
import com.chat.base.utils.WKDialogUtils.IImagePopupListener
import com.chat.base.utils.WKTimeUtils
import com.chat.base.utils.WKToastUtils
import com.chat.base.views.CircularProgressView
import com.chat.base.views.CustomImageViewerPopup.IImgPopupMenu
import com.chat.base.views.blurview.ShapeBlurView
import com.chat.uikit.R
import com.google.android.material.snackbar.Snackbar
import com.xinbida.wukongim.WKIM
import com.xinbida.wukongim.entity.WKCMD
import com.xinbida.wukongim.entity.WKCMDKeys
import com.xinbida.wukongim.entity.WKChannel
import com.xinbida.wukongim.entity.WKChannelType
import com.xinbida.wukongim.entity.WKMsg
import com.xinbida.wukongim.message.type.WKMsgContentType
import com.xinbida.wukongim.msgmodel.WKImageContent
import java.io.File
import java.text.DecimalFormat
import java.util.Objects

class WKImageProvider : WKChatBaseProvider() {
    override fun getChatViewItem(parentView: ViewGroup, from: WKChatIteMsgFromType): View? {
        return LayoutInflater.from(context).inflate(R.layout.chat_item_img, parentView, false)
    }

    override fun setData(
        adapterPosition: Int,
        parentView: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        from: WKChatIteMsgFromType
    ) {
        val contentLayout = parentView.findViewById<LinearLayout>(R.id.contentLayout)

        val imgMsgModel = uiChatMsgItemEntity.wkMsg.baseContentMsgModel as WKImageContent
        val imageView = parentView.findViewById<FilterImageView>(R.id.imageView)
        imageView.setAllCorners(10)
        val progressTv = parentView.findViewById<TextView>(R.id.progressTv)
        val progressView = parentView.findViewById<CircularProgressView>(R.id.progressView)
        progressView.setProgColor(Theme.colorAccount)
        val imageLayout = parentView.findViewById<View>(R.id.imageLayout)
        val otherLayout = parentView.findViewById<FrameLayout>(R.id.otherLayout)
        val deleteTimer = SecretDeleteTimer(context)
        val blurView = parentView.findViewById<ShapeBlurView>(R.id.blurView)
        otherLayout.removeAllViews()
        otherLayout.addView(deleteTimer, LayoutHelper.createFrame(35, 35, Gravity.CENTER))
        contentLayout.gravity =
            if (from == WKChatIteMsgFromType.RECEIVED) Gravity.START else Gravity.END
        val layoutParams = imageView.layoutParams as FrameLayout.LayoutParams
        val blurViewLayoutParams = blurView.layoutParams as FrameLayout.LayoutParams
        val ints = ImageUtils.getInstance()
            .getImageWidthAndHeightToTalk(imgMsgModel.width, imgMsgModel.height)

        var showUrl: String
        if (uiChatMsgItemEntity.wkMsg.localExtraMap != null && uiChatMsgItemEntity.wkMsg.localExtraMap.containsKey(
                "showURL"
            )
        ) {
            showUrl = uiChatMsgItemEntity.wkMsg.localExtraMap["showURL"] as String
        } else {
            if (!TextUtils.isEmpty(imgMsgModel.localPath)) {
                showUrl = imgMsgModel.localPath
                val file = File(showUrl)
                if (!file.exists() || file.length() == 0L) {
                    //如果本地文件被删除就显示网络图片
                    showUrl = WKApiConfig.getShowUrl(imgMsgModel.url)
                }
            } else {
                showUrl = WKApiConfig.getShowUrl(imgMsgModel.url)
            }
            if (uiChatMsgItemEntity.wkMsg.localExtraMap == null) {
                uiChatMsgItemEntity.wkMsg.localExtraMap = HashMap<String, Objects>()
            }
            uiChatMsgItemEntity.wkMsg.localExtraMap["showURL"] = showUrl
        }


        blurView.visibility = if (uiChatMsgItemEntity.wkMsg.flame == 1)
            View.VISIBLE
        else View.GONE
        if (uiChatMsgItemEntity.wkMsg.flame == 1) {
            otherLayout.visibility = View.VISIBLE
            deleteTimer.setSize(35)
            if (uiChatMsgItemEntity.wkMsg.viewedAt > 0 && uiChatMsgItemEntity.wkMsg.flameSecond > 0) {
                deleteTimer.setDestroyTime(
                    uiChatMsgItemEntity.wkMsg.clientMsgNO,
                    uiChatMsgItemEntity.wkMsg.flameSecond,
                    uiChatMsgItemEntity.wkMsg.viewedAt,
                    false
                )
            }
        } else {
            otherLayout.visibility = View.GONE
        }

        GlideUtils.getInstance().showImg(context, showUrl, ints[0], ints[1], imageView)
        val tempShowImgUrl = showUrl
        imageView.setOnClickListener {
            if (uiChatMsgItemEntity.wkMsg.flame == 1 && uiChatMsgItemEntity.wkMsg.viewed == 0) {
                for (i in 0 until getAdapter()!!.data.size) {
                    if (getAdapter()!!.data[i].wkMsg.clientMsgNO.equals(uiChatMsgItemEntity.wkMsg.clientMsgNO)) {
                        getAdapter()!!.data[i].wkMsg.viewed = 1
                        getAdapter()!!.data[i].wkMsg.viewedAt =
                            WKTimeUtils.getInstance().currentMills
                        getAdapter()!!.notifyItemChanged(adapterPosition)
                        uiChatMsgItemEntity.wkMsg.viewedAt = getAdapter()!!.data[i].wkMsg.viewedAt
                        WKIM.getInstance().msgManager.updateViewedAt(
                            1,
                            getAdapter()!!.data[i].wkMsg.viewedAt,
                            getAdapter()!!.data[i].wkMsg.clientMsgNO
                        )
                        break
                    }
                }

            }
            showImages(
                uiChatMsgItemEntity.wkMsg,
                tempShowImgUrl,
                imageView
            )
        }

        val layoutParams1 = imageLayout.layoutParams as LinearLayout.LayoutParams
        if (uiChatMsgItemEntity.wkMsg.flame == 1) {
            layoutParams.height = AndroidUtilities.dp(150f)
            layoutParams.width = AndroidUtilities.dp(150f)
            blurViewLayoutParams.height = AndroidUtilities.dp(150f)
            blurViewLayoutParams.width = AndroidUtilities.dp(150f)
            layoutParams1.height = AndroidUtilities.dp(150f)
            layoutParams1.width = AndroidUtilities.dp(150f)
        } else {
            layoutParams.height = ints[1]
            layoutParams.width = ints[0]
            blurViewLayoutParams.height = ints[1]
            blurViewLayoutParams.width = ints[0]
            layoutParams1.height = ints[1]
            layoutParams1.width = ints[0]
        }
        imageView.layoutParams = layoutParams
        blurView.layoutParams = blurViewLayoutParams
//        if (uiChatMsgItemEntity.wkMsg.channelType != WKChannelType.PERSONAL && from != WKChatIteMsgFromType.SEND) {
//            layoutParams1.leftMargin = AndroidUtilities.dp(10f)
//            layoutParams1.rightMargin = AndroidUtilities.dp(10f)
//        }
        imageLayout.layoutParams = layoutParams1

        //设置上传进度
        if (TextUtils.isEmpty(imgMsgModel.url)) {
            OkGoUploadOrDownloadProgress.getInstance()
                .addProgress(uiChatMsgItemEntity.wkMsg.clientSeq, object : IProgress {
                    override fun onProgress(tag: Any, progress: Float) {
                        if (tag is Long) {
                            if (tag == uiChatMsgItemEntity.wkMsg.clientSeq) {
                                val df = DecimalFormat(".00")
                                val pg = (progress * 100).toInt()
                                progressView.progress = pg
                                progressTv.text =
                                    String.format("%s%%", df.format((progress * 100).toDouble()))
                                if (progress >= 1.0) {
                                    OkGoUploadOrDownloadProgress.getInstance()
                                        .removeProgress(uiChatMsgItemEntity.wkMsg.clientSeq)
                                    progressTv.visibility = View.GONE
                                    progressView.visibility = View.GONE
                                    deleteTimer.visibility = View.VISIBLE
                                } else {
                                    progressView.visibility = View.VISIBLE
                                    progressTv.visibility = View.VISIBLE
                                    deleteTimer.visibility = View.GONE
                                }
                            }
                        }
                    }

                    override fun onSuccess(tag: Any, path: String) {}
                })
        }
        addLongClick(imageView, uiChatMsgItemEntity.wkMsg)
    }

    override val itemViewType: Int
        get() = WKMsgContentType.WK_IMAGE


    //查看大图
    private fun showImages(mMsg: WKMsg, uri: String, imageView: ImageView) {
        val flame = mMsg.flame
        val list: List<WKUIChatMsgItemEntity> = getAdapter()!!.data
        val imgList: MutableList<ImageView?> = ArrayList()
        val showImgList: MutableList<WKMsg> = ArrayList()
        val tempImgList: MutableList<Any?> = ArrayList()
        if (flame == 1) {
            tempImgList.add(uri)
            imgList.add(imageView)
        } else
            run {
                var i = 0
                val size = list.size
                while (i < size) {
                    if (list[i].wkMsg != null && list[i].wkMsg.type == WKContentType.WK_IMAGE && list[i].wkMsg.remoteExtra.revoke == 0 && list[i].wkMsg.isDeleted == 0 && list[i].wkMsg.flame == 0
                    ) {
                        val imgMsgModel =
                            list[i].wkMsg.baseContentMsgModel as WKImageContent
                        var showUrl: String
                        if (!TextUtils.isEmpty(imgMsgModel.localPath)) {
                            showUrl = imgMsgModel.localPath
                            val file = File(showUrl)
                            if (!file.exists() || file.length() == 0L) {
                                //如果本地文件被删除就显示网络图片
                                showUrl = WKApiConfig.getShowUrl(imgMsgModel.url)
                            }
                        } else {
                            showUrl = WKApiConfig.getShowUrl(imgMsgModel.url)
                        }
                        showImgList.add(list[i].wkMsg)
                        val itemView =
                            getAdapter()!!.recyclerView.layoutManager!!.findViewByPosition(i)
                        if (itemView != null) {
                            val imageView1 =
                                itemView.findViewById<ImageView>(R.id.imageView)
                            imgList.add(imageView1)
                        } else imgList.add(null)
                        if (!TextUtils.isEmpty(showUrl)) {
                            tempImgList.add(showUrl)
                        }
                    }
                    i++
                }
            }

        if (tempImgList.size == 0) return
        var index = 0
        for (i in tempImgList.indices) {
            if (!TextUtils.isEmpty(uri) && tempImgList[i] != null && tempImgList[i] == uri) {
                index = i
                break
            }
        }
        imageView.tag = flame
        val popupView = WKDialogUtils.getInstance().showImagePopup(
            context,
            mMsg,
            tempImgList,
            imgList,
            imageView,
            index,
            null,
            object : IImgPopupMenu {
                override fun onForward(position: Int) {
                    val mMessageContent = showImgList[position].baseContentMsgModel
                    EndpointManager.getInstance().invoke(
                        "chat_show_choose_chat",
                        ChooseChatMenu(
                            ChatChooseContacts { list1: List<WKChannel>? ->
                                if (!list1.isNullOrEmpty()) {
                                    for (mChannel in list1) {
                                        WKIM.getInstance().msgManager.sendMessage(
                                            mMessageContent,
                                            mChannel.channelID,
                                            mChannel.channelType
                                        )
                                    }
                                    val viewGroup =
                                        (context as Activity).findViewById<View>(android.R.id.content)
                                            .rootView as ViewGroup
                                    Snackbar.make(
                                        viewGroup,
                                        context.getString(R.string.is_forward),
                                        1000
                                    )
                                        .setAction(
                                            ""
                                        ) { }
                                        .show()
                                }
                            },
                            mMessageContent
                        )
                    )
                }

                override fun onFavorite(position: Int) {
                    collect(showImgList[position])
                }

                override fun onShowInChat(position: Int) {
                    (Objects.requireNonNull(getAdapter()) as ChatAdapter).showTipsMsg(
                        showImgList[position].clientMsgNO
                    )
                }
            },
            object : IImagePopupListener {
                override fun onShow() {
                    val adapter = getAdapter() as ChatAdapter
                    adapter.conversationContext.onViewPicture(true)
                }

                override fun onDismiss() {
                    val adapter = getAdapter() as ChatAdapter
                    adapter.conversationContext.onViewPicture(false)
                    WKIM.getInstance().msgManager.removeRefreshMsgListener("show_chat_img")
                }
            })
        WKIM.getInstance().cmdManager.addCmdListener(
            "show_chat_img"
        ) { cmd: WKCMD ->
            if (!TextUtils.isEmpty(cmd.cmdKey)) {
                if (cmd.cmdKey == WKCMDKeys.wk_messageRevoke) {
                    if (cmd.paramJsonObject != null && cmd.paramJsonObject.has("message_id")) {
                        val msgID = cmd.paramJsonObject.optString("message_id")
                        val mMsg1 =
                            WKIM.getInstance().msgManager.getWithMessageID(msgID)
                        if (mMsg1 != null) {
                            for (msg in showImgList) {
                                if (msg.clientMsgNO == mMsg1.clientMsgNO) {
                                    WKToastUtils.getInstance()
                                        .showToast(context.getString(R.string.msg_revoked))
                                    popupView.dismiss()
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun collect(mMsg: WKMsg) {
        val jsonObject = JSONObject()
        val mImageContent = mMsg.baseContentMsgModel as WKImageContent
        jsonObject["content"] = WKApiConfig.getShowUrl(mImageContent.url)
        jsonObject["width"] = mImageContent.width
        jsonObject["height"] = mImageContent.height
        val hashMap = HashMap<String, Any>()
        hashMap["type"] = mMsg.type
        var uniqueKey = mMsg.messageID
        if (TextUtils.isEmpty(uniqueKey)) uniqueKey = mMsg.clientMsgNO
        hashMap["unique_key"] = uniqueKey
        if (mMsg.from != null) {
            hashMap["author_uid"] = mMsg.from.channelID
            hashMap["author_name"] = mMsg.from.channelName
        }
        hashMap["payload"] = jsonObject
        hashMap["context"] = context
        EndpointManager.getInstance().invoke("collection_add_collect", hashMap)
    }


    override fun resetCellListener(
        position: Int,
        parentView: View,
        uiChatMsgItemEntity: WKUIChatMsgItemEntity,
        from: WKChatIteMsgFromType
    ) {
        super.resetCellListener(position, parentView, uiChatMsgItemEntity, from)
        val imageView = parentView.findViewById<FilterImageView>(R.id.imageView)
        addLongClick(imageView, uiChatMsgItemEntity.wkMsg)
    }
}