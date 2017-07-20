package com.oleksandr.passexam;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class ChooseQuestionFragment extends DialogFragment {
    public static final String TAG = "ChooseQuestionFragment";

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;
    private CheckBox mCheckBox5;
    private CheckBox mCheckBox6;
    private CheckBox mCheckBox7;
    private CheckBox mCheckBox8;
    private CheckBox mCheckBox9;
    private CheckBox mCheckBox10;
    private CheckBox mCheckBox11;
    private CheckBox mCheckBox12;
    private CheckBox mCheckBox13;
    private Button mButton;

    public static ChooseQuestionFragment newInstance() {
        Bundle args = new Bundle();

        ChooseQuestionFragment fragment = new ChooseQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_question, container, false);
        mCheckBox1 = (CheckBox) v.findViewById(R.id.checkBox);
        mCheckBox2 = (CheckBox) v.findViewById(R.id.checkBox2);
        mCheckBox3 = (CheckBox) v.findViewById(R.id.checkBox3);
        mCheckBox4 = (CheckBox) v.findViewById(R.id.checkBox4);
        mCheckBox5 = (CheckBox) v.findViewById(R.id.checkBox5);
        mCheckBox6 = (CheckBox) v.findViewById(R.id.checkBox6);
        mCheckBox7 = (CheckBox) v.findViewById(R.id.checkBox7);
        mCheckBox8 = (CheckBox) v.findViewById(R.id.checkBox8);
        mCheckBox9 = (CheckBox) v.findViewById(R.id.checkBox9);
        mCheckBox10 = (CheckBox) v.findViewById(R.id.checkBox10);
        mCheckBox11 = (CheckBox) v.findViewById(R.id.checkBox11);
        mCheckBox12 = (CheckBox) v.findViewById(R.id.checkBox12);
        mCheckBox13 = (CheckBox) v.findViewById(R.id.checkBox13);

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


        mButton = (Button) v.findViewById(R.id.buttonSave);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryPreferences.setRandom(getActivity(), mCheckBox13.isChecked());
                QueryPreferences.setCheckValue(getActivity(), mCheckBox1.isChecked(), mCheckBox2.isChecked(),
                        mCheckBox3.isChecked(), mCheckBox4.isChecked(),mCheckBox5.isChecked(), mCheckBox6.isChecked(),
                        mCheckBox7.isChecked(), mCheckBox8.isChecked(),mCheckBox9.isChecked(), mCheckBox10.isChecked(),
                        mCheckBox11.isChecked(), mCheckBox12.isChecked());
                dismiss();
            }
        });
        return v;
    }
}
