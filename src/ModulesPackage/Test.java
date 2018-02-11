package ModulesPackage;

import java.io.Serializable;

/**
 * Created by itamarfredavrahami on 09/12/2017.
 */

public class Test implements Serializable{

    private static final long serialVersionUID = 1129L;

    private String testId;
    private String socialWorkerId;
    private String childId;
    private String pictureId;

    private Picture refToPicture;



    public Test() {

    }

    public Test(String testId, String socialWorkerId, String childId, String pictureId, Picture refToPicture) {
        this.testId = testId;
        this.socialWorkerId = socialWorkerId;
        this.childId = childId;
        this.pictureId = pictureId;
        this.refToPicture = refToPicture;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getSocialWorkerId() {
        return socialWorkerId;
    }

    public void setSocialWorkerId(String socialWorkerId) {
        this.socialWorkerId = socialWorkerId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public Picture getRefToPicture() {
        return refToPicture;
    }

    public void setRefToPicture(Picture refToPicture) {
        this.refToPicture = refToPicture;
    }

    @Override
    public String toString() {

        return getClass().getName() + ": testId = " + testId
                + " socialWorkerId = " + socialWorkerId
                + " childId = " + childId
                + " pictureId = " + pictureId + '\n'
                ;
    }
}
