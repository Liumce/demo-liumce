package net.hw.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.adpters.PaiGridAdapter;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.ProductInfoEntity;

import java.util.List;

public class PaiMaiActivity extends AppCompatActivity {


//    private TextView mIvLeftIcon;
    private TextView mTvTitle;
    private GridView mGvProduct;
    private PaiGridAdapter gridAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai);
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
//        mTvTitle =  (TextView)findViewById(R.id.tv_title);
//        mTvTitle.setText("在线拍卖");
//        mIvLeftIcon =  (TextView)findViewById(R.id.iv_left_icon);
//        mIvLeftIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });
//        final List<ProductInfoEntity> proList = OperateDbDao.getInstance(this)
//                .getAllProduct();
//        gridAdapter = new PaiGridAdapter(this,proList);
//        mGvProduct.setAdapter(gridAdapter);




        final List<ProductInfoEntity> proList = OperateDbDao.getInstance(this)
                .queryByLeiBie("2");
        gridAdapter = new PaiGridAdapter(this,proList);
        mGvProduct.setAdapter(gridAdapter);

        mGvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductInfoEntity productInfoEntity = proList.get(position);
                Intent intent = new Intent(PaiMaiActivity.this, DetailsActivity.class);
                intent.putExtra("product",productInfoEntity);
                startActivity(intent);
            }
        });

    }


    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("在线拍卖");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mGvProduct = (GridView) findViewById(R.id.gv_product);
//        mIvLeftIcon = (TextView) findViewById(R.id.iv_left_icon);
//        mTvTitle = (TextView) findViewById(R.id.tv_title);
//        mGvProduct = (GridView) findViewById(R.id.gv_product);
    }
}
