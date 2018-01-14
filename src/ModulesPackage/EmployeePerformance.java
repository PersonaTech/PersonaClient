package ModulesPackage;

/**
 * Created by itamarfredavrahami on 14/01/2018.
 */
public class EmployeePerformance {
    private static final long serialVersionUID = 1997L;

    private String EmpUid ;

    private String EmpName ;

    private int TreeDiagCounter;

    private int PersonDiagCounter;

    private int HouseDiagCounter;

    public EmployeePerformance() {

    }

    public EmployeePerformance(String empUid, String empName, int treeDiagCounter, int personDiagCounter, int houseDiagCounter) {
        EmpUid = empUid;
        EmpName = empName;
        TreeDiagCounter = treeDiagCounter;
        PersonDiagCounter = personDiagCounter;
        HouseDiagCounter = houseDiagCounter;
    }
    public String getEmpUid() {
        return EmpUid;
    }

    public void setEmpUid(String empUid) {
        EmpUid = empUid;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public int getTreeDiagCounter() {
        return TreeDiagCounter;
    }

    public void setTreeDiagCounter(int treeDiagCounter) {
        TreeDiagCounter = treeDiagCounter;
    }

    public int getPersonDiagCounter() {
        return PersonDiagCounter;
    }

    public void setPersonDiagCounter(int personDiagCounter) {
        PersonDiagCounter = personDiagCounter;
    }

    public int getHouseDiagCounter() {
        return HouseDiagCounter;
    }

    public void setHouseDiagCounter(int houseDiagCounter) {
        HouseDiagCounter = houseDiagCounter;
    }


    @Override
    public String toString() {
        return "EmployeePerformance{" +
                "EmpUid='" + EmpUid + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", TreeDiagCounter=" + TreeDiagCounter +
                ", PersonDiagCounter=" + PersonDiagCounter +
                ", HouseDiagCounter=" + HouseDiagCounter +
                '}';

    }

}
