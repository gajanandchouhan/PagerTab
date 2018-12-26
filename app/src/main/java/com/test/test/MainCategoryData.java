package com.test.test;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class MainCategoryData implements Serializable{

    private String title;
    private List<SubCategoryData> subCategoryDataList;

    public MainCategoryData(String title, List<SubCategoryData> subCategoryDataList) {
        this.title = title;
        this.subCategoryDataList = subCategoryDataList;
    }

    public String getTitle() {
        return title;
    }

    public List<SubCategoryData> getSubCategoryDataList() {
        return subCategoryDataList;
    }


}
class SubCategoryData {

    private String title;

    public SubCategoryData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}