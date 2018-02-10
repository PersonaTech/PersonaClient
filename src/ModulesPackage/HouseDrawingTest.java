package ModulesPackage;

import java.io.Serializable;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */



public class HouseDrawingTest extends Test implements Serializable {

    private static final long serialVersionUID = 1119L;


    // ======================  Quantitative variables =========================

    private int houseSizePercentageSlider;
    private int drawingSizePercentageSlider;
    private int proportionBetweenElementsSlider;
    private int referenceToDoorWindowsSlider;
    private int referenceToRoofSlider;
    private String yesNoWall;


    // ======================  Qualitative variables ===========================

    private String diagnosticFreeText;


    // ======================  Constructors and Setters & Getters ===============

    public HouseDrawingTest() {

    }

    public HouseDrawingTest(String testId, String socialWorkerId, String childId, String pictureId,
                            int houseSizePercentageSlider, int drawingSizePercentageSlider, int proportionBetweenElementsSlider,
                            int referenceToDoorWindowsSlider, int referenceToRoofSlider, String yesNoWall,
                            String diagnosticFreeText) {
        super(testId, socialWorkerId, childId, pictureId);
        this.houseSizePercentageSlider = houseSizePercentageSlider;
        this.drawingSizePercentageSlider = drawingSizePercentageSlider;
        this.proportionBetweenElementsSlider = proportionBetweenElementsSlider;
        this.referenceToDoorWindowsSlider = referenceToDoorWindowsSlider;
        this.referenceToRoofSlider = referenceToRoofSlider;
        this.yesNoWall = yesNoWall;
        this.diagnosticFreeText = diagnosticFreeText;
    }

    public int getHouseSizePercentageSlider() {
        return houseSizePercentageSlider;
    }

    public void setHouseSizePercentageSlider(int houseSizePercentageSlider) {
        this.houseSizePercentageSlider = houseSizePercentageSlider;
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

    public int getReferenceToDoorWindowsSlider() {
        return referenceToDoorWindowsSlider;
    }

    public void setReferenceToDoorWindowsSlider(int referenceToDoorWindowsSlider) {
        this.referenceToDoorWindowsSlider = referenceToDoorWindowsSlider;
    }

    public int getReferenceToRoofSlider() {
        return referenceToRoofSlider;
    }

    public void setReferenceToRoofSlider(int referenceToRoofSlider) {
        this.referenceToRoofSlider = referenceToRoofSlider;
    }

    public String getYesNoWall() {
        return yesNoWall;
    }

    public void setYesNoWall(String yesNoWall) {
        this.yesNoWall = yesNoWall;
    }

    public String getDiagnosticFreeText() {
        return diagnosticFreeText;
    }

    public void setDiagnosticFreeText(String diagnosticFreeText) {
        this.diagnosticFreeText = diagnosticFreeText;
    }

    @Override
    public String toString() {
        return super.toString() +  getClass().getName() + " - HouseDrawingTest{" +
                "houseSizePercentageSlider=" + houseSizePercentageSlider +
                ", drawingSizePercentageSlider=" + drawingSizePercentageSlider +
                ", proportionBetweenElementsSlider=" + proportionBetweenElementsSlider +
                ", referenceToDoorWindowsSlider=" + referenceToDoorWindowsSlider +
                ", referenceToRoofSlider=" + referenceToRoofSlider +
                ", yesNoWall='" + yesNoWall + '\'' +
                ", diagnosticFreeText='" + diagnosticFreeText + '\'' +
                '}';
    }

    // ======================  Override methods ===============



}
