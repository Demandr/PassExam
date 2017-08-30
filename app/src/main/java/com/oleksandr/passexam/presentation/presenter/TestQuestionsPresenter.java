package com.oleksandr.passexam.presentation.presenter;




import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oleksandr.passexam.QueryPreferences;
import com.oleksandr.passexam.R;
import com.oleksandr.passexam.QuestionItem;
import com.oleksandr.passexam.presentation.view.TestQuestionsView;
import com.oleksandr.passexam.ui.fragments.ChooseQuestionFragment;
import com.oleksandr.passexam.ui.fragments.ResultFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

@InjectViewState
public class TestQuestionsPresenter extends MvpPresenter<TestQuestionsView> {
    private final Context mContext;
    private List<QuestionItem> mQuestionItems = new ArrayList<>();
    private List<QuestionItem> mChoiceQuestions = new ArrayList<>();

    public TestQuestionsPresenter(Context context) {
        mContext = context;
    }

    public void btnChoicePress(){
        DialogFragment chooseQuestionFragment = ChooseQuestionFragment.newInstance();
        chooseQuestionFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog);
        getViewState().showChoiceFragment(chooseQuestionFragment);
    }

    public void btnStartPress(){
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

        DialogFragment  resultFragment = ResultFragment
                .newInstance("Incorrect:  " + sizeIncorrect + "/" + mChoiceQuestions.size()
                        + "\n\n" + sb.toString());

        mChoiceQuestions.clear();
//        resultFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog);
        getViewState().showResultFragment(resultFragment);
    }

    public void getQuestions(int quantity){
        read();
        List<QuestionItem> questionItems = new ArrayList<>();

        if (QueryPreferences.getFirstBlock(mContext)){
            for(int i = 0; i < 50; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getSecondBlock(mContext)){
            for(int i = 50; i < 91; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getThirdBlock(mContext)){
            for(int i = 91; i < 111; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock4(mContext)){
            for(int i = 111; i < 162; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock5(mContext)){
            for(int i = 162; i < 198; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock6(mContext)){
            for(int i = 198; i < 239; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock7(mContext)){
            for(int i = 239; i < 262; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock8(mContext)){
            for(int i = 262; i < 530; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock9(mContext)){
            for(int i = 530; i < 565; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock10(mContext)){
            for(int i = 565; i < 688; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock11(mContext)){
            for(int i = 688; i < 738; i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }
        if (QueryPreferences.getBlock12(mContext)){
            for(int i = 738; i < mQuestionItems.size(); i++){
                questionItems.add(mQuestionItems.get(i));
            }
        }

        mixQuestions(questionItems, quantity);
    }

    public List<QuestionItem> getChoiceQuestions() {
        return mChoiceQuestions;
    }

    private void mixQuestions(List<QuestionItem> list, int quantity){
        mChoiceQuestions.clear();
        Random random = new Random();
        QueryPreferences.setQuantity(mContext, quantity);

        if (quantity > list.size()) quantity = list.size();

        if (QueryPreferences.getRandom(mContext)) {
            for (int i = 0; i < quantity; i++) {
                int getQuestion = random.nextInt(list.size());
                mChoiceQuestions.add(list.get(getQuestion));
                list.remove(getQuestion);
            }
        }else {
            for (int i = 0; i < quantity; i++)
                mChoiceQuestions.add(list.get(i));
        }
    }

    private void read(){
        mQuestionItems.clear();
        try {
            Log.d(TAG, "start");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("question")));
            String s;
            while ((s = reader.readLine()) != null) {
                QuestionItem item = new QuestionItem();
                item.setQuestion(s);
                String text = reader.readLine();
                item.setTrueAnswer(text);
                // TODO: 25.08.2017 rewrite Random
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

}
