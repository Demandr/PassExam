package com.oleksandr.passexam.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.oleksandr.passexam.QueryPreferences;
import com.oleksandr.passexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO: 27.08.2017 fix fragment retain
public class ChooseQuestionFragment extends DialogFragment {
    public static final String TAG = "ChooseQuestionFragment";
    private boolean dismiss = false;

    @BindView(R.id.checkBox) CheckBox mCheckBox1;
    @BindView(R.id.checkBox2) CheckBox mCheckBox2;
    @BindView(R.id.checkBox3) CheckBox mCheckBox3;
    @BindView(R.id.checkBox4) CheckBox mCheckBox4;
    @BindView(R.id.checkBox5) CheckBox mCheckBox5;
    @BindView(R.id.checkBox6) CheckBox mCheckBox6;
    @BindView(R.id.checkBox7) CheckBox mCheckBox8;
    @BindView(R.id.checkBox8) CheckBox mCheckBox7;
    @BindView(R.id.checkBox9) CheckBox mCheckBox9;
    @BindView(R.id.checkBox10) CheckBox mCheckBox10;
    @BindView(R.id.checkBox11) CheckBox mCheckBox11;
    @BindView(R.id.checkBox12) CheckBox mCheckBox12;
    @BindView(R.id.checkBox13) CheckBox mCheckBox13;


    public static ChooseQuestionFragment newInstance() {
        Bundle args = new Bundle();

        ChooseQuestionFragment fragment = new ChooseQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if(dismiss) {
            this.dismiss();
        }
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_questions, container, false);

        ButterKnife.bind(this, v);
        setCheckBox();

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dismiss = true;
    }


    @OnClick(R.id.buttonSave)
    public void pressSave(){
        QueryPreferences.setRandom(getActivity(), mCheckBox13.isChecked());
        QueryPreferences.setCheckValue(getActivity(), mCheckBox1.isChecked(), mCheckBox2.isChecked(),
                mCheckBox3.isChecked(), mCheckBox4.isChecked(),mCheckBox5.isChecked(), mCheckBox6.isChecked(),
                mCheckBox7.isChecked(), mCheckBox8.isChecked(),mCheckBox9.isChecked(), mCheckBox10.isChecked(),
                mCheckBox11.isChecked(), mCheckBox12.isChecked());
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setCheckBox(){
        mCheckBox1.setChecked(QueryPreferences.getFirstBlock(getActivity()));
        mCheckBox2.setChecked(QueryPreferences.getSecondBlock(getActivity()));
        mCheckBox3.setChecked(QueryPreferences.getThirdBlock(getActivity()));
        mCheckBox4.setChecked(QueryPreferences.getBlock4(getActivity()));
        mCheckBox5.setChecked(QueryPreferences.getBlock5(getActivity()));
        mCheckBox6.setChecked(QueryPreferences.getBlock6(getActivity()));
        mCheckBox7.setChecked(QueryPreferences.getBlock7(getActivity()));
        mCheckBox8.setChecked(QueryPreferences.getBlock8(getActivity()));
        mCheckBox9.setChecked(QueryPreferences.getBlock9(getActivity()));
        mCheckBox10.setChecked(QueryPreferences.getBlock10(getActivity()));
        mCheckBox11.setChecked(QueryPreferences.getBlock11(getActivity()));
        mCheckBox12.setChecked(QueryPreferences.getBlock12(getActivity()));
        mCheckBox13.setChecked(QueryPreferences.getRandom(getActivity()));
    }
}
