package com.hfad.chapter6_listview;



public class Drink {

  private String name;
  private String description;
  private int imageResourceId;
  private boolean favorite;

  public Drink() {
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getImageResourceId() {
    return imageResourceId;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImageResourceId(int imageResourceId) {
    this.imageResourceId = imageResourceId;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  @Override public String toString() {
    return this.name;
  }
}
