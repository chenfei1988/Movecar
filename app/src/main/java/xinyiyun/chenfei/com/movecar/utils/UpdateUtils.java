package xinyiyun.chenfei.com.movecar.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateUtils {

    /*
     * 从服务器中下载APK
     */
    public static void downLoadApk(Context mcontext, String downurl) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            final ProgressDialog pd;    //进度条对话框
            pd = new ProgressDialog(mcontext);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setMessage("正在下载更新");
            pd.setCanceledOnTouchOutside(false);
            //  pd.setCancelable(false);//设置进度条是否可以按退回键取消
            pd.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        File file = getFileFromServer(downurl, pd);
                        sleep(3000);
                        installApk(file, mcontext);
                        pd.dismiss(); //结束掉进度条对话框
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            Toast.makeText(mcontext, "SD卡不可用，请插入SD卡", Toast.LENGTH_LONG);
        }

    }

    //安装apk
    public static void installApk(File file, Context mcontext) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        //关键点：
        //安装完成后执行打开
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    public static File getFileFromServer(String path, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "KDBC.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
                pd.setProgressNumberFormat("");
                pd.setTitle((int) (total / conn.getContentLength() * 100) + "%");
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

}
