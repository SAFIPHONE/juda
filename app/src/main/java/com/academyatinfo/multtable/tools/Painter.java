package com.academyatinfo.multtable.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.academyatinfo.multtable.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by geek on 03/09/17.
 */

public class Painter {


    public static Bitmap paint(Context context, Bitmap bitmap, String user_name, String family_name, String degree, String gender) {

        Config configBitmap = bitmap.getConfig();
        // set default bitmap config if none
        if (configBitmap == null) {
            configBitmap = android.graphics.Bitmap.Config.ARGB_8888;
        }

        bitmap = bitmap.copy(configBitmap, true);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Rect rect = new Rect();

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.pathFontUserName);


        // draw user name and family name
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.user_name_certificate)
                , Constants.sizeUserName, Constants.xUserName, Constants.yUserName, user_name + " " + family_name);

        typeface = Typeface.createFromAsset(context.getAssets(), Constants.pathFontUserName);

        // draw text certification
        String text = context.getResources().getString(R.string.text_certification_male1);
        text = text.replaceAll("---", user_name + " " + family_name);
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.text_certificate)
                , Constants.sizeTextCertification, Constants.xTextCertification1, Constants.yTextCertification1, text);

        text = context.getResources().getString(R.string.text_certification_male2);
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.text_certificate)
                , Constants.sizeTextCertification, Constants.xTextCertification2, Constants.yTextCertification2, text);

        text = context.getResources().getString(R.string.text_certification_male3);
        text = text.replaceAll("###", degree);
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.text_certificate)
                , Constants.sizeTextCertification, Constants.xTextCertification3, Constants.yTextCertification3, text);

        typeface = Typeface.createFromAsset(context.getAssets(), Constants.pathFontSignature);
        // draw signature
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.text_signature)
                , Constants.sizeSignature, Constants.xTextSignature, Constants.yTextSignature, context.getResources()
                        .getString(R.string.signature));

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        text = "التاريخ : " + format.format(new Date());
        // draw Date
        drawer(canvas, bitmap, paint, rect, typeface, context.getResources().getColor(R.color.text_signature)
                , Constants.sizeDate, Constants.xTextDate, Constants.yTextDate, text);
        return bitmap;
    }

    private static void drawer(Canvas canvas, Bitmap bitmap, Paint paint, Rect rect, Typeface typeface, int color, int size, int x, int y, String text) {


        paint.setTypeface(typeface);

        paint.setColor(color);

        paint.setTextSize(size);

        paint.getTextBounds(text, 0, text.length(), rect);

        x = (bitmap.getWidth() - rect.width()) / 2 + x;
        y = (bitmap.getHeight() + rect.height()) / 2 + y;

        canvas.drawText(text, x, y, paint);

    }
}
