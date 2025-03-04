/* 
Using the following array of students make 2 different methods that either assigns students into a seating 2 array as it reads it and a randomSeating array that randomly assigns students to different seats. 
3 rows
4 columns
 */

public class StudentSeating {

    String[] name = {"Konrad", "Elijah", "Steven", "GianLuca", "Matthew", "Jacob", "Christopher", "Cole", "Max", "Jordan", "Logan", "Hunter"};

    int rows = 3;
    int cols = 4;

    public String[][] assignSeating(String[] names, int rows, int cols) {
        String[][] seating = new String[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seating[i][j] = names[index++];
            }
        }
        return seating;
    }

    public String[][] randomseating(String[] names, int rows, int cols) {
        String[][] randseating = new String[rows][cols];
        int totalstudnet = names.length;
        String[] tempname = names;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomstudent = (int) (Math.random() * totalstudnet);
                randseating[i][j] = tempname[randomstudent];
                tempname[randomstudent] = tempname[totalstudnet - 1];
                totalstudnet--;
            }
        }
        return randseating;
    }
}
