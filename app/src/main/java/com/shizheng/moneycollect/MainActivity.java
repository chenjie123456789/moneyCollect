package com.shizheng.moneycollect;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shizheng.adapter.SmsAdapter;
import com.shizheng.bean.Smsbean;
import com.shizheng.unit.timeFormater;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongFunction;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Uri SMS_INBOX = Uri.parse("content://sms/");
    private TextView textViewUsedMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewUsedMoney = findViewById(R.id.used_money);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = findViewById(R.id.rec_message);
        recyclerView.setLayoutManager(manager);
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        obtainPhoneMessage();
    }

    public void obtainPhoneMessage() {
        int addText = 0;
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{"android.permission.READ_SMS","android.permission.RECEIVE_SMS"},123 );
        ContentResolver cr = getContentResolver();
        List<Smsbean>  list = new ArrayList<>();
        SmsAdapter smsAdapter = new SmsAdapter(list,this);
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        @SuppressLint("Recycle")
        Cursor cur = cr.query(SMS_INBOX, projection,  "address=?",new String[]{"1069049996568"}, "date desc");
        if (null == cur) {
            Log.i("ooc", "************cur == null");
            return;
        }
        while (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String date = cur.getString(cur.getColumnIndex("date"));//获取时间
            String time = timeFormater.timeTransition(Long.parseLong(date));
            String body = cur.getString(cur.getColumnIndex("body"));//短信内容

            if (time.equals(timeFormater.getTodayTime())){
                Smsbean smsbean = new Smsbean();
                smsbean.setAddress(number);
                smsbean.setContent(body);
                smsbean.setTime(time);
                int numStart = body.indexOf("转出")+2;
                int numEnd = numStart + 2;
                String string = body.substring(numStart,numEnd);
                if (string.contains(".")){
                    String[] text = string.split("\\.");
                    string = "0"+text[0];
                }
                int numAdd = Integer.parseInt(string);
                addText = addText + numAdd ;
                Log.i("TEST MONEY INTEGER",">>>>>>>>>>>>>>>>>>"+numAdd);
                list.add(smsbean);
            }


        }
        Log.i("TEST MONEY ADD",">>>>>>>>>>>>>>>>>>"+addText);
        String money_text = Integer.toString(addText);
        textViewUsedMoney.setText(money_text);
        recyclerView.setAdapter(smsAdapter);

    }

}