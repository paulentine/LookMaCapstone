
package com.paulentine.android.capstone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Step {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("step")
    @Expose
    private String step;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Step() {}

    public Step(int number, String step) {
        this.number = number;
        this.step = step;
    }
}
