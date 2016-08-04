package list.infinite.com.infinitelist;

import android.content.Context;
import android.provider.Contacts;
import android.text.Layout;
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


    public CustomAdapters(ArrayList<Photomodel.Photo> photos , Context context) {
        this.photos = photos;
        this.context = context;
        photoInflater = LayoutInflater.from(context);
    }
    public static class ViewHolder
    {
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
        mViewHolder.textView.setText(photos.get(i).getPhotoId());
        return view;
    }
}
