
package com.mks.zakati.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * This the custom ImageView class for making image
 * view circular by masking the image from its corners
 */
public class CirculerImageView extends ImageView {

    public CirculerImageView(Context context) {
        super(context);
    }

    public CirculerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CirculerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = drawableToBitmap(drawable);
        if (b == null)
            return;
        Bitmap bitmap = b.copy(Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();
        int leftPadding = 0;
        int topPadding = 0;
        leftPadding = w > h ? (w - h) / 2 : 0;
        topPadding = h > w ? (h - w) / 2 : 0;

        Bitmap roundBitmap = getCroppedBitmap(bitmap, Math.min(w, h));

        canvas.drawBitmap(roundBitmap, leftPadding, topPadding, null);

    }

    /**
     * Method for converting drawable to bitmap
     * @param drawable drawble to be converted
     * @return bitmap of the drawable
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        if(drawable.getIntrinsicHeight() < 1 || drawable.getIntrinsicWidth() < 1)
            return null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * Method to crop the bitmap in to circular bitmap
     * @param bmp Bitmap to be cropped
     * @param radius radius of the circular cropped bitmap
     * @return Circular cropped bitmap
     */
    public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            if (getScaleType() == ScaleType.CENTER_CROP) {
                bmp = getSquaredBitmap(bmp);
            }
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        float strokeSize = radius / 10f;
        strokeSize = strokeSize == 0 ? 1 : strokeSize;

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        Paint sStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sStrokePaint.setStrokeWidth(strokeSize);
        sStrokePaint.setStyle(Paint.Style.STROKE);
        sStrokePaint.setColor(Color.parseColor("#FFFFFF"));
        sStrokePaint.setAlpha(0x00);
        float cx = sbmp.getWidth() / 2;
        float cy = sbmp.getHeight() / 2;
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(cx, cy, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        //canvas.drawCircle(cx, cy, sbmp.getWidth() / 2 - strokeSize, sStrokePaint);

        float opaqueStrokeWidth = radius / 50f;
        opaqueStrokeWidth = opaqueStrokeWidth == 0 ? 1 : opaqueStrokeWidth;
        sStrokePaint.setStrokeWidth(opaqueStrokeWidth);
        sStrokePaint.setColor(getResources().getColor(android.R.color.white));
        //canvas.drawCircle(cx, cy, sbmp.getWidth() / 2 - opaqueStrokeWidth / 2, sStrokePaint);

        return output;
    }

    /**
     * Method for mnaking a bitmap(image) squared
     * @param srcBmp bitmap to be squared
     * @return squared bitmap
     */
    private Bitmap getSquaredBitmap(Bitmap srcBmp) {
        Bitmap dstBmp;
        if (srcBmp.getWidth() >= srcBmp.getHeight()) {

            dstBmp = Bitmap.createBitmap(srcBmp,
                    srcBmp.getWidth() / 2 - srcBmp.getHeight() / 2, 0,
                    srcBmp.getHeight(), srcBmp.getHeight());

        } else {

            dstBmp = Bitmap.createBitmap(srcBmp, 0, srcBmp.getHeight() / 2
                            - srcBmp.getWidth() / 2, srcBmp.getWidth(),
                    srcBmp.getWidth());
        }
        return dstBmp;
    }
}
