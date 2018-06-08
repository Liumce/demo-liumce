package net.hw.application.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import net.hw.application.R;

/**
 * Created by 17828121783 on 2018/5/20.
 */

public class CategoryActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_category);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.tea_category_title);
        init();
        tv_category_menu1.setBackgroundColor(Color.WHITE);
        tv_category_menu1.setOnClickListener(this);
        tv_category_menu2.setOnClickListener(this);
        tv_category_menu3.setOnClickListener(this);
        tv_category_menu4.setOnClickListener(this);
        tv_category_menu5.setOnClickListener(this);
        tv_category_menu6.setOnClickListener(this);
        tv_category_menu7.setOnClickListener(this);
        tv_category_size1.setOnClickListener(this);
        tv_category_size2.setOnClickListener(this);
        tv_category_size3.setOnClickListener(this);
        tv_category_product1.setOnClickListener(this);
        tv_category_product2.setOnClickListener(this);
        tv_category_product3.setOnClickListener(this);
    }
    void init(){
        tv_category_menu1 = findViewById(R.id.tv_category_menu1);
        tv_category_menu2 = findViewById(R.id.tv_category_menu2);
        tv_category_menu3 = findViewById(R.id.tv_category_menu3);
        tv_category_menu4 = findViewById(R.id.tv_category_menu4);
        tv_category_menu5 = findViewById(R.id.tv_category_menu5);
        tv_category_menu6 = findViewById(R.id.tv_category_menu6);
        tv_category_menu7 = findViewById(R.id.tv_category_menu7);
        tv_category_size1 = findViewById(R.id.tv_category_size1);
        tv_category_size2 = findViewById(R.id.tv_category_size2);
        tv_category_size3 = findViewById(R.id.tv_category_size3);
        tv_category_product1 = findViewById(R.id.tv_category_product1);
        tv_category_product2 = findViewById(R.id.tv_category_product2);
        tv_category_product3 = findViewById(R.id.tv_category_product3);
    }
    private TextView tv_category_menu1;
    private TextView tv_category_menu2;
    private TextView tv_category_menu3;
    private TextView tv_category_menu4;
    private TextView tv_category_menu5;
    private TextView tv_category_menu6;
    private TextView tv_category_menu7;
    private TextView tv_category_size1;
    private TextView tv_category_size2;
    private TextView tv_category_size3;
    private TextView tv_category_product1;
    private TextView tv_category_product2;
    private TextView tv_category_product3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_category_menu1:
                tv_category_menu1.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv_category_menu2:
                break;
            case R.id.tv_category_menu3:
                break;
            case R.id.tv_category_menu4:
                break;
            case R.id.tv_category_menu5:
                break;
            case R.id.tv_category_menu6:
                break;
            case R.id.tv_category_menu7:
                break;
            case R.id.tv_category_size1:
                break;
            case R.id.tv_category_size2:
                break;
            case R.id.tv_category_size3:
                break;
            case R.id.tv_category_product1:
                break;
            case R.id.tv_category_product2:
                break;
            case R.id.tv_category_product3:
                break;
        }
    }
}
