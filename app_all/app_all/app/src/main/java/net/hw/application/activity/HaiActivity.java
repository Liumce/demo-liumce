package net.hw.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.adpters.HaiGridAdapter;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.ProductInfoEntity;

import java.util.List;

public class HaiActivity extends AppCompatActivity {


    private GridView mGvProduct;
    private HaiGridAdapter gridAdapter;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai);



        initView();

        final List<ProductInfoEntity> proList = OperateDbDao.getInstance(this)
                .getAllProduct();
        gridAdapter = new HaiGridAdapter(this,proList);
        mGvProduct.setAdapter(gridAdapter);
        mGvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductInfoEntity productInfoEntity = proList.get(position);
                Intent intent = new Intent(HaiActivity.this, DetailsActivity.class);
                intent.putExtra("product",productInfoEntity);
                startActivity(intent);
            }
        });

    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("海淘市集");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mGvProduct = (GridView) findViewById(R.id.gv_product);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
