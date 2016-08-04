package list.infinite.com.infinitelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.Contacts;
import android.text.Layout;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by admin on 8/3/2016.
 */
public class CustomAdapters extends BaseAdapter {
    ArrayList<Photomodel.Photo> photos;
    LayoutInflater photoInflater;
    Context context;
    private LruCache<String, Bitmap> mMemoryCache;


    public CustomAdapters(ArrayList<Photomodel.Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
        photoInflater = LayoutInflater.from(context);

        // Get memory class of this device, exceeding this amount will throw an
        // OutOfMemory exception.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<>(cacheSize);
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public int getCount() {

        return photos.size();
    }

    @Override
    public Object getItem(int i) {
        return photos.get(i);

    }

    @Override
    public long getItemId(int i) {
        return photos.get(i).getPhotoId();

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;

        if (view == null) {
            view = photoInflater.inflate(R.layout.photolist, viewGroup, false);
            mViewHolder = new ViewHolder();
            mViewHolder.textView = (TextView) view.findViewById(R.id.textview);
            mViewHolder.imageView = (ImageView) view.findViewById(R.id.image_view);

            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        String photoId = String.valueOf(photos.get(i).getPhotoId());
        if (mMemoryCache.get(photoId) != null) {
            mViewHolder.imageView.setImageBitmap(mMemoryCache.get(photoId));
        } else {
            mViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.dummy));
            new LoadImage(photos.get(i).getImageUrl(), photoId, mViewHolder.imageView).execute();
        }

        mViewHolder.textView.setText(String.valueOf(photos.get(i).getPhotoId()));
        return view;
    }


    private class LoadImage extends AsyncTask<Void, Void, Bitmap> {
        String url;
        String id;
        ImageView imageView;

        public LoadImage(String url, String id, ImageView imageView) {
            this.url = url;
            this.id = id;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return RestServiceManager.getBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (id != null && bitmap != null) {
                mMemoryCache.put(id, bitmap);
                imageView.setImageBitmap(bitmap);
            }

        }
    }
}
