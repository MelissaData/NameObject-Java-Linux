import com.melissadata.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MelissaNameObjectLinuxJava {

  public static void main(String args[]) throws IOException {
    // Variables
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testName = arguments[1];
    String dataPath = arguments[2];

    RunAsConsole(license, testName, dataPath);
  }

  public static String[] ParseArguments(String[] args) {
    String license = "", testName = "", dataPath = "";
    List<String> argumentStrings = Arrays.asList("--license", "-l", "--name", "-n", "--dataPath", "-d");
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--license") || args[i].equals("-l")) {
        if (args[i + 1] != null) {
          license = args[i + 1];
        }
      }
      if (args[i].equals("--name") || args[i].equals("-n")) {
        if (args[i + 1] != null) {
          testName = args[i + 1];
          int nameLength = 2;
          while ((args.length - 1 >= i + nameLength) && (!argumentStrings.contains(args[i + nameLength]))) {
            testName += " " + args[i + nameLength];
            nameLength += 1;
          }
        }
      }
      if (args[i].equals("--dataPath") || args[i].equals("-d")) {
        if (args[i + 1] != null) {
          dataPath = args[i + 1];
        }
      }
    }
    return new String[] { license, testName, dataPath };

  }

  public static void RunAsConsole(String license, String testName, String dataPath) throws IOException {
    System.out.println("\n\n============ WELCOME TO MELISSA NAME OBJECT LINUX JAVA =============\n");
    NameObject nameObject = new NameObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    if (!nameObject.mdNameObj.GetInitializeErrorString().equals("No Error"))
      shouldContinueRunning = false;

    while (shouldContinueRunning) {
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      if (testName == null || testName.trim().isEmpty()) {
        System.out.println("\nFill in each value to see the Name Object results");
        System.out.print("Name:");

        dataContainer.Name = stdin.readLine();
      } else {
        dataContainer.Name = testName;
      }

      // Print user input
      System.out.println("\n============================== INPUTS ==============================\n");
      System.out.println("\t               Name: " + dataContainer.Name);

      // Execute Name Object
      nameObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
      System.out.println("\n============================== OUTPUT ==============================\n");
      System.out.println("\n\tName Object Information:");

      System.out.println("\t           Prefix: " + nameObject.mdNameObj.GetPrefix());
      System.out.println("\t       First Name: " + nameObject.mdNameObj.GetFirstName());
      System.out.println("\t      Middle Name: " + nameObject.mdNameObj.GetMiddleName());
      System.out.println("\t        Last Name: " + nameObject.mdNameObj.GetLastName());
      System.out.println("\t           Suffix: " + nameObject.mdNameObj.GetSuffix());
      System.out.println("\t           Gender: " + nameObject.mdNameObj.GetGender());
      System.out.println("\t       Salutation: " + nameObject.mdNameObj.GetSalutation());

      System.out.println("\t  Result Codes: " + dataContainer.ResultCodes);

      String[] rs = dataContainer.ResultCodes.split(",");
      for (String r : rs) {
        System.out.println("        " + r + ":"
            + nameObject.mdNameObj.GetResultCodeDescription(r, mdName.ResultCdDescOpt.ResultCodeDescriptionLong));
      }

      Boolean isValid = false;
      if (testName != null && !testName.trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

      while (!isValid) {
        System.out.println("\nTest another name? (Y/N)");
        String testAnotherResponse = stdin.readLine();

        if (testAnotherResponse != null && !testAnotherResponse.trim().isEmpty()) {
          testAnotherResponse = testAnotherResponse.toLowerCase();
          if (testAnotherResponse.equals("y")) {
            isValid = true;
          } else if (testAnotherResponse.equals("n")) {
            isValid = true;
            shouldContinueRunning = false;
          } else {
            System.out.println("Invalid Response, please respond 'Y' or 'N'");
          }
        }
      }
    }
    System.out.println("\n=============== THANK YOU FOR USING MELISSA JAVA OBJECT ============\n");

  }
}

class NameObject {
  // Path to Name Object data files (.dat, etc)
  String dataFilePath;

  // Create instance of Melissa Name Object
  mdName mdNameObj = new mdName();

  public NameObject(String license, String dataPath) {
    // Set license string and set path to data files (.dat, etc)
    mdNameObj.SetLicenseString(license);
    dataFilePath = dataPath;
    mdNameObj.SetPathToNameFiles(dataFilePath);

    // If you see a different date than expected, check your license string and
    // either download the new data files or use the Melissa Updater program to
    // update your data files.
    mdName.ProgramStatus pStatus = mdNameObj.InitializeDataFiles();

    if (pStatus != mdName.ProgramStatus.NoError) {
      // Problem during initialization
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    System.out.println("                DataBase Date: " + mdNameObj.GetDatabaseDate());
    System.out.println("              Expiration Date: " + mdNameObj.GetLicenseExpirationDate());

    /**
     * This number should match with the file properties of the Melissa Object
     * binary file.
     * If TEST appears with the build number, there may be a license key issue.
     */
    System.out.println("               Object Version: " + mdNameObj.GetBuildNumber());
    System.out.println();

  }

  // This will call the lookup function to process the input name as well as
  // generate the result codes
  public void ExecuteObjectAndResultCodes(DataContainer data) {

    mdNameObj.ClearProperties();

    mdNameObj.SetFullName(data.Name);
    mdNameObj.Parse();
    mdNameObj.Genderize();
    mdNameObj.Salutate();
    data.ResultCodes = mdNameObj.GetResults();

    // ResultsCodes explain any issues Name Object has with the object.
    // List of result codes for Name Object
    // https://wiki.melissadata.com/?title=Result_Code_Details#Name_Object

  }
}

class DataContainer {
  public String Name;
  public String ResultCodes;
}
