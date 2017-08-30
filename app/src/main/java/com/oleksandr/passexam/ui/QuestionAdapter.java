package com.oleksandr.passexam.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.oleksandr.passexam.R;
import com.oleksandr.passexam.QuestionItem;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private static final String TAG = QuestionAdapter.class.getSimpleName();
    private Context mContext;
    private List<QuestionItem> mQuestionItems;

//    private OnItemClickListener mListener;
//
//    public QuestionAdapter(Context context, List<QuestionItem> list, OnItemClickListener onItemClickListener) {
//        this.mContext = context;
//        this.mQuestionItems = list;
//        this.mListener = onItemClickListener;
//    }

    public QuestionAdapter(Context context, List<QuestionItem> questionItems) {
        this.mContext = context;
        this.mQuestionItems = questionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        QuestionItem item = mQuestionItems.get(position);
        bindItem(holder, item);

        holder.mRadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 1");
                mQuestionItems.get(position).setChoiceAnswerId(0);
                mQuestionItems.get(position).setChoiceAnswer(holder.mRadioButton1.getText().toString());
            }
        });
        holder.mRadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 2");
                mQuestionItems.get(position).setChoiceAnswerId(1);
                mQuestionItems.get(position).setChoiceAnswer(holder.mRadioButton2.getText().toString());
            }
        });
        holder.mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 3");
                mQuestionItems.get(position).setChoiceAnswerId(2);
                mQuestionItems.get(position).setChoiceAnswer(holder.mRadioButton3.getText().toString());
            }
        });
        holder.mRadioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 4");
                mQuestionItems.get(position).setChoiceAnswerId(3);
                mQuestionItems.get(position).setChoiceAnswer(holder.mRadioButton4.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionItems.size();
    }

    private void bindItem (ViewHolder holder, QuestionItem item){

        clearRadioBtn(holder, item);
        holder.mQuestion.setText(item.getQuestion());
        holder.mRadioButton1.setText(item.getAnswer1());
        holder.mRadioButton2.setText(item.getAnswer2());
        holder.mRadioButton3.setText(item.getAnswer3());
        holder.mRadioButton4.setText(item.getAnswer4());
    }

    private void clearRadioBtn(ViewHolder holder, QuestionItem item){
        switch (item.getChoiceAnswerId()){
            case 0:{
                holder.mRadioButton1.setChecked(true);
                break;
            }
            case 1:{
                holder.mRadioButton2.setChecked(true);
                break;
            }
            case 2:{
                holder.mRadioButton3.setChecked(true);
                break;
            }
            case 3:{
                holder.mRadioButton4.setChecked(true);
                break;
            }
            default:{
                holder.mRadioGroup.clearCheck();
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textQuestion) TextView mQuestion;
        @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
        @BindView(R.id.radioButton1) RadioButton mRadioButton1;
        @BindView(R.id.radioButton2) RadioButton mRadioButton2;
        @BindView(R.id.radioButton3) RadioButton mRadioButton3;
        @BindView(R.id.radioButton4) RadioButton mRadioButton4;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}