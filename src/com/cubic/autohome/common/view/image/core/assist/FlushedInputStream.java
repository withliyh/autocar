package com.cubic.autohome.common.view.image.core.assist;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FlushedInputStream extends FilterInputStream {

	protected FlushedInputStream(InputStream in) {
		super(in);
	}
	


}
