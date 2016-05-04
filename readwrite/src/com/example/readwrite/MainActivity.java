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
				/*�������¼��ť
				 * ���ѡ���˼�ס�û�����checkbox�Ŀ�
				 * ���û���д���ļ���
				 * ȱ�ݣ�����ÿ��ֻ��д��һ��
				 * 
				 * */
				
				String name = et_user.getText().toString();
				String password = et_paw.getText().toString();
				
				cb = (CheckBox)findViewById(R.id.cb);
				if(cb.isChecked()){
					File file = new File(getFilesDir(),"log.txt");
					//File file = new File("data/data/com.example.readwrite/info.txt");//ָ��һ���ļ���·��
					try {
						FileOutputStream fos = new FileOutputStream(file);//�����ļ����ֽ������
						fos.write((name+"##"+password).getBytes());//������д���ļ���
						fos.close();//�ر������
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_LONG).show();
			}
		});
	
		}
	public void readContent(){
		/*
		 * �ڶ��ļ�ʱ��Ҫ�ж��ļ��Ƿ����
		 * */
		//File file  = new File(getCacheDir(),"in.txt");//����ڻ���·����
		File file = new File(getFilesDir(),"log.txt");//��API�еķ�����ô����ָ��Ŀ¼��
		//File file = new File("data/data/com.example.readwrite/info.txt");//ָ��һ���ļ���·��
	if(file.exists()){
		try {
			FileInputStream fis = new FileInputStream(file);//�ֽ�������
			//�ַ�������,,,,,,,���ֽ���ת�����ַ���
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			//fis.read();//��������Ч��̫�ͣ�Ӧ�ð����ŵ�һ��������������
			
			//��ȡtxt�ļ�����û���������
			String text = br.readLine();
			String[] s = text.split("##");//ͨ��##�ֿ����ַ������뵽�ַ���������
			
			et_user.setText(s[0]);
	    	et_paw.setText(s[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	
}
