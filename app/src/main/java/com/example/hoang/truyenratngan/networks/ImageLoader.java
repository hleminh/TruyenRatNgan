package com.example.hoang.truyenratngan.networks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.hoang.truyenratngan.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hoang on 5/14/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private String urlString;
    private String imageTag;

    public ImageLoader() {
    }

    public ImageLoader setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageTag = (imageView.getTag() == null) ? "" : (imageView.getTag().toString());
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public void loadImage(String urlString){
        if (!urlString.equals(imageTag)) {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageResource(R.drawable.progress_animation);
            execute(urlString);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        urlString = params[0];
        if (urlString.equals(imageTag)) {
            return null;
        }
        try {
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(bitmap);
            imageView.setTag(urlString);
        }
    }
}
