package royluo.simwechat.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import royluo.simwechat.R;
import royluo.simwechat.fragment.BottomBarFragment;
import royluo.simwechat.fragment.MainInCenterFragment_1;
import royluo.simwechat.fragment.MainInCenterFragment_2;
import royluo.simwechat.fragment.MainInCenterFragment_3;
import royluo.simwechat.fragment.MainInCenterFragment_4;

public class WechatActivity extends AppCompatActivity implements MainInCenterFragment_1.OnNotify,MainInCenterFragment_2.OnNotify,
        MainInCenterFragment_3.OnNotify,MainInCenterFragment_4.OnNotify{

    private static final String TAG="WechatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.main_in_center_container);
        if (fragment==null){
            fragmentManager.beginTransaction()
                    .add(R.id.bottom_bar_container, BottomBarFragment.newInstance())
                    .add(R.id.main_in_center_container, MainInCenterFragment_1.newInstance(),MainInCenterFragment_1.TAG)
                    .add(R.id.main_in_center_container, MainInCenterFragment_2.newInstance(),MainInCenterFragment_2.TAG)
                    .add(R.id.main_in_center_container, MainInCenterFragment_3.newInstance(),MainInCenterFragment_3.TAG)
                    .add(R.id.main_in_center_container, MainInCenterFragment_4.newInstance(),MainInCenterFragment_4.TAG)
                    .commit();
        }

    }

    //监听fragment创建情况
    @Override
    public void fragmentCreated(String str) {
        if (MainInCenterFragment_1.TAG.equals(str)){

        }else if (MainInCenterFragment_2.TAG.equals(str)){

        }else if (MainInCenterFragment_3.TAG.equals(str)){

        }else if (MainInCenterFragment_4.TAG.equals(str)){

        }
    }

}
