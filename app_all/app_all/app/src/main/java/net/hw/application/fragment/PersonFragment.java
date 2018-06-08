package net.hw.application.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.util.CommonDialog;
import net.hw.application.util.LoginSharedPreference;

import java.io.File;

public class PersonFragment extends Fragment implements View.OnClickListener{

	private Button exitbtn;
	private CommonDialog dialog;
	private LoginSharedPreference sp ;
	private TextView tv_name;
	private TextView tv_phone;
	private TextView tv_finish_order;
	private RelativeLayout aboutus_layout;
	private RelativeLayout rl_inputfinger;
	private RelativeLayout myprofile_layout;
	private ImageView headImg;
	private Context mContext;

	public PersonFragment(){}
	public PersonFragment(Context context) {
		this.mContext = context;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.forth_fragment, container,
				false);
		exitbtn = (Button) rootView.findViewById(R.id.exitbtn);
		headImg = (ImageView) rootView.findViewById(R.id.headImg);
		tv_name = (TextView) rootView.findViewById(R.id.tv_name);
		tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
		tv_finish_order = (TextView) rootView.findViewById(R.id.tv_finish_order);
		aboutus_layout = (RelativeLayout) rootView.findViewById(R.id.aboutus_layout);
		myprofile_layout = (RelativeLayout) rootView.findViewById(R.id.myprofile_layout);
		sp = new LoginSharedPreference(getActivity(),LoginSharedPreference.LOGIN_PREFERENCE_FILE);
//		tv_name.setText(sp.getPreferences(LoginSharedPreference.CURRENT_NAME));
        if(sp.getPreferences(LoginSharedPreference.LOGIN_TYPE).equals("2")){
            tv_name.setText("当前登录账号");
        }else{
            tv_name.setText("当前登录账号");
        }

		//tv_phone.setText(sp.getPreferences(LoginSharedPreference.PHONENUMEBR));
		exitbtn.setOnClickListener(this);
		tv_finish_order.setOnClickListener(this);
		aboutus_layout.setOnClickListener(this);
		myprofile_layout.setOnClickListener(this);
		rl_inputfinger = (RelativeLayout) rootView.findViewById(R.id.rl_inputfinger);
		rl_inputfinger.setOnClickListener(this);
		File file = new File(sp.getPreferences(LoginSharedPreference.HEAD_IAMGE));
		if (file.exists()) {
			Bitmap bm = BitmapFactory.decodeFile(sp.getPreferences(LoginSharedPreference.HEAD_IAMGE));
			//将图片显示到ImageView中
			headImg.setImageBitmap(bm);
		}
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.rl_inputfinger://历史订单
		/*		Intent intent = new Intent(getContext(), InputFingerPrintActivity.class);
				startActivity(intent);*/
				break;

			case  R.id.aboutus_layout:
//				 intent = new Intent(getContext(), LibFpDemo.class);
//				startActivity(intent);
				break;
			case  R.id.myprofile_layout:
			case  R.id.tv_finish_order://个人信息
	/*			 intent = new Intent(getContext(), FinishedOrderActivity.class);
				startActivity(intent);*/
				break;
			case R.id.exitbtn:
				//test
//				intent = new Intent(getContext(), PingLunActivity.class);
//				startActivity(intent);
				//end test
				dialog = new CommonDialog(getActivity(), "退出登录", "是否确定切换账号？", "确定", "取消", new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();

						sp.save(LoginSharedPreference.TOKEN,"");
						sp.save(LoginSharedPreference.ID,"");
						sp.save(LoginSharedPreference.MOBILE_NUMEBR,"");
	/*					startActivity(new Intent(getActivity(),
								LoginActivity.class));*/
//							AppManager.getAppManager().finishActivity(DActivity.class);
//							AppManager.getAppManager().finishActivity(CActivity.class);
//							AppManager.getAppManager().finishActivity(BActivity.class);
						getActivity().finish();

					}
				}, false);
				dialog.show();
				break;
		}

	}
}
