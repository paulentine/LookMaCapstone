
package com.paulentine.android.capstone.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnalyzedInstruction {

    @SerializedName("steps")
    @Expose
    private List<Step> steps = new ArrayList<Step>();

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}
