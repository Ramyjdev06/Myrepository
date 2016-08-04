package list.infinite.com.infinitelist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 8/2/2016.
 */
public class Photomodel {
    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @SerializedName("photos")
    ArrayList<Photo> photos;

    public class Photo {
        public int getPhotoId() {
            return photoId;
        }

        @SerializedName("id")
        int photoId;

        public String getImageUrl() {
            return imageUrl;
        }

        @SerializedName("image_url")
        String imageUrl;

    }

}
