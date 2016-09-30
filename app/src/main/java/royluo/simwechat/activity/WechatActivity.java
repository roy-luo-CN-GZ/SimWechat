package royluo.simwechat.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import royluo.simwechat.R;
import royluo.simwechat.fragment.BottomBarFragment;
import royluo.simwechat.fragment.MainInCenterFragmentContainer;
import royluo.simwechat.utils.LogUtil;

public class WechatActivity extends AppCompatActivity implements BottomBarFragment.NotifyChanges,MainInCenterFragmentContainer.NotifyChanges{

    private static final String TAG="WechatActivity";

    private int currSelectedItemPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        //托管fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.bottom_bar_container);
        if (fragment==null){
            fragmentManager.beginTransaction()
                    .add(R.id.bottom_bar_container, BottomBarFragment.newInstance())
                    .add(R.id.main_in_center_container, MainInCenterFragmentContainer.newInstance())
                    .commit();
        }

        final Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_in_wechat);
        String title=getResources().getString(R.string.wechat_toolbar_title,"");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        List<String> list=new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        list.add("DDD");

        final MenuPopupWindow menuPopupWindow=new MenuPopupWindow(this,null,0,0);
        menuPopupWindow.setAdapter(new Myadapter(this,list));


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_wechat_search:
                        Toast.makeText(WechatActivity.this,"搜索被点击",Toast.LENGTH_SHORT).show();
                        menuPopupWindow.show();
                        break;
                    case R.id.menu_item_wechat_add:
                        Toast.makeText(WechatActivity.this,"添加被点击",Toast.LENGTH_SHORT).show();
                        menuPopupWindow.dismiss();
                        break;
                }
                return false;
            }
        });

        

    }

    private class Myadapter extends BaseAdapter{

        List<String>mList;
        Context mContext;

        Myadapter(Context context,List<String> list){
            mContext=context;
            mList=list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=LayoutInflater.from(mContext).inflate(R.layout.popupwindow_wechat_activity_add,parent);
            Button button_1= (Button) view.findViewById(R.id.button_1);
            Button button_2= (Button) view.findViewById(R.id.button_2);
            button_1.setText(mList.get(position)+"  Button_1");
            button_1.setText(mList.get(position)+"  Button_2");
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wechat_title_bar,menu);
        MenuItem item= (MenuItem) findViewById(R.id.menu_item_wechat_add);
        
        return super.onCreateOptionsMenu(menu);
    }


    //监听BottomBar item变化然后通知 MainInCenterFragmentContainer
    @Override
    public void itemInBottomHasChanged(int itemPosition) {

        LogUtil.d(TAG,"监听BottomBar item变化然后通知 MainInCenterFragmentContainer  "+itemPosition);

        if (itemPosition!=currSelectedItemPos){
            currSelectedItemPos=itemPosition;
            MainInCenterFragmentContainer.getInstance().itemChanged(itemPosition);
        }


    }

    //监听MainInCenter item变化然后通知 BottomBarFragment
    @Override
    public void itemInCenterChanged(int position) {
        LogUtil.d(TAG,"监听MainInCenter item变化然后通知 BottomBarFragment  "+position);
        if (position!=currSelectedItemPos){
            currSelectedItemPos=position;
            BottomBarFragment.getInstance().changeItem(position);
        }


    }
}
