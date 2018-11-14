import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main {


    static File formFile;
    static File variableFile;
    static File outputFile;
    static final String usage = "Usage: form file, variable file, output file";


    public static void main(String[] args) throws FileNotFoundException{
        System.out.println(Arrays.toString(args));
        if (handleArguments(args))
            regExReplace();
    }

    static boolean handleArguments(String[] args) {
        // Check for correct number of arguments
        if (args.length != 3) {
            System.out.println("Wrong number of command line arguments.");
            System.out.println(usage);
            return false;
        }

        // Open the form file
        formFile = new File(args[0]);
        if (!formFile.exists()) {
            System.out.println("The file " + args[0] + " cannot be opened for input.");
            return false;
        }

        // Open the variable file
        variableFile = new File(args[1]);
        if (!variableFile.exists()) {
            System.out.println("The file " + args[1] + " cannot be opened for input.");
            return false;
        }


        // Open the output file
        outputFile = new File(args[2]);
        try{
            if (outputFile.createNewFile()){
                System.out.println("The file " + args[2] + " cannot be opened for output.");
                return false;
            }
        }catch(IOException e){
            System.out.println("face");
        }


        return true;
    }



    static void regExReplace() throws FileNotFoundException{
        //HashMap<String, String> variableHash = new HashMap<>();
        Scanner variableInput = new Scanner(variableFile);
        PrintStream outStream = new PrintStream(outputFile);
        Scanner formInput = new Scanner(formFile);
        String fullForm = new String();
        while (formInput.hasNextLine()) {

            String temp = formInput.nextLine();
            fullForm = fullForm + temp + "\n";

        }
        System.out.println(fullForm);
        while (variableInput.hasNext()){
            String key = variableInput.next();
            //key = key + variableInput.next();
            //key = key + ")";
            System.out.println(key);
            String value = variableInput.next();
            System.out.println(value);
            fullForm.replaceAll(key, value);

        }
        System.out.println(fullForm);
        outStream.println(fullForm);

    }


}
