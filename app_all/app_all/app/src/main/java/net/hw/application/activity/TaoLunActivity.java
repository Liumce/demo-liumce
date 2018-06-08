package net.hw.application.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.adpters.TaolunAdapter;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.TaoLunEntity;

import java.util.List;

public class TaoLunActivity extends AppCompatActivity {


    //    private TextView mIvLeftIcon;
    private TextView mTvTitle;
    private ListView mLvTaolun;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taolun);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            // 激活状态栏
//            tintManager.setStatusBarTintEnabled(true);
//            // enable navigation bar tint 激活导航栏
//            tintManager.setNavigationBarTintEnabled(true);
//            //设置系统栏设置颜色
//            //tintManager.setTintColor(R.color.red);
//            //给状态栏设置颜色
//            tintManager.setStatusBarTintResource(R.color.bg);
//            //Apply the specified drawable or color resource to the system navigation bar.
//            //给导航栏设置资源
//            tintManager.setNavigationBarTintResource(R.color.bg);
//        }


        initView();

//        mTvTitle = (TextView) findViewById(R.id.tv_title);
//        mTvTitle.setText("互动社区");
//        mIvLeftIcon = (TextView) findViewById(R.id.iv_left_icon);
//        mIvLeftIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });

        List<TaoLunEntity> taolunList = OperateDbDao.getInstance(this).getAllTaolun();
        taolunAdapter = new TaolunAdapter(this, taolunList);
        mLvTaolun.setAdapter(taolunAdapter);

    }

    private TaolunAdapter taolunAdapter;


    private void initView() {

        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("互动社区");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        mIvLeftIcon = (TextView) findViewById(R.id.iv_left_icon);
//        mTvTitle = (TextView) findViewById(R.id.tv_title);
//
//        mLvTaolun = (ListView) findViewById(R.id.lv_taolun);
    }
}
