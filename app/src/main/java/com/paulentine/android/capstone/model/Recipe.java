package com.paulentine.android.capstone.model;

import android.util.Log;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe POJO.
 */
@IgnoreExtraProperties
public class Recipe {
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_READY_IN_MINUTES = "readyInMinutes";
    public static final String FIELD_SERVINGS = "servings";

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;

    @SerializedName("servings")
    @Expose
    private Integer servings;

    @SerializedName("extendedIngredients")
    @Expose
    private List<ExtendedIngredient> extendedIngredients = new ArrayList<ExtendedIngredient>();

    private List<Step> steps;

    private int stepCursor;
    public static Recipe currRecipe;

    public Recipe() {
        resetCursor();
    }

    public Recipe(String title, int readyInMinutes, int servings, List<ExtendedIngredient> extendedIngredients, List<Step> steps) {
        this();
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;

        this.extendedIngredients = extendedIngredients;
        this.steps = steps;
    }

    public String nextStep() {
        if (stepCursor < (steps.size() -1 )) {
            stepCursor += 1;
            Log.d("stepCursor1", (stepCursor) + " " + steps.size());

        }
        Log.d("stepCursor2", (stepCursor) + " " + steps.size());
        return readInstruction();
    }

    public String readInstruction() {
        String text = steps.get(stepCursor).getInstruction();
        Log.d("stepCursorInstruction", (text == null) ? "null": text);
        return text;
    }

    public void resetCursor() {
        this.stepCursor = 0;
        Log.d("resetCursor", "This is reset cursor");
    }

    public int getStepCursor() {
        return stepCursor;
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

    public List<ExtendedIngredient> getExtendedIngredients() { return extendedIngredients; }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
