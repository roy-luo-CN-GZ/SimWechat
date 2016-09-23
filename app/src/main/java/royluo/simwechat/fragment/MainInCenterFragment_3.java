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

public class MainInCenterFragment_3 extends Fragment {

    public static final String TAG="MainInCenterFragment_3";

    public static MainInCenterFragment_3 newInstance(){
        return new MainInCenterFragment_3();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_in_center_fragment_layout_3,container,false);

        //用接口通知Activity此Fragment已加载
        if (getActivity() instanceof OnNotify){
            ((OnNotify) getActivity()).fragmentCreated(TAG);
        }else {
            throw new ClassCastException("托管MainInCenterFragment_3的Activity必须实现此Fragment中的OnNotiy接口");
        }

        return view;
    }

    public interface OnNotify {
        void fragmentCreated(String str);
    }

}
