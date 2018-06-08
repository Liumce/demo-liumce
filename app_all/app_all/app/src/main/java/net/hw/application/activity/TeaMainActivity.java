package net.hw.application.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.hw.application.CusomViewPager.CustomViewPager;
import net.hw.application.R;
import net.hw.application.adpters.HomepageFragmentAdapter;
import net.hw.application.fragment.HomepageFragment1;
import net.hw.application.fragment.HomepageFragment2;
import net.hw.application.fragment.HomepageFragment3;
import net.hw.application.fragment.HomepageFragment4;
import net.hw.application.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 17828121783 on 2018/5/16.
 */

public class TeaMainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarLayout mAppBarLayout;
    private AppBarLayout mAppBarLayout2;
    private TextView tvTitle;
    private Toolbar toolbar;
    private ImageView mIvTabHome;
    private TextView mTvTabHome;
    private ImageView mIvTabMarket;
    private TextView mTvTabMarket;
    private ImageView mIvTabmid;
    private TextView mTvTabmid;
    private ImageView mIvTabgou;
    private TextView mTvTabgou;
    private ImageView mIvTabmy;
    private TextView mTvTabmy;
    //private LinearLayout ll_title;
    private LinearLayout ll_root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tea_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            /*//透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.bg);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            tintManager.setNavigationBarTintResource(R.color.bg);*/
        }
        initView();
        // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.tea_title);
        init();
        //search_title_homepage.setOnClickListener(this);
        //btn_category_homepage.setOnClickListener(this);
        //btn_shop_cart_homepage.setOnClickListener(this);
        iv_tab_home.setOnClickListener(this);
        iv_tab_market.setOnClickListener(this);
        mIvTabmid.setOnClickListener(this);
        mIvTabgou.setOnClickListener(this);
        mIvTabmy.setOnClickListener(this);
        //vp_content.addOnPageChangeListener(this);
        vp_content.setScanScroll(false);
        vp_content.setOffscreenPageLimit(3);
        adapter = new HomepageFragmentAdapter(getSupportFragmentManager(), mDataSource);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(0);

    }

    private void init() {



        /*search_title_homepage = (LinearLayout) findViewById(R.id.search_title_homepage);
        btn_shop_cart_homepage = (Button) findViewById(R.id.btn_shop_cart_homepage);*/
        //btn_category_homepage = (Button) findViewById(R.id.btn_category_homepage);
        iv_tab_home = (ImageView) findViewById(R.id.iv_tab_home);
        iv_tab_home.setImageResource(R.mipmap.home_choose_logo);
        iv_tab_market = (ImageView) findViewById(R.id.iv_tab_market);
        vp_content = (CustomViewPager) findViewById(R.id.vp_content);
        mDataSource = new ArrayList<>();
        mDataSource.add(new HomepageFragment1(this));
        mDataSource.add(new HomepageFragment3(this));
        mDataSource.add(new HomepageFragment2(this));
        mDataSource.add(new HomepageFragment4(this));
        mDataSource.add(new PersonFragment(this));
    }

    private LinearLayout search_title_homepage;
    private Button btn_shop_cart_homepage;
    //private Button btn_category_homepage;
    private ImageView iv_tab_home;
    private ImageView iv_tab_market;

    private CustomViewPager vp_content;
    private HomepageFragmentAdapter adapter;
    private List<Fragment> mDataSource;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.search_title_homepage:
                Toast.makeText(this, "你点击了搜索框", Toast.LENGTH_SHORT).show();
                break;*/
            /*case R.id.btn_category_homepage:

                break;*/
            /*case R.id.btn_shop_cart_homepage:
                Toast.makeText(this, "你点击了购物车", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.iv_tab_home:
                mAppBarLayout.setVisibility(View.VISIBLE);
                mAppBarLayout2.setVisibility(View.GONE);
                vp_content.setCurrentItem(0);
                mIvTabmy.setImageResource(R.drawable.icon_tab_mine_normal_selector);
                mIvTabHome.setImageResource(R.mipmap.home_choose_logo);
                mIvTabMarket.setImageResource(R.drawable.icon_tab_market_normal_selector);
                mIvTabmid.setImageResource(R.drawable.icon_tab_mid_normal_selector);
                mIvTabgou.setImageResource(R.drawable.icon_tab_message_normal_selector);
                //ll_title.setVisibility(View.VISIBLE);
                ll_root.setBackgroundResource(R.color.bg);
                break;
            case R.id.iv_tab_market:
                tvTitle.setText("资讯信息");
                mAppBarLayout.setVisibility(View.GONE);
                mAppBarLayout2.setVisibility(View.VISIBLE);
                vp_content.setCurrentItem(1);
                mIvTabmy.setImageResource(R.drawable.icon_tab_mine_normal_selector);
                mIvTabHome.setImageResource(R.drawable.icon_tab_home_normal_selector);
                mIvTabMarket.setImageResource(R.mipmap.msg_choose_logo);
                mIvTabmid.setImageResource(R.drawable.icon_tab_mid_normal_selector);
                mIvTabgou.setImageResource(R.drawable.icon_tab_message_normal_selector);

                //ll_title.setVisibility(View.GONE);
                ll_root.setBackgroundResource(R.color.white);

                break;
            case R.id.iv_tabmid:
                tvTitle.setText("社区分享");
                mAppBarLayout.setVisibility(View.GONE);
                mAppBarLayout2.setVisibility(View.VISIBLE);
                vp_content.setCurrentItem(2);

                mIvTabmy.setImageResource(R.drawable.icon_tab_mine_normal_selector);
                mIvTabHome.setImageResource(R.drawable.icon_tab_home_normal_selector);
                mIvTabMarket.setImageResource(R.drawable.icon_tab_market_normal_selector);
                mIvTabmid.setImageResource(R.mipmap.mid_choose_logo);
                mIvTabgou.setImageResource(R.drawable.icon_tab_message_normal_selector);

//                ll_title.setVisibility(View.GONE);
                ll_root.setBackgroundResource(R.color.white);
                break;
            case R.id.iv_tabgou:
                tvTitle.setText(" 我的购物车");
                mAppBarLayout.setVisibility(View.GONE);
                mAppBarLayout2.setVisibility(View.GONE);
                vp_content.setCurrentItem(3);
               // startActiviy(new Intent(TeaMainActivity.this, VideoActivity.class));
                mIvTabmy.setImageResource(R.drawable.icon_tab_mine_normal_selector);
                mIvTabHome.setImageResource(R.drawable.icon_tab_home_normal_selector);
                mIvTabMarket.setImageResource(R.drawable.icon_tab_market_normal_selector);
                mIvTabmid.setImageResource(R.drawable.icon_tab_mid_normal_selector);
                mIvTabgou.setImageResource(R.mipmap.gou_choose_logo);

//                ll_title.setVisibility(View.GONE);
                ll_root.setBackgroundResource(R.color.white);
                break;
            case R.id.iv_tabmy:
                tvTitle.setText("个人中心");
                mAppBarLayout.setVisibility(View.GONE);
                mAppBarLayout2.setVisibility(View.VISIBLE);
                vp_content.setCurrentItem(4);
                mIvTabmy.setImageResource(R.mipmap.my_choose_logo);
                mIvTabHome.setImageResource(R.drawable.icon_tab_home_normal_selector);
                mIvTabMarket.setImageResource(R.drawable.icon_tab_market_normal_selector);
                mIvTabmid.setImageResource(R.drawable.icon_tab_mid_normal_selector);
                mIvTabgou.setImageResource(R.drawable.icon_tab_message_normal_selector);

//                ll_title.setVisibility(View.GONE);
                ll_root.setBackgroundResource(R.color.white);
                break;

        }
    }

    private void initView() {
        mAppBarLayout = findViewById(R.id.appbarlayout);
        mAppBarLayout2 = findViewById(R.id.appbarlayout2);
        tvTitle = findViewById(R.id.tv_title);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_category_homepage);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeaMainActivity.this, CategoryActivity.class));
            }
        });
        mIvTabHome = (ImageView) findViewById(R.id.iv_tab_home);
        mTvTabHome = (TextView) findViewById(R.id.tv_tab_home);
        mIvTabMarket = (ImageView) findViewById(R.id.iv_tab_market);
        mTvTabMarket = (TextView) findViewById(R.id.tv_tab_market);
        mIvTabmid = (ImageView) findViewById(R.id.iv_tabmid);
        mTvTabmid = (TextView) findViewById(R.id.tv_tabmid);
        mIvTabgou = (ImageView) findViewById(R.id.iv_tabgou);
        mTvTabgou = (TextView) findViewById(R.id.tv_tabgou);
        mIvTabmy = (ImageView) findViewById(R.id.iv_tabmy);
        mTvTabmy = (TextView) findViewById(R.id.tv_tabmy);
//        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shop_car, menu);
        return true;
    }
}
