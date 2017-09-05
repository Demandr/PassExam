package com.oleksandr.passexam.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.oleksandr.passexam.QueryPreferences;
import com.oleksandr.passexam.R;
import com.oleksandr.passexam.presentation.presenter.TestQuestionsPresenter;
import com.oleksandr.passexam.presentation.view.TestQuestionsView;
import com.oleksandr.passexam.ui.QuestionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestQuestionsFragment extends MvpAppCompatFragment implements TestQuestionsView {
    public static final String TAG = "TestQuestionsFragment";
    @InjectPresenter
    TestQuestionsPresenter mTestQuestionsPresenter;


    private boolean btnState = true;

    @BindView(R.id.buttonStart)
    Button mStart;
    @BindView(R.id.editTextSize)
    TextView mSizeText;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @ProvidePresenter
    TestQuestionsPresenter provideTestQuestionsPresenter() {
        return new TestQuestionsPresenter(getActivity().getApplicationContext());
    }

    public static TestQuestionsFragment newInstance() {
        TestQuestionsFragment fragment = new TestQuestionsFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_questions, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mSizeText.setText(QueryPreferences.getQuantity(getActivity()));
        if (btnState){
            mSizeText.setVisibility(View.VISIBLE);
            mStart.setText(R.string.btn_start);
        }else {
            mSizeText.setVisibility(View.GONE);
            mStart.setText(R.string.btb_done);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new QuestionAdapter(getActivity(), mTestQuestionsPresenter.getChoiceQuestions()));
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @OnClick(R.id.buttonChoose)
    public void pressChoose(){
        mTestQuestionsPresenter.btnChoicePress();
    }

    @OnClick(R.id.buttonStart)
    public void pressStart(){
        if (mSizeText.getVisibility() == View.VISIBLE) {
            mSizeText.setVisibility(View.GONE);
            mStart.setText(R.string.btb_done);
            mTestQuestionsPresenter.getQuestions(Integer.parseInt(mSizeText.getText().toString()));
            mRecyclerView.getAdapter().notifyDataSetChanged();
            btnState = !btnState;
        }else {
            mSizeText.setVisibility(View.VISIBLE);
            mStart.setText(R.string.btn_start);
            btnState = !btnState;
            mTestQuestionsPresenter.btnStartPress();
        }
    }

    @Override
    public void showResultFragment(DialogFragment df) {
        mRecyclerView.getAdapter().notifyDataSetChanged();
        df.show(getFragmentManager(), null);
    }

    @Override
    public void showChoiceFragment(DialogFragment df) {
        df.show(getFragmentManager(), "Choice");
    }

}
