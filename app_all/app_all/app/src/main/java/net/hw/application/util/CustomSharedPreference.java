package net.hw.application.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CustomSharedPreference
{
    // 文件名字

    public static final String NNCC_PREFERENCE_FILE = "carshare.file";
    // 是否清除缓存，方便最优惠页面显示
    public static final String PHONENUMEBR            = "phoneNumber";
    public static final String TOKEN = "token";
    public static final String NICKNAME            = "nickName";
    public static final String LNEEDSHOW            = "luckyNeedShow";
    private static final String TAG                  = "NNPreferenceService";

    private Editor editor;
    private SharedPreferences preferences;

    public CustomSharedPreference(Context context, String preferenceName)
    {
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 
     * 保存参数
     */
    public void save(String key, String val)
    {
        LogUtil.d(TAG, key + " : " + val);
        editor.putString(key, val);
        editor.commit();
    }

    /**
     * 
     * 保存参数
     */
    public void saveInt(String key, int val)
    {
        LogUtil.d(TAG, key + " : " + val);
        editor.putInt(key, val);
        editor.commit();
    }
    /**
     * 
     * 保存参数
     */
    public void saveBoolean(String key, boolean val)
    {
        LogUtil.d(TAG, key + " : " + val);
        editor.putBoolean(key, val);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        return preferences.getBoolean(key, defValue);
    }

    /**
     * 
     * 获取各项配置参数
     * 
     * 
     * 
     * @return
     */
    public String getPreferences(String key)
    {
        return preferences.getString(key, "");
    }

    public int getIntPreferences(String key)
    {
        return preferences.getInt(key, 0);
    }

    public int getIntPreferences(String key, int defValue)
    {
        return preferences.getInt(key, defValue);
    }

    /**
     * 
     * 获取各项配置参数,可以指定默认值
     * 
     * 
     * 
     * @return 返回各项配置参数的值，若不存在，返回默认值
     */
    public String getPreferences(String key, String defValue)
    {
        return preferences.getString(key, defValue);
    }
    
    public void delete(String key){
    	preferences.edit().remove(key);
    }
}
