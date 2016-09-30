package royluo.simwechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import royluo.simwechat.R;

/**
 * Created by Administrator on 2016/9/23.
 */

public class MainInCenterFragment_1 extends Fragment {

    public static final String TAG="MainInCenterFragment_1";

    public static MainInCenterFragment_1 newInstance(){
        return new MainInCenterFragment_1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_in_center_fragment_layout_1,container,false);
        initData(view);
        return view;
    }

    private ListView mListView;
    private List<String> dataList;

    private void initData(View view){
        dataList=new ArrayList<>();
        for (int i=0;i<100;i++){
            dataList.add("ITEM_"+i);
        }

        mListView= (ListView) view.findViewById(R.id.listview_main_1);
        mListView.setAdapter(new MyAdapter(getActivity(),dataList));
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> dataList;
        private Context mContext;

        MyAdapter(Context context,List<String> stringList){
            dataList =stringList;
            mContext=context;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view;
            if (convertView==null){
                viewHolder=new ViewHolder();
                view=LayoutInflater.from(mContext).inflate(R.layout.list_item_main_1,parent,false);
                TextView textView= (TextView) view.findViewById(R.id.list_item_text);
                viewHolder.setTextView(textView);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.getTextView().setText(dataList.get(position));
            view.setTag(viewHolder);

            return view;
        }

        class ViewHolder{
            TextView textView;

            public TextView getTextView() {
                return textView;
            }

            public void setTextView(TextView textView) {
                this.textView = textView;
            }
        }

    }

}
