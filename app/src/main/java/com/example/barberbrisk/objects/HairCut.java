package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HairCut implements Parcelable {


    enum HaircutStyle {
        BUZZ_CUT, PIXIE_CUT, MOHAWK, BOB, FADE, UNDERCUT, POMPADOUR;
    }

    private Double price;
    private HaircutStyle hairCutStyle;

    public HairCut() {
    }

    public HairCut(Double price, HaircutStyle haircutStyle) {
        this.price = price;
        this.hairCutStyle = haircutStyle;
    }

    public HaircutStyle getHairCutStyle() {
        return hairCutStyle;
    }

    public void setHairCutStyle(HaircutStyle hairCutStyle) {
        this.hairCutStyle = hairCutStyle;
    }

    @NonNull
    @Override
    public String toString() {
        return "HairCut{" +
                "price=" + price +
                ", hairCutStyle=" + hairCutStyle +
                '}';
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    protected HairCut(Parcel in) {
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
    }

    public static final Creator<HairCut> CREATOR = new Creator<HairCut>() {
        @Override
        public HairCut createFromParcel(Parcel in) {
            return new HairCut(in);
        }

        @Override
        public HairCut[] newArray(int size) {
            return new HairCut[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }

    }


}
