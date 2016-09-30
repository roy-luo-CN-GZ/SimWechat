package royluo.simwechat.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/9/24.
 */

public class MainInCenterViewPager extends ViewPager {

    public MainInCenterViewPager(Context context) {
        this(context,null);
    }

    public MainInCenterViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
