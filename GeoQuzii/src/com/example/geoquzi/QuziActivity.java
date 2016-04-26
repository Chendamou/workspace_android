package com.example.geoquzi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuziActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{//����TureFalse��������
			new TrueFalse(R.string.question_afica, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_oceans, false),
	};
	private int mCurrentIndex = 0;//�������������//�½�ָ�����������浱ǰ�������һ��Ԫ��
	
	private void updataQuestion(){
		/*
		 * ��ȡ��mQuestionBank�е�ֵ������question
		 * Ȼ������TextView��ֵΪquestion
		 * */
		int question  = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue){//�жϵ���λ���Ƿ���ȷ
		/*�½�һ����Ϊ checkAnswer �ķ��������û����Ĳ���ֵ������ʾ�Ƿ���ȷ ,
		 *  userPressedTrueΪboolean���βΣ�
		 * */
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();//��ȡ���������е������Ƿ���ȷ
		int messageResId = 0;//����λ�õ�id��������������ַ�����Դstring.xml�ĸ�ֵ
		
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
		 mNextButton = (ImageButton)findViewById(R.id.next_button);
		 mNextButton.setOnClickListener(new OnClickListener() {//Ϊnext��ť��ӵ���¼�
			
			@Override
			public void onClick(View v) {
			mCurrentIndex  = (mCurrentIndex+1)% mQuestionBank.length;
			updataQuestion();
				
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
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new OnClickListener() {
			/*
			 * Ϊ�ı�����ӵ���¼�
			 * ������ı������������ʾ��һ������
			 * �൱��next��ť
			 * */
			@Override
			public void onClick(View v) {
				mCurrentIndex  = (mCurrentIndex+1)% mQuestionBank.length;
				updataQuestion();
			}
		});
		mPrevButton = (ImageButton)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new OnClickListener() {//Ϊ���˰�ť��ӵ���¼�
			
			@Override
			public void onClick(View v) {
				if(mCurrentIndex%mQuestionBank.length==0){
					mCurrentIndex = mQuestionBank.length-1;
				}else
				{
				mCurrentIndex  = (mCurrentIndex-1)% mQuestionBank.length;
				updataQuestion();
				}
			}
		});
	}

}
