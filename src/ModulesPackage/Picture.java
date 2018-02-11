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

    public Picture(){

    }

    public Picture(String pictureId, String pictureUrl, Boolean isTested) {
        this.pictureId = pictureId;
        this.pictureUrl = pictureUrl;
        this.isTested = isTested;
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

    public Boolean getTested() {
        return isTested;
    }

    public void setTested(Boolean tested) {
        isTested = tested;
    }
}
