package ModulesPackage;

import java.io.Serializable;

/**
 * Created by itamarfredavrahami on 11/02/2018.
 */
public class Picture implements Serializable{

    private static final long serialVersionUID = 19465L;

    private String pictureId;
    private String pictureUrl;
    private Boolean isTested;
    private String type;


    public Picture(){

    }

    public Picture(String pictureId, String pictureUrl, Boolean isTested , String type) {
        this.pictureId = pictureId;
        this.pictureUrl = pictureUrl;
        this.isTested = isTested;
        this.type = type;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Boolean getIsTested() {
        return isTested;
    }

    public void setIsTested(Boolean isTested) {
        this.isTested = isTested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
