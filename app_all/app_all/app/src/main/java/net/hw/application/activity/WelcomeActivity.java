package net.hw.application.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;



import net.hw.application.R;
import net.hw.application.util.CommonUtils;
import net.hw.application.util.LoginSharedPreference;
import net.hw.application.util.ScreenUtils;


public class WelcomeActivity extends Activity {

    private int guideVersion; // 用于标志引导页是否更新
    private int versionCode = 0;
    public static final int VERIFY_SUCCESS = 7001; // 判断是否被重新登陆

    public static final int VERIFY_FAILURE = 7002;
    private boolean isAldreadySent = false;
    private long waitingTime = 2000;
    private ImageView welBgFromNetView;
    private LoginSharedPreference sp ;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    /**
                     * 如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
                     */
                    // if (guideVersion != versionCode) {
                    // Intent intent = new
                    // Intent(WelcomeActivity.this,SplashActivity.class);
                    // intent.putExtra("oldVersion", versionCode);
                    // startActivity(intent);
                    // } else {
 /*                   if (CommonUtils.isEmpty(sp.getPreferences(LoginSharedPreference.TOKEN))){
                        startActivity(new Intent(WelcomeActivity.this,
                                LoginActivity.class));
                    }else{*/
                        startActivity(new Intent(WelcomeActivity.this,
                                TeaMainActivity.class));
           /*             Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(MessageReceiver.ACTION_LOGIN_SUCCESS_BROADCASE);
                        WelcomeActivity.this.sendBroadcast(intent);*/
            //        }




                    overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                    // }
                    finish();
                    break;
                case VERIFY_SUCCESS:
                    break;
                case VERIFY_FAILURE:
                    // LoginInfoUtil.clearLoginInfo(WelcomeActivity.this);
                    break;
                default:
                    break;
            }
        };
    };
    protected String verifyStatus;
    protected String phoneNumber;
    protected String password;
    protected Bitmap bitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 渠道
        // AnalyticsConfig.setChannel(channelArr[CHANNELID]);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
        setContentView(R.layout.activity_welcome);
        welBgFromNetView = (ImageView) findViewById(R.id.wel_bg_from_net);

        bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.welcome);
        bitmap = zoomImg(bitmap);
        sp = new LoginSharedPreference(WelcomeActivity.this,LoginSharedPreference.LOGIN_PREFERENCE_FILE);
/*        if (bitmap !=null) {
            welBgFromNetView.setImageBitmap(bitmap);
        }else {
            welBgFromNetView.setImageResource(R.mipmap.welcome);
        }*/

        // versionCode = NNCCUtils.getVersionCode(getApplicationContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!isAldreadySent) {
                    handler.sendEmptyMessage(0);
                }
            }
        }, waitingTime);

    }

    private Bitmap zoomImg(Bitmap bm) {
        int screenHeight = ScreenUtils.getScreenHeight(this);
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleHeight = ((float) screenHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
                true);
        return newbm;
    }


    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
