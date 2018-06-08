package net.hw.application.dao;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;


import net.hw.application.db.DatabaseHelper;
import net.hw.application.entity.GouWuEntity;
import net.hw.application.entity.ProductInfoEntity;
import net.hw.application.entity.TaoLunEntity;
import net.hw.application.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 * 操作数据库的接口类
 *
 */

public class OperateDbDao {
    /**
     * 创建操作表的接口对象
     * 里面泛型说明：
     * 泛型1：映射类的类名
     * 泛型2：通常传整型
     */
    private RuntimeExceptionDao<UserEntity, Integer> userDao         = null;
    private  RuntimeExceptionDao<ProductInfoEntity, Integer>          productInfoDao       = null;
    private  RuntimeExceptionDao<TaoLunEntity, Integer>          taolunDao       = null;
    private RuntimeExceptionDao<GouWuEntity, Integer> gouWuDao         = null;
    private DatabaseHelper databaseHelper;

    public OperateDbDao(Context context){
        //创建DatabaseHelper对象
        /**
         * 调用OpenHelperManager的静态方法getHelper方法
         * 获取表和数据库帮助的对象
         * 参数说明
         * 参数1：上下文环境
         * 参数2：帮助类的类名
         */
        databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            //通过数据库的帮助类的对象，获取操作表的接口对象
            //参数说明：参数1 传递的是映射表的类
            userDao = databaseHelper.getUserInfoDao();
            productInfoDao = databaseHelper.getProductInfoDao();
        taolunDao = databaseHelper.getTaolunDao();
        gouWuDao = databaseHelper.getGouWuDao();
    }

    private  static OperateDbDao instance;
    public static OperateDbDao getInstance(Context context) {
        if (instance == null) {
            instance = new OperateDbDao(context);
        }
        return instance;
    }
    /**
     * 通过姓名模糊删除某个用户
     * @param name  姓名
     * @return  返回上次的记录
     */
    public int deleteUserByUser(String name) throws SQLException {
           DeleteBuilder<UserEntity,Integer> deleteBuilder =  userDao.deleteBuilder();
        //delete from t_user where uname like '%zhangsan%'
        deleteBuilder.where().like("uname",name);
        /**
         * where():对应sql语句的where
         * or():对应sql语句的or
         * eq:对应sql语句的=
         * and():对应sql语句的and
         * between()：对应sql语句的between
         * like():对应sql语句的like
         * le:对应sql语句的<=
         * ge:对应sql语句的>=
         * lt:对应sql语句的<
         * gt:对应sql语句的>
         *
         */
        //delete方法里面传的参数是deletePrepared对象
        int count = userDao.delete(deleteBuilder.prepare());
        return count;
    }


    /**
     * 通过姓名模糊删除某个用户
     * @param Id  姓名
     * @return  返回上次的记录
     */
    public int deleteGowuById(String Id) throws SQLException {
        DeleteBuilder<GouWuEntity,Integer> deleteBuilder =  gouWuDao.deleteBuilder();
        //delete from t_user where uname like '%zhangsan%'
        deleteBuilder.where().like("uid",Id);
        /**
         * where():对应sql语句的where
         * or():对应sql语句的or
         * eq:对应sql语句的=
         * and():对应sql语句的and
         * between()：对应sql语句的between
         * like():对应sql语句的like
         * le:对应sql语句的<=
         * ge:对应sql语句的>=
         * lt:对应sql语句的<
         * gt:对应sql语句的>
         *
         */
        //delete方法里面传的参数是deletePrepared对象
        int count = gouWuDao.delete(deleteBuilder.prepare());
        return count;
    }

    /**
     * 默认删除方法,通过id来删除
     * @param userEntity  要删除的对象
     * @return
     * @throws SQLException
     */
    public int deleteUser( UserEntity userEntity) throws SQLException {
        //delete:通过id来删除
       int count =  userDao.delete(userEntity);
        return count;
    }

    /**
     * 默认更新方法,通过id来更新
     * @param userEntity  要更新的对象
     * @return 返回被更新的记录条数
     * @throws SQLException
     */
    public int updateUser(UserEntity userEntity) throws SQLException {
        //update t_user set uname=XXX,set age=XXX where id=XXX
        int count = userDao.update(userEntity);
        return count;
    }

    public int updateUser2(UserEntity userEntity){
        UpdateBuilder<UserEntity,Integer> updateBuilder = userDao.updateBuilder();
        try {
            updateBuilder.where().eq("uid",userEntity.getUid());
            int count = userDao.update(updateBuilder.prepare());
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }




    /**
     * 往表里面插入属于
     * @param userList  要插入的数据
     * @return  插入数据以后导致表多少条数据收到影响
     */
    public int  insertUser(List<UserEntity> userList){

            //调用接口对象的create方法，相当于insert into 表名（字段名...） values（值1....）
           int count =  userDao.create(userList);
            return count;

    }
    /**
     * 往表里面插入属于
     * @param userList  要插入的数据
     * @return  插入数据以后导致表多少条数据收到影响
     */
    public int  insertGouWu(List<GouWuEntity> userList){

        //调用接口对象的create方法，相当于insert into 表名（字段名...） values（值1....）
        int count =  gouWuDao.create(userList);
        return count;

    }

    /**
     * 查询表中所有数据
     * @return  返回的就是list集合
     */
    public List<UserEntity> queryALLUser(){

            //调用queryForAll查询所有数据，相当于select * from t_user
            /*
            * 1\ cursor 通过select * from t_user
            * 2、for(int i = 0;i<cursor.size();i++){
            * UserEntity userEntity  = new UserEntity();
            * userEntity.setUname(cursor.getString(cursor.getNameIndex("uname")))
            * ......
            * }
            * */
            List<UserEntity> userEntityList = userDao.queryForAll();
            return userEntityList;

    }
    public  List<TaoLunEntity> getAllTaolun(){



        List<TaoLunEntity> userEntityList = taolunDao.queryForAll();
        return userEntityList;

    }
    /**
     * 查询表中所有数据
     * @return  返回的就是list集合
     */
    public  List<ProductInfoEntity> getAllProduct(){



        List<ProductInfoEntity> userEntityList = productInfoDao.queryForAll();
        return userEntityList;

    }

    /**
     * 查询表中所有数据
     * @return  返回的就是list集合
     */
    public  List<GouWuEntity> getAllGouwu(){



        List<GouWuEntity> userEntityList = gouWuDao.queryForAll();
        return userEntityList;

    }



    public List<ProductInfoEntity> queryByLeiBie(String lei){
        //获取查询的构建器对象
        QueryBuilder<ProductInfoEntity,Integer> queryBuilder =  productInfoDao.queryBuilder();

        try {
            queryBuilder.orderBy("leibie",true).where().eq("leibie",lei);
            //调用queryBuilder.prepare()方法获取QueryPrepaer对象，将条件放到查询中
            List<ProductInfoEntity> userEntityList = productInfoDao.query(queryBuilder.prepare());
            return userEntityList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 条件查询语句
     * @param name1  姓名1
     * @param name2 姓名2
     * @return
     */
    //select * from t_user where uname='张三' or uname='李四' and....
    public List<UserEntity> queryByName(String name1 ,String name2){
        //获取查询的构建器对象
       QueryBuilder<UserEntity,Integer> queryBuilder =  userDao.queryBuilder();
        /**
         * queryBuilder所跟方法说明
         * where():对应sql语句的where
         * or():对应sql语句的or
         * eq:对应sql语句的=
         * and():对应sql语句的and
         * between()：对应sql语句的between
         * like():对应sql语句的like
         * le:对应sql语句的<=
         * ge:对应sql语句的>=
         * lt:对应sql语句的<
         * gt:对应sql语句的>
         *
         */
        try {
            queryBuilder.orderBy("uname",true).where().eq("uname",name1).or().eq("uname",name2);
            //调用queryBuilder.prepare()方法获取QueryPrepaer对象，将条件放到查询中
            List<UserEntity> userEntityList = userDao.query(queryBuilder.prepare());
            return userEntityList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
