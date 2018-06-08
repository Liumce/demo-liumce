package net.hw.application.adpters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 17828121783 on 2018/5/21.
 */

public class HomepageFragmentAdapter extends FragmentPagerAdapter{


    public HomepageFragmentAdapter(FragmentManager fm,List<Fragment> mDataSource) {
        super(fm);
        this.mDataSource = mDataSource;
    }

    @Override
    public Fragment getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    private List<Fragment> mDataSource;
}
