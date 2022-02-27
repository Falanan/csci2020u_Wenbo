/**
 * @author Wenbo Zhang
 * @since java version "15.0.2"
 */

public class ProxyStudent implements Student {

    private RealStudent realStudent;

    /**
     * Constructor, call the realStudent constructor
     * @param StuID student ID - String
     * @param StuName student name - String
     * @param score1 first score - double
     * @param score2 second score - double
     * @param score3 third score - double
     */
    public ProxyStudent(String StuID,String StuName,double score1,double score2,double score3){
        realStudent = new RealStudent(StuID, StuName, score1, score2, score3);
    }

    /**
     * Default constructor
     */
    public ProxyStudent(){
        realStudent = new RealStudent();
    }


    /**
     * Getter, if the realStudent class not initialized, init the variable
     * then call the same function in realStudent
     * @return Student name - String
     */
    @Override
    public String getStuName() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getStuName();
    }

    /**
     * Getter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @return Student ID - String
     */
    @Override
    public String getStuID() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getStuID();
    }

    /**
     * Getter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @return score1 - double
     */
    @Override
    public double getScore1() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getScore1();
    }

    /**
     * Getter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @return score2 - double
     */
    @Override
    public double getScore2() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getScore2();
    }

    /**
     * Getter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @return score3 - double
     */
    @Override
    public double getScore3() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getScore3();
    }

    /**
     * Getter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @return total score - double
     */
    @Override
    public double getTotalScore() {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        return realStudent.getTotalScore();
    }

    /**
     * Setter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @param name student name - String
     */
    @Override
    public void setStuName(String name) {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        realStudent.setStuName(name);
    }

    /**
     * Setter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @param StuID Student ID - Stringsa
     */
    @Override
    public void setStuID(String StuID) {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        realStudent.setStuID(StuID);
    }

    /**
     * Setter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @param score score1 - double
     */
    @Override
    public void setScore1(double score) {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        realStudent.setScore1(score);
    }

    /**
     * Setter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @param score score - double
     */
    @Override
    public void setScore2(double score) {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        realStudent.setScore2(score);
    }

    /**
     * Setter, if the realStudent class not initialized, init the variable,
     * then call the same function in realStudent
     * @param score score - double
     */
    @Override
    public void setScore3(double score) {
        if (realStudent == null){
            realStudent = new RealStudent();
        }
        realStudent.setScore3(score);
    }
}
