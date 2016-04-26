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
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{//创建TureFalse对象数组
			new TrueFalse(R.string.question_afica, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_oceans, false),
	};
	private int mCurrentIndex = 0;//对象数组的索引//新建指数变量来保存当前数组的哪一个元素
	
	private void updataQuestion(){
		/*
		 * 获取到mQuestionBank中的值给变量question
		 * 然后设置TextView的值为question
		 * */
		int question  = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue){//判断地理位置是否正确
		/*新建一个名为 checkAnswer 的方法根据用户给的布尔值弹出提示是否正确 ,
		 *  userPressedTrue为boolean的形参，
		 * */
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();//获取对象数组中的问题是否正确
		int messageResId = 0;//地理位置的id即下面接受来自字符串资源string.xml的赋值
		
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
		 mNextButton.setOnClickListener(new OnClickListener() {//为next按钮添加点击事件
			
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
			 * 为文本框添加点击事件
			 * 当点击文本框的内容是显示下一条内容
			 * 相当于next按钮
			 * */
			@Override
			public void onClick(View v) {
				mCurrentIndex  = (mCurrentIndex+1)% mQuestionBank.length;
				updataQuestion();
			}
		});
		mPrevButton = (ImageButton)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new OnClickListener() {//为后退按钮添加点击事件
			
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
