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

/**
 * Created by Oleksandr on 19.07.2017.
 */

public class ChooseQuestionFragment extends DialogFragment {
    public static final String TAG = "ChooseQuestionFragment";

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
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
        mCheckBox1.setChecked(QueryPreferences.getFirstBlock(getActivity()));
        mCheckBox2.setChecked(QueryPreferences.getSecondBlock(getActivity()));
        mCheckBox3.setChecked(QueryPreferences.getThirdBlock(getActivity()));


        mButton = (Button) v.findViewById(R.id.buttonSave);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryPreferences.setCheckValue(getActivity(), mCheckBox1.isChecked(), mCheckBox2.isChecked(), mCheckBox3.isChecked());
                dismiss();
            }
        });
        return v;
    }
}
