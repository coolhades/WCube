package com.hades.wcube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.R;
import com.hades.wcube.video.PlayerFragmentAcivity;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mehdi.sakout.fancybuttons.FancyButton;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadStatus;

/**
 * Created by Hades on 2017/3/26.
 */

public class ConnectedFragment extends NormalBaseFragment {
    private FancyButton btnBack;
    private TextView tvTitle;
    private FancyButton btnReload;
    private RelativeLayout btnDownload;
    private FancyButton icon;
    private TextView tvContenttitle;
    private RelativeLayout btnGoplay;
    private FancyButton icon2;
    private TextView tvContenttitle2;

    RxDownload rxDownload ;  //单例

    String url = "http://ir360.haoduotools.com/Files/VRZip/3A8EB81F4C2242078ADC19C54396CC99.mp4";
    String[] BIG_FILE_URLS = {
            // 5m
            "http://mirror.internode.on.net/pub/test/5meg.test5",
            // 6m
            "http://download.chinaunix.net/down.php?id=10608&ResourceID=5267&site=1",
            // 8m
            "http://7xjww9.com1.z0.glb.clouddn.com/Hopetoun_falls.jpg",
            // 10m
            "http://dg.101.hk/1.rar",
            // 22m
            "http://113.207.16.84/dd.myapp.com/16891/2E53C25B6BC55D3330AB85A1B7B57485.apk?mkey=5630b43973f537cf&f=cf87&fsname=com.htshuo.htsg_3.0.1_49.apk&asr=02f1&p=.apk",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_connected, container, false);
        init(v,savedInstaneState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        btnBack = (FancyButton) view.findViewById(R.id.btn_back);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText("连接成功");
        btnReload = (FancyButton) view.findViewById(R.id.btn_reload);
        btnReload.setVisibility(View.INVISIBLE);
        btnDownload = (RelativeLayout) view.findViewById(R.id.btn_download);
        icon = (FancyButton) view.findViewById(R.id.icon);
        tvContenttitle = (TextView) view.findViewById(R.id.tv_contenttitle);
        btnGoplay = (RelativeLayout) view.findViewById(R.id.btn_goplay);
        icon2 = (FancyButton) view.findViewById(R.id.icon2);
        tvContenttitle2 = (TextView) view.findViewById(R.id.tv_contenttitle2);

        rxDownload = RxDownload.getInstance(getActivity());

    }
    String innerPath;
    @Override
    protected void initData() {

        innerPath = getActivity().getFilesDir().getAbsolutePath();
        Log.d("FileName",getFileExtensionFromUrl(url));

    }

    @Override
    protected void initEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //下载视频
//                final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(fileDownloadListener);
//
//                final List<BaseDownloadTask> tasks = new ArrayList<>();
//                for (int i = 0; i < 1; i++) {
//                    BaseDownloadTask task = FileDownloader.getImpl().create(url);
//                    task.setPath(innerPath,true);
//
//                    tasks.add(task);
//
//                }
//                queueSet.downloadTogether(tasks);
//                queueSet.disableCallbackProgressTimes();
//                queueSet.start();

                Disposable disposable = RxDownload.getInstance(getActivity())
                        .defaultSavePath(innerPath)
                        .download(url)                       //只传url即可
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DownloadStatus>() {
                            @Override
                            public void accept(DownloadStatus status) throws Exception {
                                //DownloadStatus为下载进度
                                Log.d("Disposable", status.getFormatStatusString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                //下载失败
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                //下载成功
                                Toast.makeText(getActivity(), "DownLoadFinish", Toast.LENGTH_SHORT).show();
                                
                            }
                        });
            }
        });

        btnGoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PlayerFragmentAcivity.class));
            }
        });
    }


    FileDownloadListener fileDownloadListener = new FileDownloadListener() {
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.d("FileDownloadListener", "pending   "+"soFarBytes="+soFarBytes+"   totalBytes="+totalBytes);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.d("FileDownloadListener", "progress   "+"soFarBytes="+soFarBytes+"   totalBytes="+totalBytes);
        }

        @Override
        protected void completed(BaseDownloadTask task) {
            Log.d("FileDownloadListener", "fileName="+task.getFilename()+"  filePath="+task.getTargetFilePath()
            + "  filepath="+task.getPath()+"  url="+task.getUrl());
            //缓存列表到本地  tag + 路径  tag用来切换视频
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.d("FileDownloadListener", "paused");
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            Log.d("FileDownloadListener", "error"+e.toString());
        }

        @Override
        protected void warn(BaseDownloadTask task) {
            Log.d("FileDownloadListener", "warn");
        }
    };

    /**
     * Returns the file extension or an empty string iff there is no
     * extension. This method is a convenience method for obtaining the
     * extension of a url and has undefined results for other Strings.
     * @param url
     * @return The file extension of the given url.
     */
    public static String getFileExtensionFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
//            if (!filename.isEmpty() &&
//                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
//                int dotPos = filename.lastIndexOf('.');
//                if (0 <= dotPos) {
//                    return filename.substring(dotPos + 1);
//                }
//            }
            return filename;
        }

        return "";
    }
}
