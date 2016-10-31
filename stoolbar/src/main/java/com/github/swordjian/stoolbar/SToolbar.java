package com.github.swordjian.stoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * @author SwordJian
 */
public class SToolbar extends RelativeLayout implements View.OnClickListener {

    public View contentView;

    public android.support.v7.widget.Toolbar v7ToolBar; //support.v7的toolBar

    private TextView title; //居中的大标题
    private TextView subtitle; //小标题，一般还用作关闭的点击
    private View btn_left;//左边按钮，包含图片和文字
    private ImageView btn_left_img;//左边按钮的图标
    private TextView btn_left_str;//左边按钮的文字
    private View btn_right;//右边按钮，包含图片和文字
    private ImageView btn_right_img;//右边按钮图标
    private TextView btn_right_str;//右边按钮的文字

    private OnButtonClicListener mOnButtonListener;
    private OnLeftClickListener mOnLeftClickListener;
    private OnRightClickListener mOnRightClickListener;
    private OnSubTitleClickListener mOnSubTitleClickListener;


    public SToolbar(Context context) {
        super(context);
        init();
    }

    public SToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        init2(attrs);
    }

    public SToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        init2(attrs);
    }

    private void init() {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.s_toolbar_layout, null);
        addView(contentView);
        findView();

        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        subtitle.setOnClickListener(this);
    }

    public void init2(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SToolbar);
        int lSPopupTheme = a.getResourceId(R.styleable.SToolbar_SPopupTheme, 0);
        if (lSPopupTheme > 0) {
            v7ToolBar.setPopupTheme(lSPopupTheme);
        }
        a.recycle();//必须回收
    }

    private void findView() {
        v7ToolBar = (android.support.v7.widget.Toolbar) contentView.findViewById(R.id.v7toolbar);
        title = (TextView) contentView.findViewById(R.id.title);
        subtitle = (TextView) contentView.findViewById(R.id.subtitle);
        btn_left = contentView.findViewById(R.id.btn_left);
        btn_left_img = (ImageView) contentView.findViewById(R.id.btn_left_img);
        btn_left_str = (TextView) contentView.findViewById(R.id.btn_left_str);
        btn_right = contentView.findViewById(R.id.btn_right);
        btn_right_img = (ImageView) contentView.findViewById(R.id.btn_right_img);
        btn_right_str = (TextView) contentView.findViewById(R.id.btn_right_str);
    }

    public void setTextColor(@ColorInt int color) {
        title.setTextColor(color);
        subtitle.setTextColor(color);
        btn_left_str.setTextColor(color);
    }

    public void setTitleColor(@ColorInt int color) {
        title.setTextColor(color);
    }

    public void setSubTitleColor(@ColorInt int color) {
        subtitle.setTextColor(color);
    }

    public void setBtnLeftStrColor(@ColorInt int color) {
        btn_left_str.setTextColor(color);
    }

    /**
     * @param text 如果文字太长，需要在初始化按钮之后再设置Title
     */
    public void setTitle(CharSequence text) {
        title.setText(text);
//        title.post(new Runnable() {
//            @Override
//            public void run() {
//                int padding = 0;
//                if (btn_left.getWidth() >= btn_right.getWidth()) {
//                    padding = btn_left.getWidth();
//                } else {
//                    padding = btn_right.getWidth();
//                }
//                title.setPadding(padding, 0, padding, 0);
//                title.getExtendedPaddingTop();
//                if (title.getLineCount() >= 2) {
//                    float defaultTextSize = title.getTextSize();
//                    title.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, title.getHeight(), getResources().getDisplayMetrics()));
//                    if (defaultTextSize < title.getTextSize()) {
//                        title.setTextSize(defaultTextSize);
//                    }
//                }
//            }
//        });
    }

    /**
     * @param resid 如果文字太长，需要在初始化按钮之后再设置Title
     */
    public void setTitle(@StringRes int resid) {
        title.setText(resid);
//        title.post(new Runnable() {
//            @Override
//            public void run() {
//                int padding = 0;
//                if (btn_left.getWidth() >= btn_right.getWidth()) {
//                    padding = btn_left.getWidth();
//                } else {
//                    padding = btn_right.getWidth();
//                }
//                title.setPadding(padding, 0, padding, 0);
//                title.getExtendedPaddingTop();
//                if (title.getLineCount() >= 2) {
//                    title.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, title.getHeight(), getResources().getDisplayMetrics()));
//                }
//            }
//        });
    }

    public void setSubTitle(CharSequence text) {
        subtitle.setText(text);
    }

    public void setSubTitle(@StringRes int resid) {
        subtitle.setText(resid);
    }

    public void setBtnLeftImage(@DrawableRes int resId) {
        if (resId == 0) {
            btn_left_img.setImageBitmap(null);
        } else {
            btn_left_img.setImageResource(resId);
        }
    }

    public void setBtnLeftText(CharSequence text) {
        btn_left_str.setText(text);
    }

    public void setBtnRightImage(@DrawableRes int resId) {
        if (resId == 0) {
            btn_right_img.setImageBitmap(null);
        } else {
            btn_right_img.setImageResource(resId);
        }
    }

    public void setBtnRightText(CharSequence text) {
        btn_right_str.setText(text);
    }

    public View getContentView() {
        return contentView;
    }

    public android.support.v7.widget.Toolbar getV7ToolBar() {
        return v7ToolBar;
    }

    public TextView getSubtitle() {
        return subtitle;
    }

    public TextView getTitle() {
        return title;
    }

    public ImageView getBtnLeftImg() {
        return btn_left_img;
    }

    public TextView getBtnLeftStr() {
        return btn_right_str;
    }

    public ImageView getBtnRightImg() {
        return btn_right_img;
    }

    public TextView getBtnRightStr() {
        return btn_right_str;
    }

    public void setOnButtonClicListener(OnButtonClicListener listener) {
        mOnButtonListener = listener;
    }

    public void setOnLeftClickListener(OnLeftClickListener listener) {
        mOnLeftClickListener = listener;
    }

    public void seOnRightClickListener(OnRightClickListener listener) {
        mOnRightClickListener = listener;
    }

    public void seOnSubTitleClickListener(OnSubTitleClickListener listener) {
        mOnSubTitleClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_left) {
            if (mOnButtonListener != null) {
                mOnButtonListener.onClickLeft(v);
            }
            if (mOnLeftClickListener != null) {
                mOnLeftClickListener.onClickLeft(v);
            }

        } else if (i == R.id.btn_right) {
            if (mOnButtonListener != null) {
                mOnButtonListener.onClickRight(v);
            }
            if (mOnRightClickListener != null) {
                mOnRightClickListener.onClickRight(v);
            }

            if (mOnButtonListener != null) {
                mOnButtonListener.onClickSubTitle(v);
            }
            if (mOnSubTitleClickListener != null) {
                mOnSubTitleClickListener.onClickSubTitle(v);
            }

        } else if (i == R.id.subtitle) {
            if (mOnButtonListener != null) {
                mOnButtonListener.onClickSubTitle(v);
            }
            if (mOnSubTitleClickListener != null) {
                mOnSubTitleClickListener.onClickSubTitle(v);
            }

        }
    }

    public static interface OnButtonClicListener {
        void onClickLeft(View v);

        void onClickRight(View v);

        void onClickSubTitle(View v);
    }

    public static interface OnLeftClickListener {
        void onClickLeft(View v);
    }

    public static interface OnRightClickListener {
        void onClickRight(View v);
    }

    public static interface OnSubTitleClickListener {
        void onClickSubTitle(View v);
    }

}
