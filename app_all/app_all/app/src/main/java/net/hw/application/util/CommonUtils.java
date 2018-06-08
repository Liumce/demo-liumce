package net.hw.application.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @ClassName NNCCUtils.java
 * @Description 工具类
 * 
 */
@SuppressLint("SdCardPath")
public class CommonUtils
{
    public static final String TAG = "NNCCUtils";

    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    private final static String ALBUM_PATH
            = Environment.getExternalStorageDirectory() + "/service/";
    public static String saveFile(Bitmap bm, String fileName) {
        File dirFile = new File(ALBUM_PATH);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        try {
            File myCaptureFile = new File(ALBUM_PATH + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

        return ALBUM_PATH + fileName;
    }
    public static byte[] decodeStr(String str){

        if(str==null){
            return new byte[1];
        }
        String[] temp  = str.split("&");
        int size = temp.length;
        byte[] result = new byte[size];
        for(int i = 0;i<temp.length;i++){
            result[i] = Byte.parseByte(temp[i]);
        }
        return  result;
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        /*UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;*/

        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(final byte[] bytes) {
        if(bytes==null||bytes.length==0){
            return "";
        }
        StringBuffer strBuffer = new StringBuffer();
        for(int i = 0;i<bytes.length;i++){
            strBuffer.append(bytes[i]+"&");
        }
        int size = strBuffer.length();
        return strBuffer.substring(0,size-1);
        //return new String(Base64.encodeBase64(bytes));
    }
    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            LogUtil.d(TAG, "获取应用程序名称:" + context.getResources().getString(labelRes));
            return context.getResources().getString(labelRes);
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     * 
     * @param context
     * @return 当前应用的版本名称versionName
     */
    public static String getVersionName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            LogUtil.d(TAG, "当前应用的版本名称:" + packageInfo.versionName);
            return packageInfo.versionName;

        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     * 
     * @param context
     * @return 当前应用的版本名称versionCode
     */
    public static int getVersionCode(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            LogUtil.d(TAG, "当前应用的版本名称versionCode:" + packageInfo.versionCode);
            return packageInfo.versionCode;

        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @return
     * @description 检测sdcard是否可用
     */
    public static boolean isSDCardAvailable()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        return false;
    }

    /**
     * 获取存储卡路径
     * 
     * @return
     */
    public static File getMemoryCardPath()
    {
        File destDir = new File("mnt/sdcard/" + "NNCC");
        if (!destDir.exists())
        {
            destDir.mkdirs();
        }
        return destDir;
    }

    /**
     * 获取缓存cacheFile路径
     * 
     * @return
     */
    public static File getCachePath()
    {
        File cacheFile = new File(getMemoryCardPath().getAbsolutePath() + "/cache");
        if (!cacheFile.exists())
        {
            cacheFile.mkdirs();
        }
        return cacheFile;
    }

    /**
     * 清缓存 （包括加载网页的缓存）
     */
    public static void deleteFileCache(Context context)
    {
        File cacheFile = new File(getMemoryCardPath() + "/cache");
        deleteFile(cacheFile);
        File file = context.getCacheDir();
        deleteFile(file);
        File webCache = new File("/data/data/com.channelsoft.nncc/app_webview");
        deleteFile(webCache);
        File databaseCache = new File("/data/data/com.channelsoft.nncc/app_database");
        deleteFile(databaseCache);
        File cache = new File("/data/data/com.channelsoft.nncc/cache");
        deleteFile(cache);
        File filescache = new File("/data/data/com.channelsoft.nncc/files");
        deleteFile(filescache);
    }

    /**
     * 递归删除 文件/文件夹
     * 
     * @param file
     */
    public static void deleteFile(File file)
    {

        LogUtil.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists())
        {
            if (file.isFile())
            {
                file.delete();
            }
            else if (file.isDirectory())
            {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++)
                {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
        else
        {
            LogUtil.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }

    public static long getFileSize(File f)
    {
        long size = 0;
        File[] flist = f.listFiles();
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getFileSize(flist[i]);
            }
            else
            {
                size = size + flist[i].length();
            }
        }
        return size;
    }
    /**
     * 判断str是否有值
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str==null||str.length()==0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 得到缓存文件大小 （包括加载网页的缓存）
     * 
     * @param context
     */
    public static String getCacheFile(Context context)
    {
        File cacheFile = new File(getMemoryCardPath() + "/cache");
        File webviewCache = new File("/data/data/com.channelsoft.nncc/app_webview");

        File databaseCache = new File("/data/data/com.channelsoft.nncc/app_database");
        File cache = new File("/data/data/com.channelsoft.nncc/cache");
        long size = 0L;
        String sizeString;
        try
        {
            if (cacheFile != null && cacheFile.exists())
            {
                size = getFileSize(cacheFile);
                LogUtil.d(TAG, "cacheFile size===" + size);
            }
            if (webviewCache != null && webviewCache.exists())
            {
                size += getFileSize(webviewCache);
                LogUtil.d(TAG, " webviewCache size===" + getFileSize(webviewCache));
            }
            if (databaseCache != null && databaseCache.exists())
            {
                size += getFileSize(databaseCache);
                LogUtil.d(TAG, " databaseCache size===" + getFileSize(databaseCache));
            }
            if (cache != null && cache.exists())
            {
                size += getFileSize(cache);
                LogUtil.d(TAG, " cache size===" + getFileSize(cache));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("0.0");
        if (size == 0L)
        {
            sizeString = "";
        }
        else if (size < 1024L)
        {
            sizeString = df.format((double) size) + "B";
        }
        else if (size < 1048576L)
        {
            sizeString = df.format((double) size / 1024) + "KB";
        }
        else if (size < 1073741824L)
        {
            sizeString = df.format((double) size / 1048576) + "MB";
        }
        else
        {
            sizeString = df.format((double) size / 1073741824) + "GB";
        }
        LogUtil.d(TAG, "sizeString===" + sizeString);
        return sizeString;
    }

    /**
     * 保存头像到本地方法
     * 
     * @param picName
     * @param mBitmap
     */
    public static void saveBitmap(String picName, Bitmap mBitmap)
    {
        LogUtil.e(TAG, "保存图片");
        File f = new File(getMemoryCardPath(), picName);
        if (f.exists())
        {
            f.delete();
        }
        try
        {
            FileOutputStream out = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            LogUtil.i(TAG, "已经保存");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 保存头像到本地方法
     * 
     * @param picName
     * @param mBitmap
     */
    public static void saveBitmap(File cachePath, String picName, Bitmap mBitmap)
    {
        LogUtil.e(TAG, "上传前保存缓存图片");
        File f = new File(cachePath, picName);
        if (f.exists())
        {
            f.delete();
        }
        try
        {
            FileOutputStream out = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
            out.flush();
            out.close();
            LogUtil.i(TAG, "上传前保存缓存图片   保存成功");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param context
     * @return 检查网络是否可用
     */
    public static boolean netIsConnect(Context context)
    {
        try
        {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null)
            {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected())
                {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.v(TAG, e.toString());
        }
        return false;
    }

    /**
     * 检查网络
     * 
     * @param context
     * @return 1wifi 2移动 0无网
     */
    public static int networkInfo(Context context)
    {
        try
        {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiInfo.isConnected())
            {
                return 1;
            }
            else if (mobileInfo.isConnected())
            {
                return 2;
            }
        }
        catch (Exception e)
        {
            LogUtil.v(TAG, e.toString());
        }
        return 0;
    }
    public static Date strToDate(String time, String type){
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        //Date date = sdf.parse("2008-08-08 12:10:12");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtil.e("commonutils-strToDate日期转换错误");
        }

        return date ;
    }

    public static Date strToDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = sdf.parse("2008-08-08 12:10:12");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtil.e("commonutils-strToDate日期转换错误");
        }

        return date ;
    }
    /**
     * 上传图片
     * 
     * @param context
     * @param uploadFile
     * @return 网络图片路径
     */
    public static String uploadFile(Context context, File uploadFile, String loginName)
    {
        String imgPath = "";// 返回值
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        DataOutputStream ds = null;
        try
        {
            URL url = new URL("" + "?loginName=" + loginName);
            LogUtil.d("--------------" + url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            /* 设置传送的method=POST */
            con.setRequestMethod("POST");
            /* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "GBK");
            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            /* 设置DataOutputStream */
            ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " + "name=\"img\";filename=\"" + uploadFile.getName() + "\""
                    + end);
            ds.writeBytes(end);
            /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(uploadFile);
            /* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1)
            {
                /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            fStream.close();
            ds.flush();
            /* 取得Response内容 */
            int code = con.getResponseCode();
            String value = "";
            if (code == 200)
            {
                InputStream is = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
                int ch;
                StringBuffer b = new StringBuffer();
                while ((ch = reader.read()) != -1)
                {
                    b.append((char) ch);
                }
                value = b.toString();
            }
            try
            {
                LogUtil.d("------value--------" + value);
                JSONObject jsonObject = new JSONObject(value);
                if ("00".equals(jsonObject.getString("returnCode")))
                {
                    imgPath = jsonObject.getString("imgPath");
                }
                else if ("-1".equals(jsonObject.getString("returnCode")))
                {
                    LogUtil.d("" + jsonObject.getString("returnMsg"));
                }
                else
                {
                    LogUtil.d("上传图片异常");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            ds.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return imgPath;
    }

    /**
     * 下载图片通过url 下载后就直接保存在本地 头像
     * 
     * @param imgUrl
     * @return
     */
    public static boolean loadImageFromUrl(String imgUrl)
    {
        URL url;
        InputStream inputStream = null;
        Drawable drawable = null;
        boolean loadImageSuccess = false;
        try
        {
            url = new URL("" + "/" + imgUrl);
            LogUtil.d("TAG", "HttpUtil loadImageFromUrl start,url:" + url);
            inputStream = (InputStream) url.getContent();
            drawable = Drawable.createFromStream(inputStream, "src");
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap mBitmap = bd.getBitmap();
            saveBitmap("headpic", mBitmap);
            loadImageSuccess = true;
        }
        catch (MalformedURLException e1)
        {
            LogUtil.d("TAG", "HttpUtil loadImageFromUrl MalformedURLException :" + e1);
            drawable = null;
            loadImageSuccess = false;
        }
        catch (IOException e)
        {
            LogUtil.d("TAG", "HttpUtil loadImageFromUrl IOException =" + e);
            drawable = null;
            loadImageSuccess = false;
        }
        catch (Exception e)
        {
            LogUtil.d("TAG", "HttpUtil loadImageFromUrl Exception =" + e);
            drawable = null;
            loadImageSuccess = false;
        }
        return loadImageSuccess;
    }

    /**
     * 推广图片
     * 
     * @param imgUrl
     * @param imgName
     * @return
     */
    public static boolean loadImageFromUrl(String imgUrl, String imgName)
    {
        URL url;
        InputStream inputStream = null;
        Drawable drawable = null;
        boolean loadImageSuccess = false;
        try
        {
            url = new URL("" + "/"+imgUrl);
            LogUtil.d("TAG", "HttpUtil loadImageFromUrl start,url:" + url);
            inputStream = (InputStream) url.getContent();
            drawable = Drawable.createFromStream(inputStream, "src");
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap mBitmap = bd.getBitmap();
            saveBitmap(imgName, mBitmap);
            loadImageSuccess = true;
        }
        catch (MalformedURLException e1)
        {
            LogUtil.d(TAG, "HttpUtil loadImageFromUrl MalformedURLException :" + e1);
            drawable = null;
            loadImageSuccess = false;
        }
        catch (IOException e)
        {
            LogUtil.d(TAG, "HttpUtil loadImageFromUrl IOException =" + e);
            drawable = null;
            loadImageSuccess = false;
        }
        catch (Exception e)
        {
            LogUtil.d(TAG, "HttpUtil loadImageFromUrl Exception =" + e);
            drawable = null;
            loadImageSuccess = false;
        }
        return loadImageSuccess;
    }

    /**
     * 判断应用是否在前台运行
     * 
     * @param context
     * @return
     */
    public static boolean isTopOfAppStack(Context context)
    {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses)
        {
            if (appProcess.processName.equals(context.getPackageName()))
            {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND)
                {
                    LogUtil.i("后台", appProcess.processName);
                    return false;
                }
                else
                {
                    LogUtil.i("前台", appProcess.processName);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断某个界面是否在前台
     * 
     * @param context
     * @param className
     *            某个界面名称
     */
    public static boolean isForeground(Context context, String className)
    {
        if (context == null || TextUtils.isEmpty(className))
        {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0)
        {
            ComponentName cpn = list.get(0).topActivity;
            LogUtil.d(TAG, "cpn.getClassName()="+cpn.getClassName());
            if (cpn.getClassName().endsWith(className))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 安装apk
     * 
     * @param context
     * @param apkFile
     */
    public static void openApkFile(Context context, File apkFile)
    {
        // TODO Auto-generated method stub
        LogUtil.e(TAG, "开始安装apk :" + apkFile.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static String getUserAgreement(Context context, String fileName)
    {
        AssetManager assetManager = context.getAssets();
        String strResponse = "";
        try
        {
            InputStream ims = assetManager.open(fileName);
            strResponse = getStringFromInputStream(ims);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is)
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try
        {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        return sb.toString();
    }
}
