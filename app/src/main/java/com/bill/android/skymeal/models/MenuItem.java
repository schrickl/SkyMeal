package com.bill.android.skymeal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {

    private String name;
    private String description;
    private String price;
    private String url;

    public MenuItem(String name, String description, String price, String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    public MenuItem() {}

    protected MenuItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readString();
        url = in.readString();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(url);
    }
}
