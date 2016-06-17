package com.myfstrong.mycniao5.weiget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myfstrong.mycniao5.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class CnToolbar extends Toolbar {

    private LayoutInflater mInflater;
    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private ImageButton mRightImageButton;

    public CnToolbar(Context context) {
        this(context,null);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        setContentInsetsRelative(10,10);

        if (attrs != null) {
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CnToolbar, defStyleAttr, 0);

            final Drawable rightIcon = a.getDrawable(R.styleable.CnToolbar_rightButtonIcon);
            if (rightIcon != null) {
                setRightButtonIcon(rightIcon);
            }

            boolean isShowSearchView = a.getBoolean(R.styleable.CnToolbar_isShowSearchView,false);
            if (isShowSearchView) {
                showSearchView();
                hideTitleView();
            }
            a.recycle();
        }

    }

    private void initView() {
        if (mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar,null);

            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mRightImageButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);

            LayoutParams params = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL);
            addView(mView,params);
        }
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (mTextTitle != null) {
            mTextTitle.setText(title);
            showTitleView();
        }
    }

    public void setRightButtonIcon(Drawable icon) {
        if (mRightImageButton != null) {
            mRightImageButton.setImageDrawable(icon);
            mRightImageButton.setVisibility(VISIBLE);
        }
    }

    public void setRightButtonOnClickListener(OnClickListener listener) {
        mRightImageButton.setOnClickListener(listener);
    }

    public void showSearchView() {
        if (mSearchView != null) {
            mSearchView.setVisibility(VISIBLE);
        }
    }

    public void hideSearchView() {
        if (mSearchView != null) {
            mSearchView.setVisibility(GONE);
        }
    }

    public void showTitleView() {
        if (mTextTitle != null) {
            mTextTitle.setVisibility(VISIBLE);
        }
    }

    public void hideTitleView() {
        if (mTextTitle != null) {
            mTextTitle.setVisibility(GONE);
        }
    }
}
