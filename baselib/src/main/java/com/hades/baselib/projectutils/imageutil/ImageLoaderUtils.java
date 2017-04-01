package com.hades.baselib.projectutils.imageutil;

import android.content.Context;

import com.hades.baselib.projectutils.imageutil.commenbean.ImageParameter;
import com.hades.baselib.projectutils.imageutil.commeninterface.ImageLoaderStrategy;
import com.hades.baselib.projectutils.imageutil.strategy.GlideImageLoaderStrategy;

/**
 * 创建时间 2017/2/16
 * auther Hades
 * 重构使用策略模式，默认使用Glide库，可继承ImageLoaderStrategy接口实现新的图片加载库，并在使用前注入策略
 * 统一封装图片加载接口
 **/
public class ImageLoaderUtils {
    //图片默认加载类型
    public static final int IMG_DEFAULT_TYPE = 0;
    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private ImageLoaderStrategy mStrategy;

    public static ImageLoaderUtils getmIntance() {
        return SingleInstance.imageLoaderUtils;
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述
     *
     * @param strategy ImageLoaderStrategy 实现了图片加载库的统一封装
     *                 提供图片加载 缓存清理等接口
     **/
    public void setLoadStrategy(ImageLoaderStrategy strategy) {
        if (null != strategy) {
            mStrategy = strategy;
        }
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述
     *
     * @param mContext  Activity、Fragment、Application...
     * @param parameter 封装的图片信息参数类 此处为拷贝
     **/
    public void loadImage(Context mContext, ImageParameter parameter) {
        mStrategy.loadImage(mContext, parameter);
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述 内部使用了ImageView的Context
     **/
    public void loadImage(ImageParameter parameter) {
        mStrategy.loadImage(parameter);
    }

    /**
     * 创建时间 2017/2/16
     * auther Hades
     * 描述 清除图片缓存
     **/
    public void clearDiskCache(Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    public void clearMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    public void clearAllCache(Context context) {
        mStrategy.clearImageDiskCache(context);
        mStrategy.clearImageMemoryCache(context);
    }


    private ImageLoaderUtils() {
        //默认策略 Glide
        mStrategy = new GlideImageLoaderStrategy();
    }


    private static class SingleInstance {
        public static ImageLoaderUtils imageLoaderUtils = new ImageLoaderUtils();
    }


//    /**
//     * 创建时间 2017/2/16
//     * auther Hades
//     * 描述 Deprecated since 2017-2-16 后续使用策略模式封装的加载工具
//     **/
//    @Deprecated
//    public static void loadUserHeader(Context mContext, String url, ImageView img) {
//
//
//        Glide.with(mContext)
//                .load(url)
////                .transform(new GlideCircleTransform(mContext))
//                .error(0)//load失敗的Drawable
//                .placeholder(R.mipmap.ic_placeholder_userheader)//loading時候的Drawable
//                .dontAnimate() //去掉淡入淡出
//                // .animate()//設置load完的動畫
////                .centerCrop()//中心切圖, 會填滿
//                .fitCenter()//中心fit, 以原本圖片的長寬為主
//                .into(img);
//    }
//
//    @Deprecated
//    public static void loadBanner(Context mContext, String url, ImageView img) {
//        Glide.with(mContext)
//                .load(url)
//                .error(0)//load失敗的Drawable
//                .placeholder(R.mipmap.ic_placeholder_video)//loading時候的Drawable
//                .dontAnimate() //去掉淡入淡出
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                // .animate()//設置load完的動畫
////                .centerCrop()//中心切圖, 會填滿
//                .fitCenter()//中心fit, 以原本圖片的長寬為主
//                .into(img);
//    }
//
//    @Deprecated
//    public static void loadPicture(Context mContext, String url, ImageView img) {
//        Glide.with(mContext)
//                .load(url)
//                .error(0)//load失敗的Drawable
//                .placeholder(0)//loading時候的Drawable
//                .dontAnimate() //去掉淡入淡出
//                // .animate()//設置load完的動畫
//                .centerCrop()//中心切圖, 會填滿
////               .fitCenter()//中心fit, 以原本圖片的長寬為主
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(img);
//    }
//
//    @Deprecated
//    public static void loadPic(Context mContext, String url, ImageView img, int width, int heigh) {
//        Glide.with(mContext)
//                .load(url)
//                .error(0)//load失敗的Drawable
//                .placeholder(R.mipmap.ic_placeholder_video)//loading時候的Drawable
//                .dontAnimate() //去掉淡入淡出
//                // .animate()//設置load完的動畫
//                .centerCrop()//中心切圖, 會填滿
////               .fitCenter()//中心fit, 以原本圖片的長寬為主
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .override(width, heigh)
//                .into(img);
//    }




}
