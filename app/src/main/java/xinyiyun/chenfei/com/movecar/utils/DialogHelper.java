package xinyiyun.chenfei.com.movecar.utils;

import android.app.Dialog;
import android.content.Context;

import xinyiyun.chenfei.com.movecar.R;


public class DialogHelper {
	public static Dialog mLoadingDialog;

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @return Dialog
	 */
	public static void showLoadingDialog(Context context) {
		mLoadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
		mLoadingDialog.setContentView(R.layout.dialog_view);// 设置布局
		mLoadingDialog.setCancelable(true);// 不可以用“返回键”取消
		mLoadingDialog.setCanceledOnTouchOutside(false);// 点击屏幕其它地方不消失
		try{
			mLoadingDialog.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void dismissLoadingDialog() {
		if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}
	
	public static boolean Isshow(){
		
		if (mLoadingDialog != null && mLoadingDialog.isShowing()){
			
			return true;
		}
		else{
			
			return false;
		}
	}
}
