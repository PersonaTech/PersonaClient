package ModulesPackage;

import java.io.Serializable;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */
public class PersonDrawingTest extends Test implements Serializable {

    private static final long serialVersionUID = 1120L;

    // ======================  Quantitative variables =========================

    private int personSizePercentageSlider;
    private int drawingSizePercentageSlider;
    private int proportionBetweenElementsSlider;

    private Boolean headIsExist;
    private Boolean LegsIsExist;
    private Boolean ArmsIsExist;


    private Boolean eyesIsExist;
    private Boolean mouthIsExist;
    private Boolean hairIsExist;

    private int numberOfClothing;


    // ======================  Qualitative variables ===========================

    private String diagnosticFreeText;


    // ======================  Constructors and Setters & Getters ===============

    public PersonDrawingTest() {
    }

    public PersonDrawingTest(String testId, String socialWorkerId, String childId, String pictureId,Picture refToPicture,
                             int personSizePercentageSlider, int drawingSizePercentageSlider, int proportionBetweenElementsSlider,
                             Boolean headIsExist, Boolean legsIsExist, Boolean armsIsExist, Boolean eyesIsExist,
                             Boolean mouthIsExist, Boolean hairIsExist, int numberOfClothing, String diagnosticFreeText) {
        super(testId, socialWorkerId, childId, pictureId,refToPicture);
        this.personSizePercentageSlider = personSizePercentageSlider;
        this.drawingSizePercentageSlider = drawingSizePercentageSlider;
        this.proportionBetweenElementsSlider = proportionBetweenElementsSlider;
        this.headIsExist = headIsExist;
        LegsIsExist = legsIsExist;
        ArmsIsExist = armsIsExist;
        this.eyesIsExist = eyesIsExist;
        this.mouthIsExist = mouthIsExist;
        this.hairIsExist = hairIsExist;
        this.numberOfClothing = numberOfClothing;
        this.diagnosticFreeText = diagnosticFreeText;
    }

    // ======================  Override methods ===============


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPersonSizePercentageSlider() {
        return personSizePercentageSlider;
    }

    public void setPersonSizePercentageSlider(int personSizePercentageSlider) {
        this.personSizePercentageSlider = personSizePercentageSlider;
    }

    public int getDrawingSizePercentageSlider() {
        return drawingSizePercentageSlider;
    }

    public void setDrawingSizePercentageSlider(int drawingSizePercentageSlider) {
        this.drawingSizePercentageSlider = drawingSizePercentageSlider;
    }

    public int getProportionBetweenElementsSlider() {
        return proportionBetweenElementsSlider;
    }

    public void setProportionBetweenElementsSlider(int proportionBetweenElementsSlider) {
        this.proportionBetweenElementsSlider = proportionBetweenElementsSlider;
    }

    public Boolean getHeadIsExist() {
        return headIsExist;
    }

    public void setHeadIsExist(Boolean headIsExist) {
        this.headIsExist = headIsExist;
    }

    public Boolean getLegsIsExist() {
        return LegsIsExist;
    }

    public void setLegsIsExist(Boolean legsIsExist) {
        LegsIsExist = legsIsExist;
    }

    public Boolean getArmsIsExist() {
        return ArmsIsExist;
    }

    public void setArmsIsExist(Boolean armsIsExist) {
        ArmsIsExist = armsIsExist;
    }

    public Boolean getEyesIsExist() {
        return eyesIsExist;
    }

    public void setEyesIsExist(Boolean eyesIsExist) {
        this.eyesIsExist = eyesIsExist;
    }

    public Boolean getMouthIsExist() {
        return mouthIsExist;
    }

    public void setMouthIsExist(Boolean mouthIsExist) {
        this.mouthIsExist = mouthIsExist;
    }

    public Boolean getHairIsExist() {
        return hairIsExist;
    }

    public void setHairIsExist(Boolean hairIsExist) {
        this.hairIsExist = hairIsExist;
    }

    public int getNumberOfClothing() {
        return numberOfClothing;
    }

    public void setNumberOfClothing(int numberOfClothing) {
        this.numberOfClothing = numberOfClothing;
    }

    public String getDiagnosticFreeText() {
        return diagnosticFreeText;
    }

    public void setDiagnosticFreeText(String diagnosticFreeText) {
        this.diagnosticFreeText = diagnosticFreeText;
    }

    @Override
    public String toString() {
        return super.toString() + " PersonDrawingTest{" +
                "personSizePercentageSlider=" + personSizePercentageSlider +
                ", drawingSizePercentageSlider=" + drawingSizePercentageSlider +
                ", proportionBetweenElementsSlider=" + proportionBetweenElementsSlider +
                ", headIsExist=" + headIsExist +
                ", LegsIsExist=" + LegsIsExist +
                ", ArmsIsExist=" + ArmsIsExist +
                ", eyesIsExist=" + eyesIsExist +
                ", mouthIsExist=" + mouthIsExist +
                ", hairIsExist=" + hairIsExist +
                ", numberOfClothing=" + numberOfClothing +
                ", diagnosticFreeText='" + diagnosticFreeText + '\'' +
                '}';
    }
}
