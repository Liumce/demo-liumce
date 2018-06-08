package net.hw.application.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.hw.application.entity.GouWuEntity;
import net.hw.application.entity.ProductInfoEntity;
import net.hw.application.entity.TaoLunEntity;
import net.hw.application.entity.UserEntity;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/3/26.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "lin.db";
    public static final int DATABASE_VERSION =8;


    /*
   * 实现一个单个参数的构造方法，参数类型是上下文环境
   * */
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }


    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    /*
    创建表的时候自行
    参数说明：
    参数1：数据库对象
    参数2：ormlite框架的链接对象
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //sqLiteDatabase.execSQL("create table ....");换成下面的方式创建
        try {
            TableUtils.createTable(connectionSource, UserEntity.class);
            TableUtils.createTable(connectionSource, ProductInfoEntity.class);
            TableUtils.createTable(connectionSource, TaoLunEntity.class);
            TableUtils.createTable(connectionSource, GouWuEntity.class);
            initData();
            initTaoLun();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    *数据库升级的时候调用此方法
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
       // sqLiteDatabase.execSQL("drop table 表名");
        if(newVersion>oldVersion){//表示数据库版本升级

            try {
                /*
                * 参数说明：
                * 参数1：链接对象
                * 参数2：映射的类
                * 参数3：true表示如果报错则不管
                * */
                TableUtils.dropTable(connectionSource,UserEntity.class,true);
                TableUtils.dropTable(connectionSource,ProductInfoEntity.class,true);
                TableUtils.dropTable(connectionSource,TaoLunEntity.class,true);
                onCreate(sqLiteDatabase,connectionSource);//重新创建表
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private RuntimeExceptionDao<UserEntity, Integer> userDao         = null;
    private RuntimeExceptionDao<ProductInfoEntity, Integer> productInfoDao         = null;
    private RuntimeExceptionDao<TaoLunEntity, Integer> taoLunDao         = null;
    private RuntimeExceptionDao<GouWuEntity, Integer> gouWuDao         = null;
    public RuntimeExceptionDao<GouWuEntity, Integer> getGouWuDao()
    {
        if (gouWuDao == null)
        {
            gouWuDao = getRuntimeExceptionDao(GouWuEntity.class);
        }
        return gouWuDao;
    }
    public RuntimeExceptionDao<ProductInfoEntity, Integer> getProductInfoDao()
    {
        if (productInfoDao == null)
        {
            productInfoDao = getRuntimeExceptionDao(ProductInfoEntity.class);
        }
        return productInfoDao;
    }
    public RuntimeExceptionDao<UserEntity, Integer> getUserInfoDao()
    {
        if (userDao == null)
        {
            userDao = getRuntimeExceptionDao(UserEntity.class);
        }
        return userDao;
    }

    public RuntimeExceptionDao<TaoLunEntity, Integer> getTaolunDao()
    {
        if (taoLunDao == null)
        {
            taoLunDao = getRuntimeExceptionDao(TaoLunEntity.class);
        }
        return taoLunDao;
    }
    public void initData(){
        userDao = getUserInfoDao();
        productInfoDao = getProductInfoDao();

        //添加产品信息
        ProductInfoEntity product = new ProductInfoEntity();
        product.setUid("t40001");
        product.setLeibie("1");
        product.setUname("儿童手工软陶泥免烧陶艺粘土");
        product.setIconUrl("t40001");
        product.setPrice((float) 16.9);
        product.setSalesCount(104);
        product.setJianjie("教学用泥");
        product.setPinglun("非常细腻！\n" +
                "很好用！\n" +
                "服务周到！\n" +
                "发货快！\n" +
                "非常及时！\n" +
                "赞");
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("t40002");
        product.setLeibie("1");
        product.setUname("diy创意手工制作软陶泥粘土");
        product.setIconUrl("t40002");
        product.setPrice((float) 33);
        product.setSalesCount(12);
        product.setJianjie("静态塑胶玩具");
        product.setPinglun("谢谢老板！\n" +
                "送的东西,宝贝很喜欢！\n" +
                "服务周到！\n" );
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("t40003");
        product.setLeibie("1");
        product.setUname("迪士尼陶艺泥粘土手工DIY制作");
        product.setIconUrl("t40003");
        product.setPrice((float) 30);
        product.setSalesCount(135);
        product.setJianjie("泥塑儿童免烧软陶泥");
        product.setPinglun("还没玩，不过摸着软软的！\n" +
                "很好用！\n" +
                "塑性很强！\n" +
                "物流很快！\n" +
                "卖家服务不错！");
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("t40004");
        product.setLeibie("1");
        product.setUname("环保软陶彩泥陶泥DIY");
        product.setIconUrl("t40004");
        product.setPrice((float) 33);
        product.setSalesCount(125);
        product.setJianjie("陶艺立体雕塑泥");
        product.setPinglun("油性挺大的！\n" +
                "看着不错！" );
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("t40005");
        product.setLeibie("1");
        product.setUname("儿童手工软陶泥粘土\n");
        product.setIconUrl("t40005");
        product.setPrice((float) 95);
        product.setSalesCount(83);
        product.setJianjie("手工制作教学用陶土");
        product.setPinglun("很好用\n" +
                "做了俩个多肉花盆\n" +
                "和一些小摆件\n" +
                "很可爱" +
                "看着不错！" );
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("j0001");
        product.setLeibie("2");
        product.setUname("金丝彩沙画景泰蓝工艺");
        product.setIconUrl("j0001");
        product.setPrice((float) 35);
        product.setSalesCount(89);
        product.setPinglun("卖家人很好，东西很美观，描图很清晰\n"
                 );
        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("j0002");
        product.setLeibie("2");
        product.setUname("彩沙陷丝珐琅画材料");
        product.setIconUrl("j0002");
        product.setPrice((float)1.2);
        product.setSalesCount(1471);
        product.setPinglun("材料很好" +
                "很满意" +
                "沙子很细");

        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("j0003");
        product.setLeibie("2");
        product.setUname("景泰蓝原材料沙画");
        product.setIconUrl("j0003");
        product.setPrice((float)29.2);
        product.setSalesCount(24);
        product.setPinglun("质量不错，满意");

        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("j0004");
        product.setLeibie("2");
        product.setUname("景泰蓝工艺手工粘丝材料");
        product.setIconUrl("j0004");
        product.setPrice((float)3);
        product.setSalesCount(452);
        product.setPinglun("老板发货很快\n" +
                "很满意\n" +
                "很喜欢");

        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("y0001");
        product.setLeibie("2");
        product.setUname("白色花边油纸伞");
        product.setIconUrl("y0001");
        product.setPrice((float)150);
        product.setSalesCount(3);
        product.setPinglun("老板发货很快\n" +
                "很满意\n" +
                "很喜欢");

        productInfoDao.create(product);

        product = new ProductInfoEntity();
        product.setUid("y0002");
        product.setLeibie("2");
        product.setUname("荷塘月色油纸伞");
        product.setIconUrl("y0002");
        product.setPrice((float)300);
        product.setSalesCount(7);
        product.setPinglun("老板发货很快\n" +
                "很满意\n" +
                "很喜欢");

        productInfoDao.create(product);
    }


    public void  initTaoLun(){
    taoLunDao = getTaolunDao();
        TaoLunEntity taoLunEntity =  new TaoLunEntity();
        taoLunEntity.setUid("t0001");
        taoLunEntity.setAuthor("张贤");
        taoLunEntity.setTitle("油纸伞怎么做的？");
        taoLunEntity.setContent("分水油纸伞是四川泸州的汉族民间工艺品。位于泸州市江阳区分水岭镇，距离泸州市城区35公里。位于长江南岸，四川盆地向云贵高原过度带，云贵川渝四省市结合部，是茶马古道，南丝绸之路起点之一；位于合江佛宝，贵州赤水，重庆四面山旅游金三角门户。分水油纸伞厂的油纸伞制作历史悠久，现在难以考证，但至少在明清两代，此地已广泛制作和使用油纸伞了");
        taoLunEntity.setPhotoUrl("t0001");
        taoLunEntity.setPinlun("以前没怎么关注\n百度啊，兄弟");
        taoLunDao.create(taoLunEntity);

        taoLunEntity =  new TaoLunEntity();
        taoLunEntity.setUid("t0002");
        taoLunEntity.setAuthor("李芬");
        taoLunEntity.setTitle("油纸伞的画是印刷的还是其他方式弄得？");
        taoLunEntity.setContent("油纸伞的画是印刷的还是其他方式弄得？");
        taoLunEntity.setPhotoUrl("t0002");
        taoLunEntity.setPinlun("以前没怎么关注\n百度啊，兄弟");
        taoLunDao.create(taoLunEntity);

        taoLunEntity =  new TaoLunEntity();
        taoLunEntity.setUid("t0003");
        taoLunEntity.setAuthor("李林");
        taoLunEntity.setTitle("彩砂分为两种，怎么区别的？");
        taoLunEntity.setContent("彩砂分为两种：一种为天然彩砂，另一种则是染色彩砂，他们怎么区别的？");
        taoLunEntity.setPhotoUrl("t0003");
        taoLunEntity.setPinlun("以前没怎么关注\n百度啊，兄弟");
        taoLunDao.create(taoLunEntity);


    }

    @Override
    public void close()
    {

        userDao = null;


        // TODO Auto-generated method stub
        super.close();
    }
}
