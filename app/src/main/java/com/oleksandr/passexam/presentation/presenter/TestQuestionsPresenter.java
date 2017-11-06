package com.oleksandr.passexam.presentation.presenter;


import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oleksandr.passexam.QueryPreferences;
import com.oleksandr.passexam.QuestionItem;
import com.oleksandr.passexam.R;
import com.oleksandr.passexam.presentation.view.TestQuestionsView;
import com.oleksandr.passexam.ui.fragments.ChooseQuestionFragment;
import com.oleksandr.passexam.ui.fragments.ResultFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@InjectViewState
public class TestQuestionsPresenter extends MvpPresenter<TestQuestionsView> {
    public static final String TAG = "TestQuestionsPresenter";
    private final Context mContext;
    private List<QuestionItem> mChoiceQuestions = new ArrayList<>();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

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

        mChoiceQuestions.clear();
        Log.d(TAG, "getQuestions: " + QueryPreferences.getFirstBlock(mContext));

        mFirestore.collection("Exam").document("Questions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult() != null){
                            List<Map<String, Object>> mapList;
                            Map<String, Object> docData;
                            docData = task.getResult().getData();
                            if (QueryPreferences.getFirstBlock(mContext)) {
                                mapList = (List<Map<String,Object>>) docData.get("Profiled_1");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getSecondBlock(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Profiled_2");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getThirdBlock(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Grammatical form");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock4(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Adjective");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock5(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Preposition");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock6(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Pronoun");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock7(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Noun");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }if (QueryPreferences.getBlock8(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Verb");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }if (QueryPreferences.getBlock9(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Modal verb");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock10(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Subordinate sentence");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock11(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Questionnaire");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                            if (QueryPreferences.getBlock12(mContext)){
                                mapList = (List<Map<String,Object>>) docData.get("Translation");
                                mChoiceQuestions.addAll(parseTask(mapList));
                            }
                        }else {
                            Log.d(TAG, "onComplete: Empty fireStore");
                        }
                    }else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                    mixQuestions(mChoiceQuestions, quantity);
                    getViewState().updateRecycler();
                });
    }

    private List<QuestionItem> parseTask(List<Map<String, Object>> mapList) {
        List<QuestionItem> itemList = new ArrayList<>();
        List<String> list = new ArrayList<>();

        for (Map<String, Object> questionItem : mapList) {
            list.clear();

            QuestionItem item = new QuestionItem();
            item.setQuestion(questionItem.get("question").toString());
            item.setTrueAnswer(questionItem.get("answer1").toString());

            list.add(questionItem.get("answer1").toString());
            list.add(questionItem.get("answer2").toString());
            list.add(questionItem.get("answer3").toString());
            list.add(questionItem.get("answer4").toString());

            Collections.shuffle(list);

            item.setAnswer1(list.get(0));
            item.setAnswer2(list.get(1));
            item.setAnswer3(list.get(2));
            item.setAnswer4(list.get(3));
            itemList.add(item);
        }
        return itemList;
    }

    public List<QuestionItem> getChoiceQuestions() {
        return mChoiceQuestions;
    }

    private void mixQuestions(List<QuestionItem> list, int quantity){
        if (QueryPreferences.getRandom(mContext))
            Collections.shuffle(list);

        while (list.size() > quantity){
            list.remove(list.size() - 1);
        }
    }
}
