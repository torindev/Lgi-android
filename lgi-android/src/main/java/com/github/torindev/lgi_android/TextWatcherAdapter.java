package com.github.torindev.lgi_android;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatcherAdapter implements TextWatcher {

    public interface OnBeforeTextChangedListener {
        void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2, int beforeTextChangedDummyMarker);
    }

    public interface OnTextChangeListener {
        void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
    }

    public interface AfterTextChangedListener {
        void afterTextChanged(Editable editable);
    }

    private OnTextChangeListener mOnTextChangeListener;
    private OnBeforeTextChangedListener mOnBeforeTextChangedListener;
    private AfterTextChangedListener mAfterTextChangedListener;

    public TextWatcherAdapter(OnBeforeTextChangedListener onBeforeTextChangedListener) {
        mOnBeforeTextChangedListener = onBeforeTextChangedListener;
    }

    public TextWatcherAdapter(OnTextChangeListener onTextChangeListener) {
        mOnTextChangeListener = onTextChangeListener;
    }

    public TextWatcherAdapter(AfterTextChangedListener afterTextChangedListener) {
        mAfterTextChangedListener = afterTextChangedListener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mOnBeforeTextChangedListener != null) {
            mOnBeforeTextChangedListener.beforeTextChanged(charSequence, i, i1, i2, 0);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mOnTextChangeListener != null) {
            mOnTextChangeListener.onTextChanged(charSequence, i, i1, i2);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mAfterTextChangedListener != null) {
            mAfterTextChangedListener.afterTextChanged(editable);
        }
    }
}
