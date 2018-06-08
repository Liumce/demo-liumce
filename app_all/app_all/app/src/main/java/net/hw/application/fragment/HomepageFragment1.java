package net.hw.application.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import net.hw.application.R;
import net.hw.application.activity.HaiActivity;
import net.hw.application.activity.PaiMaiActivity;
import net.hw.application.activity.ShiPinActivity;
import net.hw.application.adpters.GridAdapter;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.ProductInfoEntity;

import java.util.List;

/**
 * Created by 17828121783 on 2018/5/21.
 */

public class HomepageFragment1 extends Fragment  implements View.OnClickListener{

    private GridView mGvProduct;

    private GridAdapter gridAdapter;
    private TextView mTvShipin;
    private TextView mTvZhibo;
    private TextView mTvHaitao;
    private TextView mTvTiyan;
    private TextView mTvVR;
    private TextView mTvGongxiang;
    private TextView mTvGexing;
    private TextView mTvPaiMain;

    private LinearLayout llHaiTao;
    private LinearLayout llShiPin;
    private LinearLayout llZhibo;
    private LinearLayout llTiYan;
    private LinearLayout llVr;
    private LinearLayout llSheQu;
    private LinearLayout llGeXing;
    private LinearLayout llPaiMai;


    public HomepageFragment1(){}
    public HomepageFragment1(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_msg, container, false);
        init(rootView);
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.homepage_pics, null);
        view.findViewById(R.id.iv_pic).setBackgroundResource(R.mipmap.msg_pic1);
        view_flipper.addView(view);
        view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.homepage_pics, null);
        view.findViewById(R.id.iv_pic).setBackgroundResource(R.mipmap.msg_pic2);
        view_flipper.addView(view);
        view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.homepage_pics, null);
        view.findViewById(R.id.iv_pic).setBackgroundResource(R.mipmap.msg_pic3);
        view_flipper.addView(view);
        view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.homepage_pics, null);
        view.findViewById(R.id.iv_pic).setBackgroundResource(R.mipmap.msg_pic4);
        view_flipper.addView(view);
        view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.homepage_pics,null);
        view.findViewById(R.id.iv_pic).setBackgroundResource(R.mipmap.msg_pic5);
        view_flipper.addView(view);

        initView(rootView);
        final List<ProductInfoEntity> proList = OperateDbDao.getInstance(getContext())
                .getAllProduct();
        gridAdapter = new GridAdapter(getContext(), proList);
        mGvProduct.setAdapter(gridAdapter);

        mGvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ProductInfoEntity productInfoEntity = proList.get(position);
//                Intent intent = new Intent(mContext, DetailsActivity.class);
//                intent.putExtra("product",productInfoEntity);
                Toast.makeText(mContext,"dfghjkjnjh",Toast.LENGTH_SHORT).show();
//                startActivity(intent);
            }
        });
        return rootView;
    }

    private void init(View rootView) {
        view_flipper = rootView.findViewById(R.id.msg_view_flipper);
    }

    private Context mContext;
    private ViewFlipper view_flipper;

    private void initView(View rootView) {
        mGvProduct = (GridView) rootView.findViewById(R.id.gv_product);

        /*mTvShipin = (TextView) rootView.findViewById(R.id.tv_shipin);
        mTvShipin.setOnClickListener(this);
        mTvZhibo = (TextView) rootView.findViewById(R.id.tv_zhibo);
        mTvZhibo.setOnClickListener(this);
        mTvHaitao = (TextView) rootView.findViewById(R.id.tv_haitao);
        mTvHaitao.setOnClickListener(this);
        mTvTiyan = (TextView) rootView.findViewById(R.id.tv_tiyan);
        mTvTiyan.setOnClickListener(this);
        mTvVR = (TextView) rootView.findViewById(R.id.tv_VR);
        mTvVR.setOnClickListener(this);
        mTvGongxiang = (TextView) rootView.findViewById(R.id.tv_gongxiang);
        mTvGongxiang.setOnClickListener(this);
        mTvGexing = (TextView) rootView.findViewById(R.id.tv_gexing);
        mTvGexing.setOnClickListener(this);
        mTvPaiMain = (TextView) rootView.findViewById(R.id.tv_paimai);
        mTvPaiMain.setOnClickListener(this);*/
        llHaiTao = rootView.findViewById(R.id.tv_haitao);
        llHaiTao.setOnClickListener(this);
        llShiPin = rootView.findViewById(R.id.tv_shipin);
        llShiPin.setOnClickListener(this);
        llZhibo = rootView.findViewById(R.id.tv_zhibo);
        llZhibo.setOnClickListener(this);
        llTiYan = rootView.findViewById(R.id.tv_tiyan);
        llVr = rootView.findViewById(R.id.tv_VR);
        llVr.setOnClickListener(this);
        llSheQu = rootView.findViewById(R.id.tv_gongxiang);
        llSheQu.setOnClickListener(this);
        llGeXing = rootView.findViewById(R.id.tv_gexing);
        llGeXing.setOnClickListener(this);
        llPaiMai = rootView.findViewById(R.id.tv_paimai);
        llPaiMai.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_haitao:
                startActivity(new Intent(mContext, HaiActivity.class));
                break;
            case R.id.tv_paimai:
                startActivity(new Intent(mContext, PaiMaiActivity.class));
                break;
            case R.id.tv_gongxiang:
//                startActivity(new Intent(mContext, TaoLunActivity.class));
                Toast.makeText(mContext,"建设中，敬请期待!",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_zhibo:
            case R.id.tv_shipin:
                startActivity(new Intent(mContext, ShiPinActivity.class));
                break;
            case R.id.tv_VR:
                Toast.makeText(mContext,"建设中，敬请期待!",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_tiyan:
                Toast.makeText(mContext,"建设中，敬请期待!",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_gexing:
                Toast.makeText(mContext,"建设中，敬请期待!",Toast.LENGTH_LONG).show();
                break;

        }

    }
}
