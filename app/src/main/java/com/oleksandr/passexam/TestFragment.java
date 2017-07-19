package com.oleksandr.passexam;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Oleksandr on 17.07.2017.
 */

public class TestFragment extends Fragment {
    public static final String TAG = "TestFragment";
    private List<QuestionItem> mQuestionItems = new ArrayList<>();
    private List<QuestionItem> mChoiceQuestions = new ArrayList<>();
    private Button mStart;
    private Button mChose;
    private boolean btnState = true;
    private TextView mSizeText;
    private RecyclerView mRecyclerView;

    public static TestFragment newInstance(){
        return new TestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        read();
        mStart = (Button) v.findViewById(R.id.buttonStart);
        mChose = (Button) v.findViewById(R.id.buttonChoose);
        mSizeText = (TextView) v.findViewById(R.id.editTextSize);
        mSizeText.setText(QueryPreferences.getQUANTITY(getActivity()));
        if (btnState){
            mSizeText.setVisibility(View.VISIBLE);
            mStart.setText(R.string.btn_start);
        }else {
            mSizeText.setVisibility(View.GONE);
            mStart.setText(R.string.btb_done);
        }

        mChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DialogFragment  chooseQuestionFragment = ChooseQuestionFragment.newInstance();
                chooseQuestionFragment.show(fm, null);
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSizeText.getVisibility() == View.VISIBLE) {
                    mSizeText.setVisibility(View.GONE);
                    mStart.setText(R.string.btb_done);
                    getRandom();
                    btnState = false;
                }else {
                    mSizeText.setVisibility(View.VISIBLE);
                    mStart.setText(R.string.btn_start);
                    btnState = true;
                    showResult();
                }
            }
        });
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(new TestAdapter(mChoiceQuestions));
        mRecyclerView.getAdapter().notifyDataSetChanged();
        return v;
    }

    private void showResult(){
        int sizeIncorrect = 0;
        StringBuilder sb = new StringBuilder();
        for (QuestionItem item : mChoiceQuestions){
            if (!(item.getChoiceAnswer() != null && item.getChoiceAnswer().equals(item.getTrueAnswer()))){
                sizeIncorrect++;
                sb.append(item.getQuestion() + "\n"
                        + "Choice answer:  " + item.getChoiceAnswer() + "\n"
                        + "Right answer:  " + item.getTrueAnswer() + "\n\n");
            }
        }


        FragmentManager fm = getFragmentManager();
        DialogFragment  resultFragment = ResultFragment
                .newInstance("Incorrect:  " + sizeIncorrect + "/" + mChoiceQuestions.size()
                        + "\n\n" + sb.toString());
        resultFragment.show(fm, null);
    }

    private void getRandom(){
        mChoiceQuestions.clear();
        List<QuestionItem> questionItems = new ArrayList<>();

        if (QueryPreferences.getFirstBlock(getActivity())){
            for(int i = 0; i < 50; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getSecondBlock(getActivity())){
            for(int i = 50; i < 90; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getThirdBlock(getActivity())){
            for(int i = 90; i < mQuestionItems.size(); i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }

        Random random = new Random();
        int s = Integer.parseInt(mSizeText.getText().toString());
        QueryPreferences.setQuantity(getActivity(), s);
        if (s > questionItems.size()) s = questionItems.size();
        for (int i = 0; i < s; i++) {
            int getQuestion = random.nextInt(questionItems.size());

            mChoiceQuestions.add(questionItems.get(getQuestion));
            questionItems.remove(getQuestion);
        }
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }

    private void read(){
        try {
            Log.d(TAG, "start");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getActivity().getAssets().open("question")));
            String s;
            while ((s = reader.readLine()) != null) {
                QuestionItem item = new QuestionItem();
                item.setQuestion(s);
                String text = reader.readLine();
                item.setTrueAnswer(text);
                List<String> answer = new ArrayList<String>();

                answer.add(text);
                answer.add(reader.readLine());
                answer.add(reader.readLine());
                answer.add(reader.readLine());

                //Swap

                Random random = new Random();
                int q = random.nextInt(answer.size());
                item.setAnswer1(answer.get(q));
                answer.remove(q);
                q = random.nextInt(answer.size());
                item.setAnswer2(answer.get(q));
                answer.remove(q);
                q = random.nextInt(answer.size());
                item.setAnswer3(answer.get(q));
                answer.remove(q);
                q = random.nextInt(answer.size());
                item.setAnswer4(answer.get(q));
                answer.remove(q);

                mQuestionItems.add(item);
            }


        reader.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public void update(){
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private class TestHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mQuestion;
        private RadioGroup mRadioGroup;
        private RadioButton mRadioButton1;
        private RadioButton mRadioButton2;
        private RadioButton mRadioButton3;
        private RadioButton mRadioButton4;

        private QuestionItem mItem;

        public TestHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.item_question, container, false));
            mQuestion = (TextView) itemView.findViewById(R.id.textQuestion);
            mRadioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);
            mRadioButton1 = (RadioButton) itemView.findViewById(R.id.radioButton1);
            mRadioButton2 = (RadioButton) itemView.findViewById(R.id.radioButton2);
            mRadioButton3 = (RadioButton) itemView.findViewById(R.id.radioButton3);
            mRadioButton4 = (RadioButton) itemView.findViewById(R.id.radioButton4);
        }

        public void bindItem (QuestionItem item){
            mItem = item;

            clearRadioBtn(mItem);
            mQuestion.setText(mItem.getQuestion());
            mRadioButton1.setText(mItem.getAnswer1());
            mRadioButton2.setText(mItem.getAnswer2());
            mRadioButton3.setText(mItem.getAnswer3());
            mRadioButton4.setText(mItem.getAnswer4());
        }

        private void clearRadioBtn(QuestionItem item){
            switch (item.getChoiceAnswerId()){
                case 0:{
                    mRadioButton1.setChecked(true);

                    break;
                }
                case 1:{
                    mRadioButton2.setChecked(true);
                    break;
                }
                case 2:{
                    mRadioButton3.setChecked(true);
                    break;
                }
                case 3:{
                    mRadioButton4.setChecked(true);
                    break;
                }
                default:{
                    mRadioGroup.clearCheck();
                }
            }
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class TestAdapter extends RecyclerView.Adapter<TestHolder> {
        private List<QuestionItem> mQuestions;

        public TestAdapter(List<QuestionItem> questions){
            mQuestions= questions;
        }

        @Override
        public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return  new TestHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(final TestHolder holder, final int position) {
            final QuestionItem item = mQuestions.get(position);
            holder.mRadioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: 1");
                    mQuestions.get(position).setChoiceAnswerId(0);
                    mQuestions.get(position).setChoiceAnswer(holder.mRadioButton1.getText().toString());
                    update();
                }
            });
            holder.mRadioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: 2");
                    mQuestions.get(position).setChoiceAnswerId(1);
                    mQuestions.get(position).setChoiceAnswer(holder.mRadioButton2.getText().toString());
                    update();
                }
            });
            holder.mRadioButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: 3");
                    mQuestions.get(position).setChoiceAnswerId(2);
                    mQuestions.get(position).setChoiceAnswer(holder.mRadioButton3.getText().toString());
                    update();
                }
            });
            holder.mRadioButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: 4");
                    mQuestions.get(position).setChoiceAnswerId(3);
                    mQuestions.get(position).setChoiceAnswer(holder.mRadioButton4.getText().toString());
                    update();
                }
            });
            holder.bindItem(item);

        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
        }

}
