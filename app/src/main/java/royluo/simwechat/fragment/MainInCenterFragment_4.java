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

public class MainInCenterFragment_4 extends Fragment {

    public static final String TAG="MainInCenterFragment_4";

    public static MainInCenterFragment_4 newInstance(){
        return new MainInCenterFragment_4();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_in_center_fragment_layout_4,container,false);


        return view;
    }

    public interface OnNotify {
        void fragmentCreated(String str);
    }

}
