//
//package com.paulentine.android.capstone.model;
//
//import java.util.ArrayList;
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class RecipeIdeally {
//
//    @SerializedName("vegetarian")
//    @Expose
//    private Boolean vegetarian;
//    @SerializedName("vegan")
//    @Expose
//    private Boolean vegan;
//    @SerializedName("glutenFree")
//    @Expose
//    private Boolean glutenFree;
//    @SerializedName("dairyFree")
//    @Expose
//    private Boolean dairyFree;
//    @SerializedName("veryHealthy")
//    @Expose
//    private Boolean veryHealthy;
//    @SerializedName("cheap")
//    @Expose
//    private Boolean cheap;
//    @SerializedName("veryPopular")
//    @Expose
//    private Boolean veryPopular;
//    @SerializedName("sustainable")
//    @Expose
//    private Boolean sustainable;
//    @SerializedName("ketogenic")
//    @Expose
//    private Boolean ketogenic;
//    @SerializedName("whole30")
//    @Expose
//    private Boolean whole30;
//    @SerializedName("preparationMinutes")
//    @Expose
//    private Integer preparationMinutes;
//    @SerializedName("cookingMinutes")
//    @Expose
//    private Integer cookingMinutes;
//    @SerializedName("sourceUrl")
//    @Expose
//    private String sourceUrl;
//    @SerializedName("sourceName")
//    @Expose
//    private String sourceName;
//    @SerializedName("extendedIngredients")
//    @Expose
//    private List<ExtendedIngredient> extendedIngredients = new ArrayList<ExtendedIngredient>();
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("readyInMinutes")
//    @Expose
//    private Integer readyInMinutes;
//    @SerializedName("servings")
//    @Expose
//    private Integer servings;
//    @SerializedName("image")
//    @Expose
//    private String image;
//    @SerializedName("analyzedInstructions")
//    @Expose
//    private List<AnalyzedInstruction> analyzedInstructions = new ArrayList<AnalyzedInstruction>();
//
//    public RecipeIdeally() {
//        //empty constructor needed
//    }
//
//    public RecipeIdeally(Boolean vegetarian, Boolean vegan, Boolean glutenFree, Boolean dairyFree,
//                  Boolean veryHealthy, Boolean cheap, Boolean veryPopular, Boolean sustainable,
//                  Boolean ketogenic, Boolean whole30, Integer preparationMinutes,
//                  Integer cookingMinutes, String sourceUrl, String sourceName,
//                  List<ExtendedIngredient> extendedIngredients,
//                  Integer id, String title, Integer readyInMinutes, Integer servings, String image,
//                  List<AnalyzedInstruction> analyzedInstructions) {
//        this.vegetarian = vegetarian;
//        this.vegan = vegan;
//        this.glutenFree = glutenFree;
//        this.dairyFree = dairyFree;
//        this.veryHealthy = veryHealthy;
//        this.cheap = cheap;
//        this.veryPopular = veryPopular;
//        this.sustainable = sustainable;
//        this.ketogenic = ketogenic;
//        this.whole30 = whole30;
//        this.preparationMinutes = preparationMinutes;
//        this.cookingMinutes = cookingMinutes;
//        this.sourceUrl = sourceUrl;
//        this.sourceName = sourceName;
//        this.extendedIngredients = extendedIngredients;
//        this.id = id;
//        this.title = title;
//        this.readyInMinutes = readyInMinutes;
//        this.servings = servings;
//        this.image = image;
//        this.analyzedInstructions = analyzedInstructions;
//    }
//
//    public Boolean getVegetarian() {
//        return vegetarian;
//    }
//
//    public void setVegetarian(Boolean vegetarian) {
//        this.vegetarian = vegetarian;
//    }
//
//    public Boolean getVegan() {
//        return vegan;
//    }
//
//    public void setVegan(Boolean vegan) {
//        this.vegan = vegan;
//    }
//
//    public Boolean getGlutenFree() {
//        return glutenFree;
//    }
//
//    public void setGlutenFree(Boolean glutenFree) {
//        this.glutenFree = glutenFree;
//    }
//
//    public Boolean getDairyFree() {
//        return dairyFree;
//    }
//
//    public void setDairyFree(Boolean dairyFree) {
//        this.dairyFree = dairyFree;
//    }
//
//    public Boolean getVeryHealthy() {
//        return veryHealthy;
//    }
//
//    public void setVeryHealthy(Boolean veryHealthy) {
//        this.veryHealthy = veryHealthy;
//    }
//
//    public Boolean getCheap() {
//        return cheap;
//    }
//
//    public void setCheap(Boolean cheap) {
//        this.cheap = cheap;
//    }
//
//    public Boolean getVeryPopular() {
//        return veryPopular;
//    }
//
//    public void setVeryPopular(Boolean veryPopular) {
//        this.veryPopular = veryPopular;
//    }
//
//    public Boolean getSustainable() {
//        return sustainable;
//    }
//
//    public void setSustainable(Boolean sustainable) {
//        this.sustainable = sustainable;
//    }
//
//    public Boolean getKetogenic() {
//        return ketogenic;
//    }
//
//    public void setKetogenic(Boolean ketogenic) {
//        this.ketogenic = ketogenic;
//    }
//
//    public Boolean getWhole30() {
//        return whole30;
//    }
//
//    public void setWhole30(Boolean whole30) {
//        this.whole30 = whole30;
//    }
//
//    public Integer getPreparationMinutes() {
//        return preparationMinutes;
//    }
//
//    public void setPreparationMinutes(Integer preparationMinutes) {
//        this.preparationMinutes = preparationMinutes;
//    }
//
//    public Integer getCookingMinutes() {
//        return cookingMinutes;
//    }
//
//    public void setCookingMinutes(Integer cookingMinutes) {
//        this.cookingMinutes = cookingMinutes;
//    }
//
//    public String getSourceUrl() {
//        return sourceUrl;
//    }
//
//    public void setSourceUrl(String sourceUrl) {
//        this.sourceUrl = sourceUrl;
//    }
//
//    public String getSourceName() {
//        return sourceName;
//    }
//
//    public void setSourceName(String sourceName) {
//        this.sourceName = sourceName;
//    }
//
//    public List<ExtendedIngredient> getExtendedIngredients() {
//        return extendedIngredients;
//    }
//
//    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
//        this.extendedIngredients = extendedIngredients;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Integer getReadyInMinutes() {
//        return readyInMinutes;
//    }
//
//    public void setReadyInMinutes(Integer readyInMinutes) {
//        this.readyInMinutes = readyInMinutes;
//    }
//
//    public Integer getServings() {
//        return servings;
//    }
//
//    public void setServings(Integer servings) {
//        this.servings = servings;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public List<AnalyzedInstruction> getAnalyzedInstructions() {
//        return analyzedInstructions;
//    }
//
//    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) {
//        this.analyzedInstructions = analyzedInstructions;
//    }
//}
//
//
//
//
