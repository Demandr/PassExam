package com.oleksandr.passexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TestFragment extends Fragment {
    public static final String TAG = "TestFragment";
    private List<QuestionItem> mQuestionItems = new ArrayList<>();
    private List<QuestionItem> mChoiceQuestions = new ArrayList<>();
    private boolean btnState = true;
    private Unbinder unbinder;

    @BindView(R.id.buttonStart) Button mStart;
    @BindView(R.id.editTextSize) TextView mSizeText;
    @BindView(R.id.recycler) RecyclerView mRecyclerView;

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
        unbinder = ButterKnife.bind(this, v);

        mSizeText.setText(QueryPreferences.getQuantity(getActivity()));
        if (btnState){
            mSizeText.setVisibility(View.VISIBLE);
            mStart.setText(R.string.btn_start);
        }else {
            mSizeText.setVisibility(View.GONE);
            mStart.setText(R.string.btb_done);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mRecyclerView.setAdapter(new TestAdapter(mChoiceQuestions));
        mRecyclerView.setAdapter(new QuestionAdapter(getActivity(), mChoiceQuestions));
        mRecyclerView.getAdapter().notifyDataSetChanged();
        return v;
    }

    @OnClick(R.id.buttonChoose)
    public void pressChoose(){
        FragmentManager fm = getFragmentManager();
        DialogFragment  chooseQuestionFragment = ChooseQuestionFragment.newInstance();
        chooseQuestionFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog);
        chooseQuestionFragment.show(fm, null);
    }

    @OnClick(R.id.buttonStart)
    public void pressStart(){
        if (mSizeText.getVisibility() == View.VISIBLE) {
            mSizeText.setVisibility(View.GONE);
            mStart.setText(R.string.btb_done);
            getQuestion();
            btnState = !btnState;
        }else {
            mSizeText.setVisibility(View.VISIBLE);
            mStart.setText(R.string.btn_start);
            btnState = !btnState;
            showResult();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showResult(){
        int sizeIncorrect = 0;
        StringBuilder sb = new StringBuilder();
        for (QuestionItem item : mChoiceQuestions){
            if (!(item.getChoiceAnswer() != null && item.getChoiceAnswer().equals(item.getTrueAnswer()))){
                sizeIncorrect++;
                sb.append(item.getQuestion())
                        .append("\n")
                        .append("Choice answer:  ")
                        .append(item.getChoiceAnswer())
                        .append("\n")
                        .append("Right answer:  ")
                        .append(item.getTrueAnswer())
                        .append("\n\n");
            }
        }


                FragmentManager fm = getFragmentManager();
        DialogFragment  resultFragment = ResultFragment
                .newInstance("Incorrect:  " + sizeIncorrect + "/" + mChoiceQuestions.size()
                        + "\n\n" + sb.toString());
        mChoiceQuestions.clear();
        mRecyclerView.getAdapter().notifyDataSetChanged();
//        resultFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog);
        resultFragment.show(fm, null);
    }

    private void getQuestion(){
        read();
        List<QuestionItem> questionItems = new ArrayList<>();

        if (QueryPreferences.getFirstBlock(getActivity())){
            for(int i = 0; i < 50; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getSecondBlock(getActivity())){
            for(int i = 50; i < 91; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getThirdBlock(getActivity())){
            for(int i = 91; i < 111; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock4(getActivity())){
            for(int i = 111; i < 162; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock5(getActivity())){
            for(int i = 162; i < 198; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock6(getActivity())){
            for(int i = 198; i < 239; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock7(getActivity())){
            for(int i = 239; i < 262; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock8(getActivity())){
            for(int i = 262; i < 530; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock9(getActivity())){
            for(int i = 530; i < 565; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock10(getActivity())){
            for(int i = 565; i < 688; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock11(getActivity())){
            for(int i = 688; i < 738; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock12(getActivity())){
            for(int i = 738; i < mQuestionItems.size(); i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }

        getRandom(questionItems);

        mRecyclerView.getAdapter().notifyDataSetChanged();

    }

    private void getRandom(List<QuestionItem> list){
        mChoiceQuestions.clear();
        Random random = new Random();
        int s = Integer.parseInt(mSizeText.getText().toString());
        QueryPreferences.setQuantity(getActivity(), s);

        if (s > list.size()) s = list.size();

        if (QueryPreferences.getRandom(getActivity())) {
            for (int i = 0; i < s; i++) {
                int getQuestion = random.nextInt(list.size());
                mChoiceQuestions.add(list.get(getQuestion));
                list.remove(getQuestion);
            }
        }else {
            for (int i = 0; i < s; i++)
                mChoiceQuestions.add(list.get(i));
        }
    }

    private void read(){
        mQuestionItems.clear();
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
                List<String> answer = new ArrayList<>();

                answer.add(text);
                answer.add(reader.readLine());
                answer.add(reader.readLine());
                answer.add(reader.readLine());

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

    public void update() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

}
