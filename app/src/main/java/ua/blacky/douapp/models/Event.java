package ua.blacky.douapp.models;

import android.databinding.BindingAdapter;
import android.location.Location;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amatsegor on 02.07.15.
 */
public class Event {
    private String name;
    private String date;
    private String city;
    private String address;
    private String description;
    private Location location;
    private List<String> tags = new ArrayList<>();
    private String attends;
    private int comments_num;
    private String pictureurl;

    private Event() {

    }

    public static Builder newBuilder() {
        return new Event().new Builder();
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTagsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            sb.append(tags.get(i));
            if (i != tags.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public String getAttends() {
        return attends;
    }

    public String getAttendsString() {
        return attends;
    }

    public int getCommentsNum() {
        return comments_num;
    }

    public String getPictureUrl() {
        return pictureurl;
    }

    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView view, String imageUrl){
        Picasso.with(view.getContext()).load(imageUrl).into(view);
    }

    public class Builder {
        private Builder() {

        }

        public Builder setName(String name) {
            Event.this.name = name;
            return this;
        }

        public Builder setDate(String date) {
            Event.this.date = date;
            return this;
        }

        public Builder setCity(String city) {
            Event.this.city = city;
            return this;
        }

        public Builder setAddress(String address) {
            Event.this.address = address;
            return this;
        }

        public Builder setDescription(String description) {
            Event.this.description = description;
            return this;
        }

        public Builder setLocation(Location location) {
            Event.this.location = location;
            return this;
        }

        public Builder setTags(List<String> tags) {
            Event.this.tags = tags;
            return this;
        }

        public Builder setAttends(String attends) {
            Event.this.attends = attends;
            return this;
        }

        public Builder setCommentsNum(int commentsNum) {
            Event.this.comments_num = commentsNum;
            return this;
        }

        public Builder setPictureUrl(String pictureUrl) {
            Event.this.pictureurl = pictureUrl;
            return this;
        }

        public Event build() {
            return Event.this;
        }
    }
}
