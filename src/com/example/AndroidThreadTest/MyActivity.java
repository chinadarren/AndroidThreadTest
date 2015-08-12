package com.example.AndroidThreadTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//子线程实现UI操作
public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private TextView text;
    private Button changeText;
    //定义整型常量 UPDATE_TEXT，用于表示更新 TextView这个动作
    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){
        //并重写Handler父类的 handleMessage 方法
        public void handleMessage(Message msg){
            //Message的 what字段的值是否等于 UPDATE_TEXT
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里可以进行UI操作
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
                        //将Message对象 what字段的值指定为 UPDATE_TEXT
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将Message对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}