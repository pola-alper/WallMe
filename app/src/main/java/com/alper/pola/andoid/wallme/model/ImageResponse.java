package com.alper.pola.andoid.wallme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shashank on 02-Aug-17.
 */

public class ImageResponse implements Serializable{
    @SerializedName("previewHeight")
    @Expose
    private Integer previewHeight;
    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("imageSize")
    @Expose
    private Integer imageSize;
    @SerializedName("fullHDURL")
    @Expose
    private String fullHDURL;
    @SerializedName("webformatHeight")
    @Expose
    private Integer webformatHeight;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("webformatWidth")
    @Expose
    private Integer webformatWidth;
    @SerializedName("previewWidth")
    @Expose
    private Integer previewWidth;
    @SerializedName("userImageURL")
    @Expose
    private String userImageURL;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;
    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;
    @SerializedName("imageWidth")
    @Expose
    private Integer imageWidth;
    @SerializedName("id_hash")
    @Expose
    private String idHash;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("imageHeight")
    @Expose
    private Integer imageHeight;

    public Integer getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(Integer previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public void setFullHDURL(String fullHDURL) {
        this.fullHDURL = fullHDURL;
    }

    public Integer getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(Integer webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(Integer webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public Integer getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(Integer previewWidth) {
        this.previewWidth = previewWidth;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getIdHash() {
        return idHash;
    }

    public void setIdHash(String idHash) {
        this.idHash = idHash;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "previewHeight=" + previewHeight +
                ", largeImageURL='" + largeImageURL + '\'' +
                ", userId=" + userId +
                ", imageSize=" + imageSize +
                ", fullHDURL='" + fullHDURL + '\'' +
                ", webformatHeight=" + webformatHeight +
                ", imageURL='" + imageURL + '\'' +
                ", webformatWidth=" + webformatWidth +
                ", previewWidth=" + previewWidth +
                ", userImageURL='" + userImageURL + '\'' +
                ", previewURL='" + previewURL + '\'' +
                ", webformatURL='" + webformatURL + '\'' +
                ", imageWidth=" + imageWidth +
                ", idHash='" + idHash + '\'' +
                ", user='" + user + '\'' +
                ", type='" + type + '\'' +
                ", imageHeight=" + imageHeight +
                '}';
    }
}

