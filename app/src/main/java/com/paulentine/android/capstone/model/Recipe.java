package com.paulentine.android.capstone.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

/**
 * Recipe POJO.
 */
@IgnoreExtraProperties
public class Recipe {
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_READY_IN_MINUTES = "readyInMinutes";
    public static final String FIELD_SERVINGS = "servings";

    private int id;
    private String title;
    private int readyInMinutes;
    private int servings;
    private List<Step> steps;
//    private int currentStep;

//    private String image;

//    public static final String FIELD_CITY = "city";
//    public static final String FIELD_CATEGORY = "category";
//    public static final String FIELD_PRICE = "price";
//    public static final String FIELD_POPULARITY = "numRatings";
//    public static final String FIELD_AVG_RATING = "avgRating";
//
//    private String name;
//    private String city;
//    private String category;
//    private String photo;
//    private int price;
//    private int numRatings;
//    private double avgRating;

    public Recipe() {}

//    public Recipe(String name, String city, String category, String photo,
////                  int price, int numRatings, double avgRating) {
////        this.name = name;
////        this.city = city;
////        this.category = category;
////        this.price = price;
////        this.numRatings = numRatings;
////        this.avgRating = avgRating;
////    }

    public Recipe(int id, String title, int readyInMinutes, int servings, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.steps = steps;
//        this.currentStep = 0;

//        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

//    public int getCurrentStep() {
//        return currentStep;
//    }


//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int getNumRatings() {
//        return numRatings;
//    }
//
//    public void setNumRatings(int numRatings) {
//        this.numRatings = numRatings;
//    }
//
//    public double getAvgRating() {
//        return avgRating;
//    }
//
//    public void setAvgRating(double avgRating) {
//        this.avgRating = avgRating;
//    }
}
