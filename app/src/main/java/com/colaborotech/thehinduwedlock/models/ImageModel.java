package com.colaborotech.thehinduwedlock.models;

/**
 * Created by him on 27-Aug-17.
 */

public class ImageModel {
    int imageId;
    String imageURL;
    String deletedItem;
    String showingItem;
    String profilePic;


    public ImageModel(int imageId, String imageURL) {
        this.imageId = imageId;
        this.imageURL = imageURL;
    }

    public ImageModel(int imageId, String imageURL, String profilePic) {
        this.imageId = imageId;
        this.imageURL = imageURL;
        this.profilePic = profilePic;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDeletedItem() {
        return deletedItem;
    }

    public void setDeletedItem(String deletedItem) {
        this.deletedItem = deletedItem;
    }

    public String getShowingItem() {
        return showingItem;
    }

    public void setShowingItem(String showingItem) {
        this.showingItem = showingItem;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
