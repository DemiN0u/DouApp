package ua.blacky.douapp.models;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amatsegor on 02.07.15.
 */
public class Event {
    private String mName;
    private Date mDate;
    private String mCity;
    private String mAddress;
    private String mDescription;
    private Location mLocation;
    private List<String> mTags = new ArrayList<>();
    private int mAttends;
    private int mCommentsNum;
    private String mPictureUrl;

    private Event() {

    }

    public static Builder newBuilder() {
        return new Event().new Builder();
    }

    public String getName() {
        return mName;
    }

    public Date getDate() {
        return mDate;
    }

    public String getCity() {
        return mCity;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getDescription() {
        return mDescription;
    }

    public Location getLocation() {
        return mLocation;
    }

    public List<String> getTags() {
        return mTags;
    }

    public int getAttends() {
        return mAttends;
    }

    public int getCommentsNum() {
        return mCommentsNum;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public void addTag(String tag) {
        mTags.add(tag);
    }

    public class Builder {
        private Builder() {

        }

        public Builder setName(String name) {
            Event.this.mName = name;
            return this;
        }

        public Builder setDate(Date date) {
            Event.this.mDate = date;
            return this;
        }

        public Builder setCity(String city) {
            Event.this.mCity = city;
            return this;
        }

        public Builder setAddress(String address) {
            Event.this.mAddress = address;
            return this;
        }

        public Builder setDescription(String description) {
            Event.this.mDescription = description;
            return this;
        }

        public Builder setLocation(Location location) {
            Event.this.mLocation = location;
            return this;
        }

        public Builder setTags(List<String> tags) {
            Event.this.mTags = tags;
            return this;
        }

        public Builder setAttends(int attends) {
            Event.this.mAttends = attends;
            return this;
        }

        public Builder setCommentsNum(int commentsNum) {
            Event.this.mCommentsNum = commentsNum;
            return this;
        }

        public Builder setPictureUrl(String pictureUrl) {
            Event.this.mPictureUrl = pictureUrl;
            return this;
        }

        public Event build() {
            if (mName == null) {
                throw new IllegalArgumentException("Event must have a name");
            }
            return Event.this;
        }
    }
}
