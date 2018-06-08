package net.hw.application.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginSharedPreference
{
    // 文件名字

    public static final String ID = "id";
    public static final String LOGIN_PREFERENCE_FILE = "login.file";
    public static final String TOKEN            = "token";
    public static final String CURRENT_NAME            = "current_name";
    public static final String LAOREN_ID            = "laorenId";
    public static final String OFFICE_NAME            = "officeName";
    public static final String LOGIN_GRADE           = "leaderGrade";//存1为官兵，2为中层领导，3为上层领导,4 中层角色
    // 是否清除缓存，方便最优惠页面显示
    public static final String PHONENUMEBR            = "phoneNumber";
    public static final String MOBILE_NUMEBR            = "mobileNumber";
    public static final String LOGINNAME            = "loginName";
    public static final String ISLEADER            = "yes";
    public static final String PASSWORD = "password";
    public static final String LOGIN_TYPE = "password";
    public static final String APK_URL = "apkURL";
    public static final String HEAD_IAMGE = "image";

    private static final String TAG                  = "LoginSharedPreference";

    private Editor editor;
    private SharedPreferences preferences;

    public LoginSharedPreference(Context context, String preferenceName)
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
//        if(key.equals(TOKEN)){
//            return "8fd022da39894a90a8e6fbf91ff1987b";
//        }
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
