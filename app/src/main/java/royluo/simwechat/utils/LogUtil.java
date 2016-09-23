package royluo.simwechat.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/9/23.
 */

public class LogUtil {

    private static final int CURRENT_LEVEL=2;
    private static final int DEBUG_LEVEL=1;
    private static final int ERROR_LEVEL=2;

    public static void d(String TAG,String logContent){
        if (CURRENT_LEVEL>=DEBUG_LEVEL){
            Log.d(TAG,logContent);
        }
    }

    public static void e(String TAG,String logContent){
        if (CURRENT_LEVEL>=ERROR_LEVEL){
            Log.e(TAG,logContent);
        }
    }

}
