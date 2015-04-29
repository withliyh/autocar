package com.cubic.autohome.common.view;

import com.cubic.autohome.business.MyApplication;
import com.cubic.autohome.common.IF.IimageDoLoadListener;
import com.cubic.autohome.common.view.image.core.ImageLoader;
import com.cubic.autohome.common.view.image.core.assist.FailReason;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class RemoteImageView extends ImageView {

	public IimageDoLoadListener imageDoLoadListener;
	public int inGroup = -1;
	private Context mContext;
	private LoadImageCompletedListener mLoadImageCompletedListener;
	LoadImageFailListener mLoadImageFailListener;
	
	public RemoteImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RemoteImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public RemoteImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	

	public static interface LoadImageFailListener {
		public void onLoadFail();
	}
	
	
	private void recyleObjects() {
		if (mLoadImageCompletedListener != null) {
			mLoadImageCompletedListener = null;
		}
		if (imageDoLoadListener != null) {
			imageDoLoadListener = null;
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		recyleObjects();
		super.finalize();
	}
	
	public void setDefaultImage(Drawable drawable) {
		setImageDrawable(drawable);
	}
	
	public void setDefaultImage(Integer resid) {
		setBackgroundResource(resid);
	}
	
	public void setImageUrl(String url) {
		if (!TextUtils.isEmpty(url)) {
			setTag(url);
			if ((!ImageLoader.getInstance().isInited()) && (this.mContext != null)) {
				MyApplication.getInstance().initImageConfig(this.mContext.getApplicationContext());
			}
			ImageLoader.getInstance().displayImage(url, this, new ImageLoadingListener() {

				@Override
				public void onLoadingCancelled(String paramString,
						View paramView) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onLoadingComplete(String paramString,
						View paramView, Bitmap paramBitmap) {
					if (paramString != null && RemoteImageView.this.mLoadImageCompletedListener != null) {
						RemoteImageView.this.mLoadImageCompletedListener.onLoadCompleted(paramBitmap);
					}
				}

				@Override
				public void onLoadingFailed(String paramString, View paramView,
						FailReason paramFailReason) {
					if (RemoteImageView.this.mLoadImageFailListener != null) {
						RemoteImageView.this.mLoadImageFailListener.onLoadFail();
					}
				}

				@Override
				public void onLoadingStarted(String paramString, View paramView) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
	}
	
	public static interface LoadImageCompletedListener {
		public void onLoadCompleted(Bitmap bitmap);
	}
	
	public void setLoadFailListener(LoadImageFailListener listener) {
		this.mLoadImageFailListener = listener;
	}
	
	public void setLoadImageCompleted(LoadImageCompletedListener listener) {
		this.mLoadImageCompletedListener = listener;
	}
}
