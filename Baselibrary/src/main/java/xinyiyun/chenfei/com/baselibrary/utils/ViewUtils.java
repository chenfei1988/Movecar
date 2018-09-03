package xinyiyun.chenfei.com.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;


import java.text.DecimalFormat;

import xinyiyun.chenfei.com.baselibrary.R;


/**
 * Created by YangQiang on 2017/8/8.
 */

public class ViewUtils {


    public static void steepStatusBar(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight(activity);
            if (view != null) {
                int viewTopPadding = view.getPaddingTop();
                view.getLayoutParams().height += statusBarHeight;
                view.setPadding(view.getPaddingLeft(), viewTopPadding + statusBarHeight,
                        view.getPaddingRight(),
                        view.getPaddingBottom());
            }
        }

    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 获得状态栏高度
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取屏幕宽度
     *
     * @param  activity
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        return width;
    }
    /**
     * 获取屏幕高度
     *
     * @param  activity
     * @return 屏幕高度
     */
    public static int getScreenHeight(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int height = outMetrics.heightPixels;
        return height;
    }
    /**
     * 计算颜色值
     *
     * @param color
     * @param alpha
     * @return
     */
    public static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    public static void translucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static int getMipmap(Context context, String name) {
        return getIdentifier(context, name, "mipmap");
    }

    public static int getArray(Context context, String name) {
        return getIdentifier(context, name, "array");
    }

    public static int getDrawable(Context context, String name) {
        return getIdentifier(context, name, "drawable");
    }


    public static int getIdentifier(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

    public static void showClearCar(Context mContext, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        TextView tv = new TextView(mContext);
        tv.setText("清空购物车?");
        tv.setTextSize(14);
        tv.setPadding(ViewUtils.dip2px(mContext, 16), ViewUtils.dip2px(mContext, 16), 0, 0);
        tv.setTextColor(Color.parseColor("#757575"));
        AlertDialog alertDialog = builder
                .setNegativeButton("取消", null)
                .setCustomTitle(tv)
                .setPositiveButton("清空", onClickListener)
                .show();
        Button nButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nButton.setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
        nButton.setTypeface(Typeface.DEFAULT_BOLD);
        Button pButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
        pButton.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public static void addTvAnim(View fromView, int[] carLoc, Context context, final CoordinatorLayout rootview) {
        int[] addLoc = new int[2];
        fromView.getLocationInWindow(addLoc);

        Path path = new Path();
        path.moveTo(addLoc[0], addLoc[1] - 200);
        path.quadTo(carLoc[0], addLoc[1] - 200, carLoc[0], carLoc[1]);

        final TextView textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.circle_blue);
        textView.setText("1");
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(fromView.getWidth(), fromView.getHeight());
        rootview.addView(textView, lp);
        ViewAnimator.animate(textView).path(path).accelerate().duration(400).onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                rootview.removeView(textView);
            }
        }).start();
    }

    public static String formatPrice(double val) {
        return new DecimalFormat("#.00").format(val);
    }

}
