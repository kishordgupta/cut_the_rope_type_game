package com.kd.grabandcut.menu.level;

import java.util.HashMap;

import com.kd.grabandcut.menu.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class BitmapProvider {

	private static HashMap bitmaps = new HashMap();
	public static Resources res;


	public static Bitmap getBitmap(int resID) {
		try {
			if (bitmaps.get(resID) == null) {
//			Log.d("BitmapProvider", "Bitmap already Null");
				Bitmap bmp = BitmapFactory.decodeResource(res, resID);
				bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp
						.getHeight(), null, true);
				bitmaps.put(resID, bmp);			
				return bmp;
			} else
				return (Bitmap) bitmaps.get(resID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
