import com.melissadata.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Name Object automates the handling of name data, making it simple to send
 * personalized business mail, tailored specifically to the gender of the people in
 * your mailing list, while screening out vulgar or obviously false names.
 *
 * <p>High-level flow of this sample:
 * <ol>
 *   <li>SETUP     - create an mdName instance, hand it the license string and the
 *                   path to the data files, then InitializeDataFiles() (one time).</li>
 *   <li>INPUT     - feed a full name in with SetFullName().</li>
 *   <li>PROCESS   - Parse() splits the name; Genderize() and Salutate() derive the
 *                   gender and salutation from the parsed result.</li>
 *   <li>READ      - pull the individual fields back out with the Get* getters
 *                   (GetFirstName, GetLastName, GetGender, GetSalutation, ...).</li>
 *   <li>INTERPRET - GetResults() returns comma-separated result codes describing
 *                   what the object did/found; each code has a human description.</li>
 * </ol>
 *
 * <p>The pieces in this file map onto that flow:
 * <ul>
 *   <li>main / RunAsConsole / ParseArguments : console harness (argument parsing + the interactive loop).</li>
 *   <li>NameObject                           : thin wrapper around mdName that owns setup + the call sequence.</li>
 *   <li>DataContainer                        : plain holder for one record's input and output.</li>
 * </ul>
 *
 * <p>Where mdName comes from:
 * The mdName and mdNameJNI classes in com/melissadata come from mdName_JavaCode.zip,
 * which the accompanying MelissaNameObjectLinuxJava.sh script downloads and
 * expands into com/melissadata on every run. mdNameJNI declares the native methods
 * and loads libmdNameJavaWrapper.so, the JNI shim that calls into libmdName.so.
 *
 * <p>Reference:
 * <ul>
 *   <li>Quickstart    : https://docs.melissa.com/on-premise-api/name-object/name-object-quickstart.html</li>
 *   <li>Release notes : https://releasenotes.melissa.com/on-premise-api/name-object/</li>
 *   <li>Result codes  : https://docs.melissa.com/on-premise-api/name-object/result-codes.html</li>
 * </ul>
 */
public class MelissaNameObjectLinuxJava {

  /**
   * Entry point. Reads the optional command-line arguments, then hands control to
   * RunAsConsole, which performs the actual Name Object setup and processing.
   *
   * @param args The raw command-line arguments
   * @throws IOException if reading from standard input fails
   */
  public static void main(String args[]) throws IOException {
    // Populated by ParseArguments below.
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testName = arguments[1];
    String dataPath = arguments[2];

    RunAsConsole(license, testName, dataPath);
  }

  /**
   * Reads the supported command-line options and returns them.
   *
   * <p>Recognized flags (each followed by its value, e.g. "--name Ray Melissa"):
   * <ul>
   *   <li>--license / -l   : the Melissa license string</li>
   *   <li>--dataPath / -d  : path to the Name Object data files</li>
   *   <li>--name / -n      : a name to test in one-shot mode</li>
   * </ul>
   *
   * @param args The raw command-line arguments to parse.
   * @return A String array of { license, testName, dataPath }.
   */
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

  /**
   * Sets up the Name Object once, then drives the input -> process -> output cycle.
   *
   * <p>In interactive mode (no --name) it loops, asking for a new name each pass until
   * the user answers "N". In one-shot mode (--name supplied) it runs a single pass
   * on testName and exits.
   *
   * @param license  The Melissa license string used to initialize the object.
   * @param testName A name to process in one-shot mode; if empty, the program prompts interactively.
   * @param dataPath Path to the Name Object data files.
   * @throws IOException if reading from standard input fails
   */
  public static void RunAsConsole(String license, String testName, String dataPath) throws IOException {
    System.out.println("\n\n============ WELCOME TO MELISSA NAME OBJECT LINUX JAVA =============\n");

    // Construct the wrapper. This is where the object is licensed, pointed at the
    // data files, and initialized (see the NameObject constructor below).
    NameObject nameObject = new NameObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    // Gate the program on a successful initialization. If the data files could not
    // be loaded (bad/expired license, missing or wrong-path data files, ...),
    // GetInitializeErrorString() returns the reason instead of "No Error" and we
    // skip the processing loop entirely.
    if (!nameObject.mdNameObj.GetInitializeErrorString().equals("No Error"))
      shouldContinueRunning = false;

    while (shouldContinueRunning) {
      // Holder for this pass's input and result codes.
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      if (testName == null || testName.trim().isEmpty()) {
        // Interactive mode: prompt the user for a name.
        System.out.println("\nFill in each value to see the Name Object results");
        System.out.print("Name:");

        dataContainer.Name = stdin.readLine();
      } else {
        // One-shot mode: use the name passed on the command line.
        dataContainer.Name = testName;
      }

      // Print user input
      System.out.println("\n============================== INPUTS ==============================\n");
      System.out.println("\t               Name: " + dataContainer.Name);

      // Execute Name Object
      // Runs the parse/genderize/salutate sequence and stores the result codes on dataContainer.
      nameObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
      // Each Get* getter below returns one component the object produced for the most
      // recently processed name. These read directly from the mdName instance, which
      // still holds the results from the Execute call above. Prefix/First/Middle/Last/Suffix
      // come from Parse(), Gender from Genderize(), and Salutation from Salutate().
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

      // Result codes come back as a single comma-separated string (e.g. "NS01,NS02").
      // Split it and ask the object for a readable description of each code.
      // ResultCodeDescriptionLong requests the long-form text; a short form is also
      // available via ResultCodeDescriptionShort
      String[] rs = dataContainer.ResultCodes.split(",");
      for (String r : rs) {
        System.out.println("        " + r + ":"
            + nameObject.mdNameObj.GetResultCodeDescription(r, mdName.ResultCdDescOpt.ResultCodeDescriptionLong));
      }

      Boolean isValid = false;

      // In one-shot mode there is nothing more to do after a single pass: mark the
      // input handled and stop the outer loop.
      if (testName != null && !testName.trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

      // Interactive mode: ask whether to process another name. Keep prompting until
      // we get a valid Y/N. "N" ends the program; "Y" falls through to another pass.
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

/**
 * Wrapper that owns a single Melissa Name Object instance and encapsulates the two
 * things every Melissa object needs: one-time setup (license + data files) and the
 * per-record processing sequence. Reuse one instance across many names; do NOT
 * re-initialize per name.
 */
class NameObject {
  // Path to the Name Object data files.
  String dataFilePath;

  // The underlying Melissa Name Object instance.
  mdName mdNameObj = new mdName();

  /**
   * Performs the mandatory one-time setup, in this required order:
   * <ol>
   *   <li>SetLicenseString    - authorize the object.</li>
   *   <li>SetPathToNameFiles  - tell it where the data files live.</li>
   *   <li>InitializeDataFiles - load the data into memory.</li>
   * </ol>
   *
   * @param license  The Melissa license string used to authorize the object.
   * @param dataPath Path to the folder containing the Name Object data files.
   */
  public NameObject(String license, String dataPath) {
    // Set license string and set path to data files
    mdNameObj.SetLicenseString(license);
    dataFilePath = dataPath;
    mdNameObj.SetPathToNameFiles(dataFilePath);

    // Load the data files. The returned ProgramStatus reports whether initialization succeeded.
    // If you see a different date than expected, check your license string and either download the new data files
    // or use the Melissa Updater program to update your data files.
    mdName.ProgramStatus pStatus = mdNameObj.InitializeDataFiles();

    // If an issue occurred, please investigate the common causes.
    // Common causes: an invalid/expired license, or missing/wrong-path data files.
    if (pStatus != mdName.ProgramStatus.NoError) {
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    // Diagnostic information, handy for confirming the object loaded the data you expect:

    // Build date of the data files
    System.out.println("                DataBase Date: " + mdNameObj.GetDatabaseDate());

    // When the license stops working
    System.out.println("              Expiration Date: " + mdNameObj.GetLicenseExpirationDate());

    // This number should match with the file properties of the Melissa Object binary file.
    // If TEST appears with the build number, there may be a license key issue.
    System.out.println("               Object Version: " + mdNameObj.GetBuildNumber());
    System.out.println();

  }

  /**
   * Runs the full Name Object processing sequence for one name and captures its
   * result codes. This is the canonical per-record call pattern to copy into your
   * own application:
   * ClearProperties -> SetFullName -> Parse -> Genderize -> Salutate -> GetResults
   *
   * @param data The record to process. Its Name is read as input, and ResultCodes is
   *             populated with this run's result codes.
   */
  public void ExecuteObjectAndResultCodes(DataContainer data) {

    // Reset any state left over from a previous name. Important when reusing the same
    // object across multiple records so fields from a prior name don't bleed into this one.
    mdNameObj.ClearProperties();

    // Supply the raw full-name string to process
    mdNameObj.SetFullName(data.Name);

    // Split it into prefix/first/middle/last/suffix
    mdNameObj.Parse();

    // Infer gender from the parsed first name
    mdNameObj.Genderize();

    // Build a salutation from the parsed components
    mdNameObj.Salutate();

    // Collect the result codes for this run
    // ResultsCodes explain any issues Name Object has with the object.
    // List of result codes for Name Object
    // https://docs.melissa.com/on-premise-api/name-object/result-codes.html
    data.ResultCodes = mdNameObj.GetResults();
  }
}

/**
 * Data holder for a single record: carries the input name in and the result codes out.
 */
class DataContainer {
  // Input: the full name to process.
  public String Name;

  // Output: comma-separated result codes from GetResults().
  public String ResultCodes;
}
