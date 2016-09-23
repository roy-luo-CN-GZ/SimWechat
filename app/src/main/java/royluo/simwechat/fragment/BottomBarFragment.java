package royluo.simwechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import royluo.simwechat.R;

/**
 * Created by Administrator on 2016/9/23.
 */

public class BottomBarFragment extends Fragment {

    public static BottomBarFragment newInstance(){
        return new BottomBarFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.bottom_bar_fragment_layout,container,false);

        return view;

    }
}
