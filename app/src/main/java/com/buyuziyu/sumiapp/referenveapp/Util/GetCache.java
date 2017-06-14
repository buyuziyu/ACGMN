package com.buyuziyu.sumiapp.referenveapp.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment.OffprintFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by buyuziyu on 2017/4/30.
 */

public class GetCache {

    /*
    * 作用:在文件匣下生成‘本子’下四个文件夹的所有漫画首页缓存
    * */

    public static final String TAG = "TAG";
    public static final String tag = OffprintFragment.class.getSimpleName() + " | ";


    /*
    * 本子文件夹路径
    * */
    public static final String parentsPath = Environment.getExternalStorageDirectory() +
            File.separator + "文件匣" + File.separator + "本子";

    /*
    * 四个文件夹名字
    * */
    String[] childrenName = {"单行本", "同人本", "C展", "pixiv"};

    /*
    * 缓存文件夹路经
    * */
    public static final String cacheParantsPath = Environment.getExternalStorageDirectory() +
            File.separator + "文件匣" + File.separator + "cache";

    /*
    * 四个缓存文件夹名字
    * */
    static String[] cacheChildrenName = {"offprint", "comicmarket", "pixiv", "donjin"};

    public static void createCache(){
        if (createCacheChildrenFiles()){
            try {
                getAllComicFirstPager();
                Log.i("MTAG", tag + "缓存首页成功");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i(TAG, tag + "复制首页是发生失败");
            }
        } else {
            Log.i(TAG, tag + "缓存文件夹生成失败");
        }
    }


    /*
    * 生成四个缓存文件夹
    * */
    public static boolean createCacheChildrenFiles(){
        File cacheParantsFile = new File(cacheParantsPath);
        File cacheChildFile;
        for (int i = 0; i < cacheChildrenName.length; i++){
            cacheChildFile = new File(cacheParantsFile, cacheChildrenName[i]);
            if (!cacheChildFile.exists()) {
                cacheChildFile.mkdirs();
            }
        }
//        String[] temp = new File(parentsPath).list();
//        Log.i(TAG, tag + temp[0] + temp[1] + temp[2] + temp[3]);
        return true;
    }

    /*
    * 获取本子的首页
    * */
    public static boolean getAllComicFirstPager() throws FileNotFoundException {

        String[] childrenName = new File(parentsPath).list();
        File[] singleCoimcFile;
        File[] singlePicFile;
        String singleComicName;
        File toFile;
        Bitmap bitmap;

        for (int i = 0; i < childrenName.length; i++){
            //访问独立的本子文件夹
            singleCoimcFile = new File(parentsPath + File.separator
                    + childrenName[i]).listFiles();

            for (int j = 0; j < singleCoimcFile.length; j++){
                //获得本子名称
                singleComicName = singleCoimcFile[j].getName();

                //在相应缓存文件夹下创建缓存图片
                toFile = new File(cacheParantsPath + File.separator
                        + cacheChildrenName[i] + File.separator + singleComicName + ".jpg");

                if (toFile != null){
                    Log.i(TAG, tag + singleComicName + "已存在");
                    continue;
                } else {
                    //获得第一张图片
                    singlePicFile = singleCoimcFile[j].listFiles();

                    //转换成bitmap压缩
                    bitmap = BitmapFactory.decodeFile(singlePicFile[0].getAbsolutePath());
                    bitmap = zoomImage(bitmap);

                    Log.i(TAG, tag + toFile.getAbsolutePath());
                    saveBitmap(toFile, bitmap);
                }
            }

        }

        return true;
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) (width/2) )/ width;
        float scaleHeight = ((float) (height/2)) / height;
        // 缩放图片动作
        matrix.postScale((float) scaleWidth,(float) scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    public static void saveBitmap(File toFile, Bitmap bitmap) {
        Log.e(TAG, "保存图片");
        try {
            FileOutputStream out = new FileOutputStream(toFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
