package net.hw.application.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/3/26.
 * 建立表与类之间映射的过程
 */

    /*
        create table t_user
        (....)
        * */
    //@DatabaseTable 注解表名用，表示该类与表t_user的映射，默认会使用该类的类名来当做表名
  @DatabaseTable(tableName = "t_taolun")
public class TaoLunEntity {

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
    private String photoUrl;


    @DatabaseField
    private String author;


    @DatabaseField
    private String title;


    @DatabaseField
    private String content;

    @DatabaseField
    private String pinlun;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPinlun() {
        return pinlun;
    }

    public void setPinlun(String pinlun) {
        this.pinlun = pinlun;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }
}
