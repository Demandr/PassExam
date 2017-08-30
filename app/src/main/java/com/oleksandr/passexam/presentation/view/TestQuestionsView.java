package com.oleksandr.passexam.presentation.view;

import android.support.v4.app.DialogFragment;

import com.arellomobile.mvp.MvpView;

public interface TestQuestionsView extends MvpView {
    void showResultFragment(DialogFragment df);
    void showChoiceFragment(DialogFragment df);
}
