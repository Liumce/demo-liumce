package net.hw.application.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/26.
 * 建立表与类之间映射的过程
 */

    /*
        create table t_user
        (....)
        * */
    //@DatabaseTable 注解表名用，表示该类与表t_user的映射，默认会使用该类的类名来当做表名
  @DatabaseTable(tableName = "t_gouwu")
public class GouWuEntity implements Serializable{

    /**
     * generatedId = true设置自增长id
     */
    @DatabaseField(generatedId = true)
    private int _id;


    /*
    * DatabaseField注解字段用，表示表的字段和类的属性之间建立映射关系，
    * columnName是配置表字段名用，如果不设置，则默认表的字段同类的属性名一样
    * id = true:表示该字段为主键
    * */
    @DatabaseField(columnName = "uid")
    private String uid;

    @DatabaseField
    private String uname;

    @DatabaseField
    private String iconUrl;

    @DatabaseField
    private float price;

    @DatabaseField
    private String author;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }

    @DatabaseField

    private int salesCount;

    @DatabaseField
    private String jianjie;

    @DatabaseField
    private String pinglun;

    @DatabaseField
    private String leibie;

    @DatabaseField
    private String temp1;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
