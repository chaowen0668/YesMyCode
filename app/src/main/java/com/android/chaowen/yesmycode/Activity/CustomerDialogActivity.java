package com.android.chaowen.yesmycode.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.chaowen.yesmycode.R;
import com.android.chaowen.yesmycode.widget.CustomerDialog;

import java.util.ArrayList;
import java.util.List;

public class CustomerDialogActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dialog);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show();
            }
        });
    }

    public void show(){
        final CustomerDialog.Builder builder = new CustomerDialog.Builder(this);
        builder.setTitle("选择平台");
        //0: 默认第一个单选按钮被选中
        List<String> name = new ArrayList<>();
        name.add("你好");
        name.add("你好");
        name.add("你好");
        CharSequence[] namechar = name.toArray(new CharSequence[name.size()]);
        builder.setSingleChoiceItems(namechar, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("生成二维码", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                builder.hide();
            }
        });

        builder.show();
    }
}
