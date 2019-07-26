
package com.paulentine.android.capstone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("instruction")
    @Expose
    private String instruction;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Step() {}

    public Step(int number, String instruction) {
        this.number = number;
        this.instruction = instruction;
    }
}
