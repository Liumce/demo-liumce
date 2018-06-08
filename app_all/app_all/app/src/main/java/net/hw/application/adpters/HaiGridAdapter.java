package net.hw.application.adpters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.activity.DetailsActivity;
import net.hw.application.entity.ProductInfoEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-5-24.
 */

public class HaiGridAdapter extends BaseAdapter {

    private Context context;
    private List<ProductInfoEntity> list;

    public HaiGridAdapter(Context context, List<ProductInfoEntity> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final ProductInfoEntity product = list.get(position);
        Log.e("sky.liu",product.getIconUrl()+"--"+list.size());
        viewHolder.mIvPhoto.setImageDrawable
                (ContextCompat.getDrawable(context,getResource(product.getIconUrl())));
/*        viewHolder.mIvPhoto.setImageDrawable
                (ContextCompat.getDrawable(context,R.mipmap.t4001));   */

        viewHolder.mTvCount.setText("销量为："+product.getSalesCount()+"件");
        viewHolder.mTvName.setText(product.getUname());
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("product",product);
                context.startActivity(intent);

            }
        });

//        viewHolder.mTvPrice.setText("劲爆价格：￥"+product.getPrice());
        convertView.setTag(viewHolder);

        return convertView;
    }
    /**
     * 获取图片名称获取图片的资源id的方法
     * @param imageName
     * @return
     */
    public int getResource(String imageName) {

       int id = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return id;
/*        Resources res=context.getResources();
        int picid = res.getIdentifier(imageName,"mipmap",context.getPackageName());
        return picid;*/
    }

    public static class ViewHolder {
        public View rootView;
        public CardView mCardView;
        public ImageView mIvPhoto;
        public TextView mTvName;
        public TextView mTvCount;
        public TextView mTvPrice;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);
            this.mTvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.mTvCount = (TextView) rootView.findViewById(R.id.tv_count);
            this.mTvPrice = (TextView) rootView.findViewById(R.id.tv_price);
            this.mCardView = rootView.findViewById(R.id.card_view);
        }

    }
}
