import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

public class Application {

    static final String FILE_NAME = "File.txt";

    public static String ReadFile(String filename) {
        try
        {
            StringBuilder builder = new StringBuilder();
            Scanner in = new Scanner(new File(FILE_NAME));
            while(in.hasNext())
                builder.append(in.nextLine() + '\n');
            in.close();

            return builder.toString();
        }

        catch (FileNotFoundException ex)
        {
            return "File open error!";
        }
    }

    public static void ExecuteTasks(Text text) {
        Tasks.Task_01(text);
        Tasks.Task_02(text);
        Tasks.Task_03(text);
        Tasks.Task_04(text);
        Tasks.Task_05(text);
        Tasks.Task_06(text);
        Tasks.Task_07(text);
        Tasks.Task_08(text);
        Tasks.Task_09(text);
        Tasks.Task_10(text);
        Tasks.Task_11(text);
        Tasks.Task_12(text);
        Tasks.Task_13(text);
        Tasks.Task_14(text);
        Tasks.Task_15(text);
        Tasks.Task_16(text.getSenteses().get(3), 4, "str");
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.read(ReadFile(FILE_NAME));

        if (text.empty())
            throw new IllegalArgumentException("Error file read or file is empty");

       ExecuteTasks(text);
    }
}
