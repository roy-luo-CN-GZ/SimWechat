package royluo.simwechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import royluo.simwechat.R;
import royluo.simwechat.bean.Share;
import royluo.simwechat.utils.LogUtil;

/**
 * Created by Administrator on 2016/9/24.
 */

public class MainInCenterFragmentContainer extends Fragment implements ViewPager.OnPageChangeListener{

    //用int按从左到右数序表示选项的位置  选项例如:微信,通讯录,发现,我
    public static final int FIRST_ITEM=0;
    public static final int SECOND_ITEM =1;
    public static final int THIRD_ITEM=2;
    public static final int FOURTH_ITEM=3;

    public static final String TAG="MainInCenterFragmentContainer";

    public static MainInCenterFragmentContainer mainInCenterFragmentContainer;

    public static MainInCenterFragmentContainer newInstance(){
        return new MainInCenterFragmentContainer();
    }

    public static MainInCenterFragmentContainer getInstance(){
        return mainInCenterFragmentContainer;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainInCenterFragmentContainer=this;

        View view=inflater.inflate(R.layout.main_in_center_fragment_container_layout,container,false);
        //初始化数据
        initDate(view);

        LogUtil.d(TAG, Share.ITEM_POSITION.FOURTH+"");

        return view;
    }

    //用于存放ViewPager中需要使用的fragment
    private List<Fragment> fragments;
    public static ViewPager mViewPager;
    private FragmentManager fm;

    private void initDate(View view){

        fm=getActivity().getSupportFragmentManager();

        fragments=new ArrayList<>();
        fragments.add(MainInCenterFragment_1.newInstance());
        fragments.add(MainInCenterFragment_2.newInstance());
        fragments.add(MainInCenterFragment_3.newInstance());
        fragments.add(MainInCenterFragment_4.newInstance());

        mViewPager= (ViewPager) view.findViewById(R.id.main_view_pager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter(fm));
        mViewPager.addOnPageChangeListener(this);

    }

    //供Activity修改item的方法
    public void itemChanged(int itemPosition){
        mViewPager.setCurrentItem(itemPosition,false);
    }

    //pageChanged监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //pageChanged监听
    @Override
    public void onPageSelected(int position) {

        if (getActivity() instanceof NotifyChanges){
            ((NotifyChanges)getActivity()).itemInCenterChanged(position);
        }else {
            throw new ClassCastException("Activity必须实现NotifyChanged接口监听item变化");
        }

    }
    //pageChanged监听
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public interface NotifyChanges{
        void itemInCenterChanged(int position);
    }

}
