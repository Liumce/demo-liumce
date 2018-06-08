package net.hw.application.adpters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.hw.application.R;
import net.hw.application.entity.TaoLunEntity;

import java.util.List;

/**
 * Created by Administrator on 2018-5-24.
 */

public class TaolunAdapter extends BaseAdapter {

    private Context context;
    private List<TaoLunEntity> list;

    public TaolunAdapter(Context context, List<TaoLunEntity> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.taolun_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TaoLunEntity taoLun = list.get(position);
        viewHolder.mIvPhoto.setImageDrawable
                (ContextCompat.getDrawable(context,getResource(taoLun.getPhotoUrl())));
        viewHolder.mTvName.setText(taoLun.getAuthor());
        viewHolder.mTvTitle.setText(taoLun.getTitle());
        viewHolder.mTvContent.setText(taoLun.getContent());
        convertView.setTag(viewHolder);

        return convertView;
    }

    /**
     * 获取图片名称获取图片的资源id的方法
     *
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
        public ImageView mIvPhoto;
        public TextView mTvName;
        public TextView mTvTitle;
        public TextView mTvContent;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);
            this.mTvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
        }

    }
}
