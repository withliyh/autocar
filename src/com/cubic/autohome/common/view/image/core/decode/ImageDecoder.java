package com.cubic.autohome.common.view.image.core.decode;

import java.io.IOException;

import android.graphics.Bitmap;

public interface ImageDecoder {
	Bitmap decode(ImageDecodingInfo decodingInfo) throws IOException;
}
