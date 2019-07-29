package com.paulentine.android.capstone.model;

import android.util.Log;

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
    private int stepCursor;

    public Recipe() {
        resetCursor();
    }

    public Recipe(int id, String title, int readyInMinutes, int servings, List<Step> steps) {
        this();
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;

        this.steps = steps;
    }

    public void moveCursorUp() {
        if (stepCursor < (steps.size() -1 )) {
            stepCursor += 1;
            Log.d("stepCursor1", (stepCursor) + " " + steps.size());

        }
//        Log.d("stepCursor2", Integer.toString(stepCursor));
        Log.d("stepCursor2", (stepCursor) + " " + steps.size());
        readInstruction();
    }

    private void readInstruction() {
        String text = steps.get(stepCursor).getInstruction();
        Log.d("stepCursorInstruction", (text == null) ? "null": text);
//        Log.d("stepCursorInstruction", stepCursorInstruction);
    }

    public void resetCursor() {
        this.stepCursor = 0;
        Log.d("resetCursor", "This is reset cursor");
    }
    public int getStepCursor() {
        return stepCursor;
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
}
