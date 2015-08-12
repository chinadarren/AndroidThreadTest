package com.example.AndroidThreadTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//���߳�ʵ��UI����
public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private TextView text;
    private Button changeText;
    //�������ͳ��� UPDATE_TEXT�����ڱ�ʾ���� TextView�������
    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){
        //����дHandler����� handleMessage ����
        public void handleMessage(Message msg){
            //Message�� what�ֶε�ֵ�Ƿ���� UPDATE_TEXT
            switch (msg.what){
                case UPDATE_TEXT:
                    //��������Խ���UI����
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        //��Message���� what�ֶε�ֵָ��Ϊ UPDATE_TEXT
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//��Message�����ͳ�ȥ
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}