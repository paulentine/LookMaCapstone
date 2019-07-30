
package com.paulentine.android.capstone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ExtendedIngredient() {}

    public ExtendedIngredient(String name, Integer amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}
