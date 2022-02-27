import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program is build under Windows environment using IntelliJ IDEA
 * All functions tested normal under Windows with Java 15.0.2 and gradle 6.3
 * @author Wenbo Zhang
 * @since java version "15.0.2"
 */
public class StuManagement {

    static Scanner scan = new Scanner(System.in);

    /**
     * This is the main function, it provides a command line menu
     * It will call different function according the user input
     *
     * This function does not read the file automatically,
     * you need to read the file manually each time you use it.
     *
     * throw the exception to JVM
     *
     * @param args system arguments
     * @throws IOException handle the exception thrown by read file and write file function
     */
    public static void main(String[] args) throws IOException {
        ArrayList<ProxyStudent> array = new ArrayList<ProxyStudent>();
        String choice;
        while(true) {
            System.out.println("-------------Student Management System--------------");
            System.out.println("--------Author : Wenbo Zhang---------");
            System.out.println("! ! ! ! ! ! ! ! Please select 0 to read the information from the disk ! ! ! ! ! ! ! !");
            System.out.println("0.Real all information");
            System.out.println("1.Add student(and information)");
            System.out.println("2.Display all");
            System.out.println("3.Modify student information");
            System.out.println("4.Check student grades");
            System.out.println("5.Delete student");
            System.out.println("6.Sorted by total score in descending order");
            System.out.println("7.Sorted by academic number in descending order");
            System.out.println("8.Save & Quit");
            System.out.println("------------------------------------");
            System.out.print("Enter your selection: ");
            choice = scan.nextLine();
            switch(choice) {
                case "0": readStudent(array); break;
                case "1": addStudent(array); break;
                case "2": displayAll(array); break;
                case "3": changeStuIfm(array); break;
                case "4": findStuScore(array); break;
                case "5": deleteStu(array); break;
                case "6": descendingSort(array); break;
                case "7": descendingSort_ID(array); break;
                case "8": writer(array); scan.close(); System.exit(0); break;
                default : break;
            }
        }
    }


    /**
     * display all student in the list
     * if there is no student in the list, the function will print No information here
     * else it will display all students information
     * @param array student array stores all information - ArrayList
     */
    private static void displayAll(ArrayList<ProxyStudent> array) {
        if (array.size()==0) {
            System.out.println("No information here.");
            return;
        }
        System.out.println("------------Display all-------------");
        System.out.println("There are "+array.size()+"students!");
        System.out.println("Student ID\tName\tScore1\tScore2\tScore3\tTotal score");
        for (int i=0;i<array.size();i++) {
            ProxyStudent stu = array.get(i);
            System.out.println(stu.getStuID()+"\t"+stu.getStuName()+"\t"+stu.getScore1()+"\t"+
                    stu.getScore2()+"\t"+stu.getScore3()+"\t"+stu.getTotalScore());
        }
        System.out.println();
    }

    /**
     * This method is used to add student information and scores into the array
     * @param array student array - ArrayList
     */
    private static void addStudent(ArrayList<ProxyStudent> array) {
        System.out.println("--------------Register student information----------------");
        String stuId;
        while(true) {
            System.out.println("Enter Student ID: ");
            stuId = scan.nextLine();
            boolean flag = false;
            for(int i=0;i<array.size();i++) {
                ProxyStudent s = array.get(i);
                if(s.getStuID().equals(stuId)) {
                    flag = true;
                    break;
                }
            }
            if(flag == true) {
                System.out.println("The number you entered is occupied!!!");
            }else{
                break;
            }
        }
        System.out.println("Enter the name: ");
        String name = scan.nextLine();
        System.out.println("Enter Score1: ");
        Double score = scan.nextDouble();
        System.out.println("Enter Score2: ");
        Double score2 = scan.nextDouble();
        System.out.println("Enter Score3: ");
        Double score3 = scan.nextDouble();
        ProxyStudent s = new ProxyStudent();
        s.setStuID(stuId);
        s.setStuName(name);
        s.setScore1(score);
        s.setScore2(score2);
        s.setScore3(score3);
        array.add(s);
        System.out.println("Success!");
    }

    /**
     * This method is used to delete student information according teh studentID
     * @param array student array - ArrayList
     */
    private static void deleteStu(ArrayList<ProxyStudent> array) {
        System.out.println("------------Delete student--------------");
        String StuDelId = scan.nextLine();
        int position = -1;
        for (int i=0;i<array.size();i++) {
            ProxyStudent s = array.get(i);
            if (StuDelId.equals(s.getStuID())) {
                position = i;
            }
            if (position == -1) {
                System.out.println("The student number you entered does not exist, please check the input and try again!");
                scan.close();
                return;
            }else {
                array.remove(position);
                System.out.println("Successfully delete the student! \nDisplay all: ");
                displayAll(array);
            }
        }
    }

    /**
     * This method is used to modify student information according to the studentID
     * @param array student array - ArrayList
     */
    private static void changeStuIfm(ArrayList<ProxyStudent> array) {
        System.out.print("Please enter the student number to be changed:");
        String changeId = scan.nextLine();
        int position = -1;
        for(int i=0;i<array.size();i++) {
            ProxyStudent s = array.get(i);
            if(changeId.equals(s.getStuID())) {
                position = i;
            }
        }
        if (position == -1) {
            System.out.println("Student number does not exist, please check the input and try again!");
        }else {
            System.out.println("Enter student name: ");
            String name = scan.nextLine();
            System.out.println("Enter score1: ");
            Double score = scan.nextDouble();
            System.out.println("Enter score2: ");
            Double score2 = scan.nextDouble();
            System.out.println("Enter score3: ");
            Double score3 = scan.nextDouble();
            ProxyStudent s = new ProxyStudent();
            s.setStuName(name);
            s.setStuID(changeId);
            s.setScore1(score);
            s.setScore2(score2);
            s.setScore3(score3);
            array.set(position, s);
            System.out.println("Success! \nDisplay all Student: ");
            displayAll(array);
        }
    }

    /**
     * find the student information(and scores) according to student ID
     * @param array student array - ArrayList
     */
    private static void findStuScore(ArrayList<ProxyStudent> array) {
        System.out.print("Please enter the student number you are looking for: ");
        String findId = scan.nextLine();
        int position = -1;
        for(int i=0;i<array.size();i++) {
            ProxyStudent s = array.get(i);
            if(findId.equals(s.getStuID())) {
                position = i;
            }
        }
        if (position == -1) {
            System.out.println("Student ID does not exist, please check you input! ");
        }else{
            System.out.println("Student ID\tName\tScore1\tScore2\tScore3\tTotal score");
            ProxyStudent stu = array.get(position);
            System.out.print(stu.getStuID()+"\t"+stu.getStuName()+"\t"+stu.getScore1()+"\t"
                    +stu.getScore2()+"\t"+stu.getScore3()+"\t"+stu.getTotalScore());
        }
        System.out.println();
    }

    /**
     * sort total score in descending order, then call displayAll method to display all information
     * @param array student array - ArrayList
     */
    private static void descendingSort(ArrayList<ProxyStudent> array) {
        ProxyStudent temp;
        for (int i=0;i<array.size();i++) {
            for (int j=0;j<array.size()-i-1;j++) {
                ProxyStudent s1 = array.get(j);
                ProxyStudent s2 = array.get(j+1);
                if (s1.getTotalScore()>s2.getTotalScore()) {
                    temp = s1;
                    array.set(j, s2);
                    array.set(j+1, temp);
                }
            }
        }
        System.out.println("Display all: ");
        displayAll(array);
    }

    /**
     * write information to file
     * @param array student array
     * @throws IOException throw the IO exception to main function
     */
    private static void writer(ArrayList<ProxyStudent> array) throws IOException {
        FileWriter fr = new FileWriter("src/1.txt");
        BufferedWriter bfr = new BufferedWriter(fr);
        Integer x = array.size();
        bfr.write(x);;
        bfr.newLine();
        for (int i=0;i<array.size();i++) {
            ProxyStudent stu = array.get(i);
            bfr.write(stu.getStuID()+"|");
            bfr.write(stu.getStuName()+"|");
            Double a = stu.getScore1();
            String s = a.toString();
            bfr.write(s+"|");
            a = stu.getScore2();
            s = a.toString();
            bfr.write(s+"|");
            a = stu.getScore3();
            s = a.toString();
            bfr.write(s+"|");
            a = stu.getTotalScore();
            s = a.toString();
            bfr.write(s+"|");
            bfr.newLine();
        }
        bfr.close();
    }

    /**
     * read information from file
     * path: src/
     * @param array student array - ArrayList
     * @throws IOException throw IO exception to main function
     */
    private static void readStudent(ArrayList<ProxyStudent> array) throws IOException {
        FileReader fr = new FileReader("src/1.txt");
        BufferedReader bfr = new BufferedReader(fr);
        int a = bfr.read();
        if (a == 0) {
            System.out.println("No student information here! ");
        }else {
            String s = bfr.readLine();//skip the first line
            for (int i=0;i<a;i++) {
                ProxyStudent stu = new ProxyStudent();
                s = bfr.readLine();
                String[] sa = s.split("\\|");
                stu.setStuID(sa[0]);
                stu.setStuName(sa[1]);
                stu.setScore1(Double.parseDouble(sa[2]));
                stu.setScore2(Double.parseDouble(sa[3]));
                stu.setScore3(Double.parseDouble(sa[4]));
                array.add(stu);
            }
            System.out.println("Success!");
        }

        bfr.close();

    }

    /**
     * sort by student id in descending order
     * @param array
     */
    private static void descendingSort_ID(ArrayList<ProxyStudent> array) {
        ProxyStudent temp;
        for (int i=0;i<array.size();i++) {
            for (int j=0;j<array.size()-i-1;j++) {
                ProxyStudent s1 = array.get(j);
                ProxyStudent s2 = array.get(j+1);
                if ((Long.parseLong(s1.getStuID()))>(Long.parseLong(s2.getStuID()))) {
                    temp = s1;
                    array.set(j, s2);
                    array.set(j+1, temp);
                }
            }
        }
        System.out.println("Display all: ");
        displayAll(array);
    }


}
