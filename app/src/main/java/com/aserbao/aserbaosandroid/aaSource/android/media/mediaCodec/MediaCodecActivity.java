package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.DecodeShowVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.DecodeVideoShowGlSurfaceViewActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class MediaCodecActivity extends BaseRecyclerViewActivity {


    public static final int INT_ENCODE = 0;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("显示MediaCodec解码的本地视频", DecodeShowVideoActivity.class,INT_ENCODE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("用GlSurfaceView显示MediaCodec解码的本地视频", DecodeVideoShowGlSurfaceViewActivity.class,INT_ENCODE));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}