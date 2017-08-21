package com.oleksandr.passexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ResultFragment extends DialogFragment {
    public static final String TEXT = "text_res";

    private String mTextResult;
    @BindView(R.id.textResult) TextView mTextView;
    private Unbinder unbinder;

    public static ResultFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putSerializable(TEXT, s);
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextResult = (String) getArguments().getSerializable(TEXT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        unbinder = ButterKnife.bind(this, v);
        mTextView.setText(mTextResult);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
