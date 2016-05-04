package com.example.readwrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private EditText et_user;
	private EditText et_paw;
	private Button bt_login;
	private CheckBox cb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_user = (EditText)findViewById(R.id.et_user);
		et_paw = (EditText)findViewById(R.id.et_paw);
		readContent();
		
		bt_login = (Button)findViewById(R.id.bt_login);
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*当点击登录按钮
				 * 如果选择了记住用户名的checkbox的框
				 * 将用户名写入文件中
				 * 缺陷：：：每次只能写入一个
				 * 
				 * */
				
				String name = et_user.getText().toString();
				String password = et_paw.getText().toString();
				
				cb = (CheckBox)findViewById(R.id.cb);
				if(cb.isChecked()){
					File file = new File(getFilesDir(),"log.txt");
					//File file = new File("data/data/com.example.readwrite/info.txt");//指定一个文件的路径
					try {
						FileOutputStream fos = new FileOutputStream(file);//操作文件的字节输出流
						fos.write((name+"##"+password).getBytes());//将内容写到文件中
						fos.close();//关闭输出流
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
			}
		});
	
		}
	public void readContent(){
		/*
		 * 在读文件时先要判断文件是否存在
		 * */
		//File file  = new File(getCacheDir(),"in.txt");//存放在缓存路径下
		File file = new File(getFilesDir(),"log.txt");//过API中的方法获得存放在指定目录下
		//File file = new File("data/data/com.example.readwrite/info.txt");//指定一个文件的路径
	if(file.exists()){
		try {
			FileInputStream fis = new FileInputStream(file);//字节输入流
			//字符缓冲器,,,,,,,将字节流转化成字符流
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			//fis.read();//这样读的效率太低，应该把它放到一个缓冲区中来读
			
			//读取txt文件里的用户名和密码
			String text = br.readLine();
			String[] s = text.split("##");//通过##分开的字符串存入到字符串数组中
			
			et_user.setText(s[0]);
	    	et_paw.setText(s[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	
}
