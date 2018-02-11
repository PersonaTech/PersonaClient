package ModulesPackage;

import java.io.Serializable;

/**
 * Created by itamarfredavrahami on 09/12/2017.
 */
public class TreeDrawingTest extends Test implements Serializable{

    private static final long serialVersionUID = 1149L;

    // ======================  Quantitative variables =========================

    private int treeSizePercentage;
    private int drawingSizePercentage;
    private int proportionBetweenElements;
    private int referenceToLeafs;
    private int sidewaysMovement;
    private String treeLocation;

    /*
        The treeLocation can be one of th following properties:
         - CENTER
         - RIGHT
         - LEFT
         - TOP
         - BOTTOM
     */

    // ======================  Qualitative variables ===========================

    private String diagnosticFreeText;

    // ======================  Constructors and Setters & Getters ===============


    public TreeDrawingTest() {

    }

    public TreeDrawingTest(String testId, String socialWorkerId, String childId, String pictureId,Picture refToPicture,
                           int treeSizePercentage, int drawingSizePercentage, int proportionBetweenElements,
                           int referenceToLeafs, int sidewaysMovement, String treeLocation, String diagnosticFreeText) {
        super(testId, socialWorkerId, childId, pictureId,refToPicture);
        this.treeSizePercentage = treeSizePercentage;
        this.drawingSizePercentage = drawingSizePercentage;
        this.proportionBetweenElements = proportionBetweenElements;
        this.referenceToLeafs = referenceToLeafs;
        this.sidewaysMovement = sidewaysMovement;
        this.treeLocation = treeLocation;
        this.diagnosticFreeText = diagnosticFreeText;
    }


    public int getTreeSizePercentage() {
        return treeSizePercentage;
    }

    public void setTreeSizePercentage(int treeSizePercentage) {
        this.treeSizePercentage = treeSizePercentage;
    }

    public int getDrawingSizePercentage() {
        return drawingSizePercentage;
    }

    public void setDrawingSizePercentage(int drawingSizePercentage) {
        this.drawingSizePercentage = drawingSizePercentage;
    }

    public int getProportionBetweenElements() {
        return proportionBetweenElements;
    }

    public void setProportionBetweenElements(int proportionBetweenElements) {
        this.proportionBetweenElements = proportionBetweenElements;
    }

    public int getReferenceToLeafs() {
        return referenceToLeafs;
    }

    public void setReferenceToLeafs(int referenceToLeafs) {
        this.referenceToLeafs = referenceToLeafs;
    }

    public int getSidewaysMovement() {
        return sidewaysMovement;
    }

    public void setSidewaysMovement(int sidewaysMovement) {
        this.sidewaysMovement = sidewaysMovement;
    }

    public String getTreeLocation() {
        return treeLocation;
    }

    public void setTreeLocation(String treeLocation) {
        this.treeLocation = treeLocation;
    }

    public String getDiagnosticFreeText() {
        return diagnosticFreeText;
    }

    public void setDiagnosticFreeText(String diagnosticFreeText) {
        this.diagnosticFreeText = diagnosticFreeText;
    }


    // ======================  Override methods ===============

    public String toString(){


        return  "================================================================================================\n"+
                super.toString() +  getClass().getName() + ": treeSizePercentage = " + treeSizePercentage
                + " drawingSizePercentage = " + drawingSizePercentage
                + " proportionBetweenElements = " + proportionBetweenElements
                + " referenceToLeafs = " + referenceToLeafs
                + " sidewaysMovement = " + sidewaysMovement
                + " treeLocation = " + treeLocation + "\n"
                + "diagnosticFreeText = " + diagnosticFreeText + '\n'
                +"================================================================================================\n"
                ;


    }











}
