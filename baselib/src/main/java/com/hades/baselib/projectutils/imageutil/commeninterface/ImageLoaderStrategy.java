package com.hades.baselib.projectutils.imageutil.commeninterface;

import android.content.Context;

import com.hades.baselib.projectutils.imageutil.commenbean.ImageParameter;


/**
 * Created by Hades on 2017/2/16.
 * 图片加载Strategy接口，通过实现接口方便切换图片加载框架
 */

public interface ImageLoaderStrategy {

//    void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener);
//    void loadGifWithProgress(String url, ImageView imageView, ProgressLoadListener listener);

    void clearImageDiskCache(final Context context);
    void clearImageMemoryCache(Context context);
    //根据不同内存状态响应不同内存释放策略
    void trimMemory(Context context, int level);
    //获取缓存大小
    String getCacheSize(Context context);

    void loadImage(ImageParameter parameter);
    void loadImage(Context context, ImageParameter parameter);

}
