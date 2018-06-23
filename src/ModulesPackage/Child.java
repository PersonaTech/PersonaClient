package ModulesPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benshmuel on 03/12/2017.
 */
public class Child implements Serializable {
    private static final long serialVersionUID = 1313L;

    private String kindergarten;
    private String childName;
    private int childAge;
    private String childID;
    private String county;

    private Map<String,Picture> pictures;

    public Child() {
    }

    public Child(String kindergarten, String childName, int childAge , String childID , String county) {
        this.kindergarten = kindergarten;
        this.childName = childName;
        this.childAge = childAge;
        this.childID = childID;
        this.county = county;
        this.pictures = new HashMap<>();
    }

    public String getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(String kindergarten) {
        this.kindergarten = kindergarten;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public String getChildID() {
        return childID;
    }

    public void setChildID(String childID) {
        this.childID = childID;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Map<String,Picture> getPictures() {
        return pictures;
    }

    public void setPictures( Map<String,Picture> pictures) {
        this.pictures = pictures;
    }

    public List<String> getPicIdOfChild(String type){



        List<String> s = new ArrayList<>();

        for (Map.Entry<String,Picture> entry : pictures.entrySet()){

            System.out.println("id is ----> " + entry.getValue().getPictureId());
            System.out.println("type is ----> " + entry.getValue().getType());
            System.out.println("tested is ----> " + entry.getValue().getIsTested());
            System.out.println("url is ----> " + entry.getValue().getPictureUrl());

            if(type.equals(entry.getValue().getType().toLowerCase()) && !entry.getValue().getIsTested()) {

                s.add(entry.getValue().getPictureId());

                System.out.println("same type -----> added to list ! ");

            }

        }
        return s;
    }



    public String getPicUrlByPicId(String pid){

        for (Map.Entry<String,Picture> entry : pictures.entrySet()){

            if(entry.getValue().getPictureId().equals(pid))

                return entry.getValue().getPictureUrl();

        }
        return null;

    }

    public Picture getPicByPicId(String pid){

        for (Map.Entry<String,Picture> entry : pictures.entrySet()){

            if(entry.getValue().getPictureId().equals(pid))

                return entry.getValue();

        }
        return null;

    }

    public String toString(){

        return getClass().getName() + " kindergarten name = " + this.getKindergarten() + " child name = "
                + this.getChildName() + " child id = " + this.getChildID() + " age = " + this.getChildAge()
                + " County = " + this.getCounty();

    }



}
