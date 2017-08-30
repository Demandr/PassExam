package com.oleksandr.passexam.ui.activities;

import android.support.v4.app.Fragment;

import com.oleksandr.passexam.ui.fragments.TestQuestionsFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
//        return TestFragment.newInstance();
        return TestQuestionsFragment.newInstance();
    }

}
