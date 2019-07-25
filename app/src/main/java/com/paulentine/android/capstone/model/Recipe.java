/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.paulentine.android.capstone.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

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

    public Recipe(int id, String title, int readyInMinutes, int servings) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
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
