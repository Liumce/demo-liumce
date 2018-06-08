package net.hw.application.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import net.hw.application.R;
import net.hw.application.TeaCustomView.TeaCustomBar;

import java.util.List;

/**
 * Created by 17828121783 on 2018/5/21.
 */

public class HomepageFragment2 extends Fragment {

    public HomepageFragment2(){}
    public HomepageFragment2(Context context) {
        this.mContext = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_xx,container,false);
        init(rootView);


        return rootView;
    }
    private void init(View rootView){

    }
    private Context mContext;
    private ViewFlipper view_flipper;
    private TeaCustomBar tctb_menu;
    private List<String> stringList;
}
