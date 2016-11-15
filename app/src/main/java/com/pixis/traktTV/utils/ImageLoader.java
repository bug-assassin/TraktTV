package com.pixis.traktTV.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dan on 11/10/2016.
 */
//Based on http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
public class ImageLoader {
    /**
     * Saves the bitmap into the internal storage
     *
     * @param context
     * @param bitmap
     * @param recipeId Bitmap will be saved using the recipeId
     * @return the path to the bitmap or null if there was an error
     */
    public static String saveImage(Context context, Bitmap bitmap, String recipeId) {
        try {
            saveImageInternal(context, bitmap, recipeId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String saveImageInternal(Context context, Bitmap bitmap, String recipeId) throws IOException {
        File path = context.getDir("recipe_images", Context.MODE_PRIVATE);
        File imagePath = new File(path, recipeId);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
        }

        return imagePath.getAbsolutePath();
    }

    public static Bitmap loadImage(String path) {
        if (path == null)
            return null;

        File file = new File(path);
        FileInputStream fileInputStream = null;
        try {
            return BitmapFactory.decodeStream(fileInputStream = new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void loadImageOnto(String path, @DrawableRes int placeHolder, ImageView target) {
        Bitmap image = loadImage(path);
        if(image == null) {
            Drawable drawable = ContextCompat.getDrawable(target.getContext(), placeHolder);
            target.setImageDrawable(drawable);
        }
        else {
            target.setImageBitmap(image);
        }
    }
}
