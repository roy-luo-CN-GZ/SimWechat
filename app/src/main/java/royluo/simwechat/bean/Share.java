package royluo.simwechat.bean;

/**
 * Created by Administrator on 2016/9/24.
 */

public class Share {

    public enum ITEM_POSITION {
        OUT_OF_RANGE(-1),FIRST(0),SECOND(1),THIRD(2),FOURTH(3);

        private int mPostion;

        private ITEM_POSITION(int position){
            mPostion=position;
        }

        public int getInt() {
            return mPostion;
        }
    }

}
