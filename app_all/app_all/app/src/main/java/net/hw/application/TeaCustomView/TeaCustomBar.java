package net.hw.application.TeaCustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17828121783 on 2018/5/16.
 */

public class TeaCustomBar extends View{
    public TeaCustomBar(Context context) {
        super(context);
    }

    public TeaCustomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if(widthMode==MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            width = Math.min(width,widthSize);
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            height = Math.min(height,heightSize);
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init2();
        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        for(int i=0;i<rectFList.size();++i){
            if(moveBarLocateId!=i){
                barList.get(i).setAlpha(0);
            }else {
                barList.get(i).setAlpha(255);
            }
            RectF rectF = rectFList.get(i);
            paint.setColor(Color.parseColor("#DBDBDB"));
            paint.setTextSize(textSize);
            paint.setFakeBoldText(true);
            canvas.drawText(stringList.get(i),rectF.left,rectF.centerY()+textSize/3,paint);
            paint.setColor(barList.get(i).getColor());
            paint.setStrokeWidth(barList.get(i).getStrokeWidth());
            paint.setAlpha(barList.get(i).getAlpha());
            canvas.drawLine(barList.get(i).getStartX(),barList.get(i).getStartY(),barList.get(i).getStopX(),barList.get(i).getStopY(),paint);

        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        moveDistance = 0;
        int upId;
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (listner!=null){
                    upId = getOnclickId(x);
                    if(upId==-1){
                        upId = moveBarLocateId;
                    }
                    setMoveBarLocateId(upId);
                    listner.onClick(upId);
                }
                break;
        }

        return true;
    }
    //创建画笔变量
    private Paint paint;
    //创建数据源表
    private List<String> stringList;
    //创建装数据源的长方形类
    private List<RectF> rectFList;
    //保存字体大小
    private float textSize;
    //用来保存bar所在的当前位置的id
    private int moveBarLocateId;
    //用来保存bar即将移动到的位置的id
   // private int toMoveBarLocateId;
    //private int rectWidth;
    //间距
    private float distance;
    //需要移动的距离
    private float moveDistance;
    //创建副本保存之前各个控件的位置
    private List<TeaBar> barList;

    //保存上一个移动的距离
    private int toX;

    private int x;

    private void init(){

      //  toMoveBarLocateId = 0;
        moveDistance = 0;
        textSize = 42;
        moveBarLocateId = 0;
        distance = textSize;
        paint = new Paint();
        stringList = new ArrayList<>();
        /*stringList = new ArrayList<>();
        stringList.add("选项1");
        stringList.add("选项2");
        stringList.add("选项3");
        stringList.add("选项4");
        stringList.add("选项5");
        stringList.add("选项6");
        stringList.add("选项7");
        stringList.add("选项8");
        stringList.add("选项9");*/
        rectFList = new ArrayList<>();
        barList = new ArrayList<>();
    }
    private void init2(){
        //在开始的初始化
        if(rectFList.size()==0){
            //得到每个长方形区域
            RectF rectF = new RectF();
            rectF.left = distance;
            rectF.top = 0;
            rectF.right = rectF.left+textSize*stringList.get(0).length();
            rectF.bottom = getHeight();
            rectFList.add(rectF);
            TeaBar teaBar = new TeaBar();
            teaBar.setStrokeWidth(20);
            teaBar.setColor(Color.RED);
            teaBar.setStartX((int) rectF.left);
            teaBar.setStartY(getHeight());
            teaBar.setStopX((int) rectF.right);
            teaBar.setStopY(getHeight());
            teaBar.setAlpha(255);
            barList.add(teaBar);
            for(int i=1;i<stringList.size();++i){
                rectF = new RectF();
                rectF.left = rectFList.get(i-1).right+distance;
                rectF.top = 0;
                rectF.right = rectF.left+textSize*stringList.get(i).length();
                rectF.bottom = getHeight();
                rectFList.add(rectF);
                teaBar = new TeaBar();
                teaBar.setStrokeWidth(20);
                teaBar.setColor(Color.RED);
                teaBar.setStartX((int) rectF.left);
                teaBar.setStartY(getHeight());
                teaBar.setStopX((int) rectF.right);
                teaBar.setStopY(getHeight());
                teaBar.setAlpha(255);
                barList.add(teaBar);
            }

        }
    }


    public boolean setDataResourse(List<String> stringList){
        this.stringList = stringList;
        rectFList = new ArrayList<>();
        this.invalidate();
        return true;
    }
/*    //获取当前移动条的id
    public int getMoveBarId(){
        return this.moveBarLocateId;
    }*/
    /*//设置需要移动到id
    public void setMoveBarLocate(int id){
        if(id>stringList.size()){
            id = stringList.size()-1;
        }
        toMoveBarLocateId = id;
       // moveAnimation();
    }*/


    private void setMoveDistance(int distance){
        for(int i=0;i<rectFList.size();++i){
            rectFList.get(i).left += distance;
            rectFList.get(i).right += distance;

        }
        this.invalidate();
    }
    public void setMoveBarLocateId(int moveBarLocateId) {
        this.moveBarLocateId = moveBarLocateId;
        this.invalidate();
    }

   /* public void setN(int n){
        if(rectFList.size()==0){
            return;
        }
        if(lastN!=0){
            n = n-lastN;
        }
        for(int i=0;i<rectFList.size();++i){
            RectF rectF = rectFList.get(i);
            rectF.left += n;
            rectF.right += n;
            rectFList.get(i).set(rectF);
        }
        this.invalidate();
    }*/
    public int getOnclickId(float x){
        int id = -1;
        for(int i=0;i<rectFList.size();++i){
            if(x>rectFList.get(i).left && x < rectFList.get(i).right){
                id = i;
                break;
            }
        }
        return id;
    }

    private OnClickEventLister listner;

    public void setOnClickEventLister(OnClickEventLister l) {
        this.listner = l;
    }

    public interface OnClickEventLister{
        void onClick(int id);
    }
}
