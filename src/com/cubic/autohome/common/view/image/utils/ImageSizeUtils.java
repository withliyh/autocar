package com.cubic.autohome.common.view.image.utils;

import android.opengl.GLES10;
import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;

public final class ImageSizeUtils
{
  private static ImageSize maxBitmapSize = new ImageSize(0, 0);

  static
  {
    int[] arrayOfInt = new int[1];
    GLES10.glGetIntegerv(3379, arrayOfInt, 0);
    int i = Math.max(arrayOfInt[0], 2048);
  }

  public static int computeImageSampleSize(ImageSize paramImageSize1, ImageSize paramImageSize2, ViewScaleType paramViewScaleType, boolean paramBoolean)
  {
    int m = paramImageSize1.getWidth();
    int n = paramImageSize1.getHeight();
    int i1 = paramImageSize2.getWidth();
    int i2 = paramImageSize2.getHeight();
    int j = 1;
    int i = 1;
    int k = 1;
   
    return 0;
  }

  public static float computeImageScale(ImageSize paramImageSize1, ImageSize paramImageSize2, ViewScaleType paramViewScaleType, boolean paramBoolean)
  {
    int k = paramImageSize1.getWidth();
    int m = paramImageSize1.getHeight();
    int j = paramImageSize2.getWidth();
    int i = paramImageSize2.getHeight();
    float f1 = k / j;
    float f2 = m / i;
    if (((paramViewScaleType == ViewScaleType.FIT_INSIDE) && (f1 >= f2)) || ((paramViewScaleType == ViewScaleType.CROP) && (f1 < f2)))
      i = (int)(m / f1);
    while (true)
    {
      f2 = 1.0F;
      if ((paramBoolean) || (j >= k) || (i >= m))
      {
        f1 = f2;
        if (paramBoolean)
        {
          f1 = f2;
          if (j != k)
          {
            f1 = f2;
            if (i == m);
          }
        }
      }
      else
      {
        f1 = j / k;
      }
      return f1;
    }
  }

  public static int computeMinImageSampleSize(ImageSize paramImageSize)
  {
    int i = paramImageSize.getWidth();
    int j = paramImageSize.getHeight();
    int k = maxBitmapSize.getWidth();
    int m = maxBitmapSize.getHeight();
    return Math.max((int)Math.ceil(i / k), (int)Math.ceil(j / m));
  }

  private static int considerMaxTextureSize(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = maxBitmapSize.getWidth();
    int j = maxBitmapSize.getHeight();
    while (true)
    {
      if ((paramInt1 / paramInt3 <= i) && (paramInt2 / paramInt3 <= j))
        return paramInt3;
      if (paramBoolean)
        paramInt3 *= 2;
      else
        paramInt3 += 1;
    }
  }

  public static ImageSize defineTargetSizeForView(ImageAware paramImageAware, ImageSize paramImageSize)
  {
    int j = paramImageAware.getWidth();
    int i = j;
    if (j <= 0)
      i = paramImageSize.getWidth();
    int k = paramImageAware.getHeight();
    j = k;
    if (k <= 0)
      j = paramImageSize.getHeight();
    return new ImageSize(i, j);
  }
}