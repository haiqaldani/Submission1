package com.example.submission1;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {
    private int id;
    private String judul;
    private String poster;
    private String tahun;
    private String genre;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Film(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        poster = in.readString();
        tahun = in.readString();
        genre = in.readString();
        detail = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public Film() {

    }

    String getJudul() {
        return judul;
    }

    void setJudul(String judul) {
        this.judul = judul;
    }

    String getTahun() {
        return tahun;
    }

    void setTahun(String tahun) {
        this.tahun = tahun;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getDetail() {
        return detail;
    }

    void setDetail(String detail) {
        this.detail = detail;
    }

    String getPoster() {
        return poster;
    }

    void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(judul);
        parcel.writeString(poster);
        parcel.writeString(tahun);
        parcel.writeString(genre);
        parcel.writeString(detail);
    }

}
