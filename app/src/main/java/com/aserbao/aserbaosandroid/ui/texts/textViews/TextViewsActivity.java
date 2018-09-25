package com.aserbao.aserbaosandroid.ui.texts.textViews;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.HtmlTextActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.picTxt.PicTxtActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.shadowText.TextShadowActivity;

public class TextViewsActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("textView的阴影效果", TextShadowActivity.class));
        mClassBeen.add(new ClassBean("图文混排", PicTxtActivity.class));
        mClassBeen.add(new ClassBean("TextView显示Html", HtmlTextActivity.class));
    }
}