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
  @DatabaseTable(tableName = "t_user")
public class UserEntity {

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
    private String password;

    @DatabaseField
    private int age;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
