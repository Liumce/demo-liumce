package net.hw.application.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.adpters.TaolunAdapter;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.TaoLunEntity;

import java.util.List;

/**
 * Created by 17828121783 on 2018/5/21.
 */

public class HomepageFragment3 extends Fragment {
    private ListView mLvTaolun;

    public HomepageFragment3(){}
    public HomepageFragment3(Context context) {
        this.mContext = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_taolun,container,false);
            initView(rootView);


        List<TaoLunEntity> taolunList = OperateDbDao.getInstance(mContext).getAllTaolun();
        taolunAdapter = new TaolunAdapter(mContext,taolunList);
        mLvTaolun.setAdapter(taolunAdapter);
        return rootView;
    }

    private TaolunAdapter taolunAdapter;
    private void initView(View rootView) {

        mLvTaolun = (ListView) rootView.findViewById(R.id.lv_taolun);
    }
    private Context mContext;

}
