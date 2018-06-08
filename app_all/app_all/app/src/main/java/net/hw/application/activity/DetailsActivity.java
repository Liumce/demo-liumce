package net.hw.application.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.android.QuickAdapter;

import net.hw.application.R;
import net.hw.application.dao.OperateDbDao;
import net.hw.application.entity.GouWuEntity;
import net.hw.application.entity.ProductInfoEntity;
import net.hw.application.library.GradationScrollView;
import net.hw.application.library.NoScrollListView;
import net.hw.application.library.ScrollViewContainer;
import net.hw.application.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends Activity implements GradationScrollView.ScrollViewListener {

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;
    @Bind(R.id.iv_good_detai_img)
    ImageView iv;
    @Bind(R.id.ll_good_detail)
    RelativeLayout llTitle;
    @Bind(R.id.ll_offset)
    LinearLayout llOffset;
    @Bind(R.id.iv_good_detai_collect_select)
    ImageView ivCollectSelect;//收藏选中
    @Bind(R.id.iv_good_detai_collect_unselect)
    ImageView ivCollectUnSelect;//收藏未选中
    @Bind(R.id.sv_container)
    ScrollViewContainer container;
    @Bind(R.id.tv_good_detail_title_good)
    TextView tvGoodTitle;
    @Bind(R.id.nlv_good_detial_imgs)
    NoScrollListView nlvImgs;//图片详情
    private QuickAdapter<String> imgAdapter;
    private List<String> imgsUrl;

    private int height;
    private int width;
    private ImageView mIvGoodDetaiImg;
    private TextView mTvPrice;
    private TextView mTvTitle;
    private TextView mTvGoodDetailDiscount;
    private TextView mTvSalecount;
    private LinearLayout mLlOffset;
    private LinearLayout mLlGoodDetailService;
    private TextView mTvTmp1;
    private LinearLayout mRlGoodDetailJin;
    private TextView mTvAuthor;
    private TextView mTvPinglun;
    private TextView mTvTalentDetailOpen;
    private TextView mTvGoodDetailTuodong;
    private TextView mTvGoodDetailDaodi;
    private ImageView mIvGoodDetaiBack;
    private TextView mTvGoodDetailTitleGood;
    private ImageView mIvGoodDetaiShop;
    private ImageView mIvGoodDetaiShare;
    private RelativeLayout mLlGoodDetail;
    private TextView mTvGoodDetailShop;
    private ImageView mIvGoodDetaiCollectUnselect;
    private ImageView mIvGoodDetaiCollectSelect;
    private LinearLayout mLlGoodDetailCollect;
    private TextView mTvGoodDetailShopCart;
    private TextView mTvGoodDetailBuy;
    private LinearLayout mLlGoodDetailBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_details);

        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            // 激活状态栏
//            tintManager.setStatusBarTintEnabled(true);
//            // enable navigation bar tint 激活导航栏
//            tintManager.setNavigationBarTintEnabled(true);
//            //设置系统栏设置颜色
//            //tintManager.setTintColor(R.color.red);
//            //给状态栏设置颜色
//            tintManager.setStatusBarTintResource(R.color.bg);
//            //Apply the specified drawable or color resource to the system navigation bar.
//            //给导航栏设置资源
//            tintManager.setNavigationBarTintResource(R.color.bg);
//        }
        ButterKnife.bind(this);
        //透明状态栏
        StatusBarUtil.setTranslucentForImageView(this, llOffset);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llOffset.getLayoutParams();
        params1.setMargins(0, -StatusBarUtil.getStatusBarHeight(this) / 4, 0, 0);
        llOffset.setLayoutParams(params1);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        params.height = getScreenHeight(this) * 2 / 3;
        iv.setLayoutParams(params);

        container = new ScrollViewContainer(getApplicationContext());


//        initImgDatas();

        initListeners();
        initView();
        Intent intent = getIntent();
        final ProductInfoEntity product = (ProductInfoEntity) intent.getSerializableExtra("product");
        mIvGoodDetaiImg.setImageDrawable
                (ContextCompat.getDrawable(this,getResource(product.getIconUrl())));
        mTvPrice.setText("￥"+product.getPrice()+"元");
        mTvTitle.setText(product.getUname());
        mTvSalecount.setText("销售数量："+product.getSalesCount()+"件");
        mTvPinglun.setText(product.getPinglun());
        mIvGoodDetaiBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvGoodDetailShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this,"加入成功!",Toast.LENGTH_LONG).show();
                List<GouWuEntity> list = new ArrayList<GouWuEntity>();
                GouWuEntity gouWuEntity = new GouWuEntity();
                gouWuEntity.setUid(product.getUid());
                gouWuEntity.setUname(product.getUname());
                gouWuEntity.setAuthor("test");
                gouWuEntity.setIconUrl(product.getIconUrl());
                gouWuEntity.setLeibie(product.getLeibie());
                gouWuEntity.setPrice(product.getPrice());
                gouWuEntity.setPinglun(product.getPinglun());
                gouWuEntity.setJianjie(product.getJianjie());
                list.add(gouWuEntity);
                OperateDbDao.getInstance(DetailsActivity.this).insertGouWu(list);
            }
        });

    }


    /**
     * 获取图片名称获取图片的资源id的方法
     * @param imageName
     * @return
     */
    public int getResource(String imageName) {

        int id = this.getResources().getIdentifier(imageName, "mipmap", this.getPackageName());
        return id;
/*        Resources res=context.getResources();
        int picid = res.getIdentifier(imageName,"mipmap",context.getPackageName());
        return picid;*/
    }
    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

//    // TODO: 16/8/21 模拟图片假数据
//    private void initImgDatas(){
//        width = getScreenWidth(getApplicationContext());
//        imgsUrl = new ArrayList<>();
//        imgsUrl.add("https://img.alicdn.com/imgextra/i4/714288429/TB2dLhGaVXXXXbNXXXXXXXXXXXX-714288429.jpg");
//        imgsUrl.add("https://img.alicdn.com/imgextra/i3/726966853/TB2vhJ6lXXXXXbJXXXXXXXXXXXX_!!726966853.jpg");
//        imgsUrl.add("https://img.alicdn.com/imgextra/i4/2081314055/TB2FoTQbVXXXXbuXpXXXXXXXXXX-2081314055.png");
//        imgAdapter = new QuickAdapter<String>(this,R.layout.adapter_good_detail_imgs) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, String item) {
//                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
//                params.width = width;
//                params.height = width/2;
//                iv.setLayoutParams(params);
//                MyImageLoader.getInstance().displayImageCen(getApplicationContext(),item,iv,width,width/2);
//            }
//        };
//        imgAdapter.addAll(imgsUrl);
//        nlvImgs.setAdapter(imgAdapter);
//    }

    private void initListeners() {

        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = iv.getHeight();

                scrollView.setScrollViewListener(DetailsActivity.this);
            }
        });
    }

    /**
     * 滑动监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1, 24, 28));
            llTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    private void initView() {
        mIvGoodDetaiImg = (ImageView) findViewById(R.id.iv_good_detai_img);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvGoodDetailDiscount = (TextView) findViewById(R.id.tv_good_detail_discount);
        mTvSalecount = (TextView) findViewById(R.id.tv_salecount);
        mLlOffset = (LinearLayout) findViewById(R.id.ll_offset);
        mLlGoodDetailService = (LinearLayout) findViewById(R.id.ll_good_detail_service);
        mTvTmp1 = (TextView) findViewById(R.id.tv_tmp1);
        mRlGoodDetailJin = (LinearLayout) findViewById(R.id.rl_good_detail_jin);
        mTvAuthor = (TextView) findViewById(R.id.tv_author);
        mTvPinglun = (TextView) findViewById(R.id.tv_pinglun);
        mTvTalentDetailOpen = (TextView) findViewById(R.id.tv_talent_detail_open);
        mTvGoodDetailTuodong = (TextView) findViewById(R.id.tv_good_detail_tuodong);
        mTvGoodDetailDaodi = (TextView) findViewById(R.id.tv_good_detail_daodi);
        mIvGoodDetaiBack = (ImageView) findViewById(R.id.iv_good_detai_back);
        mTvGoodDetailTitleGood = (TextView) findViewById(R.id.tv_good_detail_title_good);
        mIvGoodDetaiShop = (ImageView) findViewById(R.id.iv_good_detai_shop);
        mIvGoodDetaiShare = (ImageView) findViewById(R.id.iv_good_detai_share);
        mLlGoodDetail = (RelativeLayout) findViewById(R.id.ll_good_detail);
        mTvGoodDetailShop = (TextView) findViewById(R.id.tv_good_detail_shop);
        mIvGoodDetaiCollectUnselect = (ImageView) findViewById(R.id.iv_good_detai_collect_unselect);
        mIvGoodDetaiCollectSelect = (ImageView) findViewById(R.id.iv_good_detai_collect_select);
        mLlGoodDetailCollect = (LinearLayout) findViewById(R.id.ll_good_detail_collect);
        mTvGoodDetailShopCart = (TextView) findViewById(R.id.tv_good_detail_shop_cart);
        mTvGoodDetailBuy = (TextView) findViewById(R.id.tv_good_detail_buy);
        mLlGoodDetailBottom = (LinearLayout) findViewById(R.id.ll_good_detail_bottom);
    }
}
