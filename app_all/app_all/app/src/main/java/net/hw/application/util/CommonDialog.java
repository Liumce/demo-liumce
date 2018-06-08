package net.hw.application.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.hw.application.R;


/**
 * 自定义dialog
 * 
 */
public class CommonDialog extends Dialog {


	Context context;
	String content;
	View.OnClickListener onClickListener;
	String title;
	private String confirTxt;
	private String cancelTxt;
	private String confirTxt_add;
	private boolean isButtonShow = true;
	private LinearLayout buttonLayout;
	private boolean isCanceledTouchOutside = false;

	public CommonDialog(Context context, String title, String content ,
                        boolean isButtonShow, boolean isCanceledTouchOutside) {
		super(context, R.style.QNDialog);
		this.context = context;
		this.title = title;
		this.content = content;
		this.isButtonShow = isButtonShow;
		this.isCanceledTouchOutside = isCanceledTouchOutside;
	}

	public CommonDialog(Context context, String title, String content,
                        String confirTxt, String cancelTxt,
                        View.OnClickListener onClickListener, boolean isCanceledTouchOutside) {
		super(context, R.style.QNDialog);
		this.context = context;
		this.title = title;
		this.content = content;

		this.confirTxt = confirTxt;
		this.cancelTxt = cancelTxt;
		this.onClickListener = onClickListener;
		this.isCanceledTouchOutside = isCanceledTouchOutside;
	}

	public CommonDialog(Context context, String title, String content,
                        String confirTxt_add, String confirTxt, String cancelTxt,
                        View.OnClickListener onClickListener) {
		super(context, R.style.QNDialog);
		this.context = context;
		this.title = title;
		this.content = content;
		this.confirTxt_add = confirTxt_add;

		this.confirTxt = confirTxt;
		this.cancelTxt = cancelTxt;
		this.onClickListener = onClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.qn_dialog);
		CommonDialog.this.setCanceledOnTouchOutside(isCanceledTouchOutside);
		// 实例化控件
		Button btn_confir = (Button) findViewById(R.id.btn_confir);
		Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_confir.setText(confirTxt);
		btn_cancel.setText(cancelTxt);
		TextView textv_title = (TextView) findViewById(R.id.textv_title);
		// 设置标题
		textv_title.setText(title);
		TextView textView = (TextView) findViewById(R.id.textv_content);
		textView.setText(content);
		buttonLayout = (LinearLayout) findViewById(R.id.button_lay);
		if (isButtonShow) {
			buttonLayout.setVisibility(View.VISIBLE);
		}else
			buttonLayout.setVisibility(View.GONE);
		// 给确认键设置监听
		btn_confir.setOnClickListener(onClickListener);
		// 点击取消关闭dialog
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CommonDialog.this.dismiss();
			}
		});
		TextView tv_content_add = (TextView) findViewById(R.id.tv_content_add);
		ScrollView relayout_addview_add = (ScrollView) findViewById(R.id.layout_content_add);
		if (confirTxt_add != null && confirTxt_add.trim().length() != 0) {
			tv_content_add.setText(confirTxt_add);
			relayout_addview_add.setVisibility(View.VISIBLE);
		} else {
			relayout_addview_add.setVisibility(View.GONE);
		}

	}
}