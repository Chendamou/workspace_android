package com.example.geoquzi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuziActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{//����TureFalse��������
			new TrueFalse(R.string.question_afica, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_oceans, false),
	};
	private int mCurrentIndex = 0;//�������������
	
	private void checkAnswer(boolean userPressedTrue){//�жϵ���λ���Ƿ���ȷ
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();//��������������Ƿ���ȷ
		int messageResId = 0;//����λ�õ�id
		if(userPressedTrue == answerIsTrue){
			messageResId = R.string.correct_toast;
		}else{
			messageResId = R.string.incorrect_toast;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quzi);
		
		 mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		 int question = mQuestionBank[mCurrentIndex].getQuestion();
		 mQuestionTextView.setText(question);
		 
		 mTrueButton= (Button)findViewById(R.id.true_button);
		 mFalseButton = (Button)findViewById(R.id.false_button);
		 mNextButton = (Button)findViewById(R.id.next_button);
		 mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			mCurrentIndex  = (mCurrentIndex+1)% mQuestionBank.length;
			int question  = mQuestionBank[mCurrentIndex].getQuestion();
			mQuestionTextView.setText(question);
				
			}
		});
		
		
		mTrueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuziActivity.this, R.string.correct_toast,Toast.LENGTH_LONG).show();
				checkAnswer(true);
			}
		});
		mFalseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//Toast.makeText(QuziActivity.this, R.string.incorrect_toast, Toast.LENGTH_LONG).show();
			checkAnswer(false);
			}
		});
		
	}

}
