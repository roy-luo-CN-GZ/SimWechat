package royluo.simwechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import royluo.simwechat.R;

/**
 * Created by Administrator on 2016/9/23.
 */

public class BottomBarFragment extends Fragment implements View.OnTouchListener{

    private static final String TAG="BottomBarFragment";

    //用int按从左到右数序表示选项的位置  选项例如:微信,通讯录,发现,我
    public static final int FIRST_ITEM=0;
    public static final int SECOND_ITEM =1;
    public static final int THIRD_ITEM=2;
    public static final int FOURTH_ITEM=3;

    //储存被选择图标的ID
    private List<Integer> icSelectedId;
    //储存未被选择图标的ID
    private List<Integer> icUnselectedId;
    //储存控件  TextView 和 ImageView;
    private List<ViewHolder> viewHolders;

    //上一个被选择的ITEM 默认-1表示刚初始化
    private int prevSelectedItem=-1;
    //现在被选择的ITEM 默认为0即显示第一项
    private int currSelectedItem=FIRST_ITEM;

    public static BottomBarFragment bottomBarFragment;

    public static BottomBarFragment newInstance(){
        return new BottomBarFragment();
    }

    public static BottomBarFragment getInstance(){
        return bottomBarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomBarFragment=this;
        View view=inflater.inflate(R.layout.bottom_bar_fragment_layout,container,false);
        view.setOnTouchListener(this);
        //初始化数据
        initData(view);

        return view;

    }

    private ImageView img_0;
    private ImageView img_1;
    private ImageView img_2;
    private ImageView img_3;
    private TextView text_0;
    private TextView text_1;
    private TextView text_2;
    private TextView text_3;
    private LinearLayout item_1;
    private LinearLayout item_2;
    private LinearLayout item_3;
    private LinearLayout item_4;


    private void initData(View view){

        img_0= (ImageView) view.findViewById(R.id.ic_1_in_bottom_bar);
        img_1= (ImageView) view.findViewById(R.id.ic_2_in_bottom_bar);
        img_2= (ImageView) view.findViewById(R.id.ic_3_in_bottom_bar);
        img_3= (ImageView) view.findViewById(R.id.ic_4_in_bottom_bar);
        text_0= (TextView) view.findViewById(R.id.text_1_in_bottom_bar);
        text_1= (TextView) view.findViewById(R.id.text_2_in_bottom_bar);
        text_2= (TextView) view.findViewById(R.id.text_3_in_bottom_bar);
        text_3= (TextView) view.findViewById(R.id.text_4_in_bottom_bar);

        item_1= (LinearLayout) view.findViewById(R.id.item_1_bottom_bar);
        item_2= (LinearLayout) view.findViewById(R.id.item_2_bottom_bar);
        item_3= (LinearLayout) view.findViewById(R.id.item_3_bottom_bar);
        item_4= (LinearLayout) view.findViewById(R.id.item_4_bottom_bar);

        icSelectedId=new ArrayList();
        icSelectedId.add(R.mipmap.wechat_s);
        icSelectedId.add(R.mipmap.communication_s);
        icSelectedId.add(R.mipmap.discover_s);
        icSelectedId.add(R.mipmap.mine_s);

        icUnselectedId=new ArrayList<>();
        icUnselectedId.add(R.mipmap.wechat);
        icUnselectedId.add(R.mipmap.communication);
        icUnselectedId.add(R.mipmap.discover);
        icUnselectedId.add(R.mipmap.mine);

        viewHolders=new ArrayList<>();
        viewHolders.add(new ViewHolder(text_0,img_0));
        viewHolders.add(new ViewHolder(text_1,img_1));
        viewHolders.add(new ViewHolder(text_2,img_2));
        viewHolders.add(new ViewHolder(text_3,img_3));

        itemChange();
    }

    //通知有item被选择,执行相关操作
    private void itemChange(){

        //判断是否刚初始化
        if (prevSelectedItem!=-1){
            ViewHolder unSelectedViewHolder=viewHolders.get(prevSelectedItem);
            unSelectedViewHolder.getmImageView().setImageResource(icUnselectedId.get(prevSelectedItem));
            unSelectedViewHolder.getmTextView().setTextColor(ContextCompat.getColor(getActivity(),R.color.grayLight));

            //通知activity item选择有变化
            if (getActivity() instanceof NotifyChanges){
                ((NotifyChanges)getActivity()).itemInBottomHasChanged(currSelectedItem);
            }else {
                throw new ClassCastException("Activity必须实现NotifyChanges接口才可以监听到Item选择的变化");
            }

        }
        ViewHolder selectedViewHolder=viewHolders.get(currSelectedItem);
        selectedViewHolder.getmImageView().setImageResource(icSelectedId.get(currSelectedItem));
        selectedViewHolder.getmTextView().setTextColor(ContextCompat.getColor(getActivity(),R.color.greenDark));

    }

    //提供给Activity改变item的方法
    public void changeItem(int itemPostion){
        prevSelectedItem=currSelectedItem;
        currSelectedItem=itemPostion;
        itemChange();
    }

    //记录按下时item位置 -1表示从DOWN到MOVE到UP过程中item超出范围
    private int itemPostion =-1;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                itemPostion =calculatePos(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                int tempPos=calculatePos(event.getX(),event.getY());
                if (tempPos!= itemPostion){
                    itemPostion =-1;
                }
                break;

            case MotionEvent.ACTION_UP:
                //如果从DOWN到MOVE到UP过程中item始终没有超出范围 并且 item之前并未被选择
                if (itemPostion !=-1 && itemPostion!=currSelectedItem){
                    prevSelectedItem=currSelectedItem;
                    currSelectedItem= itemPostion;
                    itemChange();
                }
                break;
        }

        return true;
    }

    //根据坐标返回所在item位置
    private int calculatePos(float x,float y){
        int position=-1;
        //超出BottomBar范围直接返回-1
        if (y<0){
            return position;
        }

        if (x<=item_1.getRight()){
            position=FIRST_ITEM;
        }else if (x<=item_2.getRight()){
            position=SECOND_ITEM;
        }else if (x<=item_3.getRight()){
            position=THIRD_ITEM;
        }else {
            position=FOURTH_ITEM;
        }
        return position;
    }


    //自定义class存放控件
    private class ViewHolder{

        private TextView mTextView;
        private ImageView mImageView;

        private ViewHolder(TextView textView,ImageView imageView){
            mTextView=textView;
            mImageView=imageView;
        }

        public TextView getmTextView() {
            return mTextView;
        }

        public ImageView getmImageView() {
            return mImageView;
        }
    }

    //给ACTIVITY的接口,通知变化
    public interface NotifyChanges {
        void itemInBottomHasChanged(int itemPosition);
    }

}
