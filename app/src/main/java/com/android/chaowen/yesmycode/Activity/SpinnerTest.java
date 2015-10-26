package com.android.chaowen.yesmycode.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.chaowen.yesmycode.R;
import com.android.chaowen.yesmycode.widget.SpinerAdapter;
import com.android.chaowen.yesmycode.widget.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

public class SpinnerTest extends AppCompatActivity implements View.OnClickListener, SpinerAdapter.IOnItemSelectListener {

    private List<String> mListType = new ArrayList<String>();  //类型列表
    private TextView mTView;
    private SpinerAdapter mAdapter;
    private RelativeLayout spinnerRe;
    //设置PopWindow
    private SpinerPopWindow mSpinerPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_spinner);
        mTView = (TextView) findViewById(R.id.tv_value);
        spinnerRe = (RelativeLayout) findViewById(R.id.bt_dropdown);
        spinnerRe.setOnClickListener(this);
        //初始化数据
        String[] names = getResources().getStringArray(R.array.array_name);
        for (int i = 0; i < names.length; i++) {
            mListType.add(names[i]);
        }

        mAdapter = new SpinerAdapter(this, mListType);
        mAdapter.refreshData(mListType, 0);

        //显示第一条数据
        //mTView.setText(names[0]);

        //初始化PopWindow
        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);

    }

    private void showSpinWindow() {
        Log.e("", "showSpinWindow");
        mSpinerPopWindow.setWidth(mTView.getWidth());
        mSpinerPopWindow.showAsDropDown(mTView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_dropdown:
                showSpinWindow();
                break;
        }
    }


    /* (non-Javadoc)
     * @see org.gaochun.adapter.SpinerAdapter.IOnItemSelectListener#onItemClick(int)
     */
    @Override
    public void onItemClick(int pos) {
        // TODO Auto-generated method stub
        if (pos >= 0 && pos <= mListType.size()) {
            String value = mListType.get(pos);
            mTView.setText(value.toString());
        }
    }
}
