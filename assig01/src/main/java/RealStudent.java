/**
 * @author Wenbo Zhang
 * @since java version "15.0.2"
 */
public class RealStudent implements Student{
    private String StuID;
    private String StuName;
    private double score1;
    private double score2;
    private double score3;
    private double score_total;


    /**
     * This is a constructor, Used to initialize all elements
     * @param StuID student ID - String
     * @param StuName student name - String
     * @param score1 first score - double
     * @param score2 second score - double
     * @param score3 third score - double
     */
    public RealStudent(String StuID,String StuName,double score1,double score2,double score3) {
        this.StuName = StuName;
        this.StuID = StuID;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score_total = score1 + score2 + score3;
    }


    /**
     * This is the default constructor
     * This constructor will initialize all numbers into zero and point all strings to null
     */
    public RealStudent() {
        this.StuName = null;
        this.StuID = null;
        this.score1 = 0.0;
        this.score2 = 0.0;
        this.score3 = 0.0;
        this.score_total = 0.0;
    }

    /**
     * Getter, get the student name
     * @return student name - String
     */
    @Override
    public String getStuName() {
        return StuName;
    }

    /**
     * Getter, get the student ID
     * @return student ID - String
     */
    @Override
    public String getStuID() {
        return StuID;
    }

    /**
     * Getter, get the first score
     * @return score1 - double
     */
    @Override
    public double getScore1() {
        return score1;
    }

    /**
     * Getter, get the second score
     * @return score2 - double
     */
    @Override
    public double getScore2() {
        return score2;
    }

    /**
     * Getter, get the third score
     * @return score3 - double
     */
    @Override
    public double getScore3() {
        return score3;
    }

    /**
     * Getter, get the total score
     * @return total_score - double
     */
    @Override
    public double getTotalScore() {
        return score_total;
    }

    /**
     * Setter, set the student name
     * @param name Student name
     */
    @Override
    public void setStuName(String name) {
        this.StuName = name;
    }

    /**
     * Setter, set the student ID
     * @param StuID Student ID
     */
    @Override
    public void setStuID(String StuID) {
        this.StuID = StuID;
    }

    /**
     * Setter, set the score1
     * @param score score1
     */
    @Override
    public void setScore1(double score) {
        this.score1 = score;
        this.score_total = this.score1+this.score2+this.score3;
    }

    /**
     * Setter, set the score2
     * @param score score2
     */
    @Override
    public void setScore2(double score) {
        this.score2 = score;
        this.score_total = this.score1+this.score2+this.score3;
    }

    /**
     * Setter, set the score3
     * @param score score3
     */
    @Override
    public void setScore3(double score) {
        this.score3 = score;
        this.score_total = this.score1+this.score2+this.score3;
    }
}
