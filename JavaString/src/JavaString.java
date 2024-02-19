import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.net.URL;


public class JavaString {
    public static void main(String[] args) throws Exception {
        String fileName = "main/resources/String1.txt";
        JavaString instance = new JavaString();
        File file = instance.getFile(fileName);

        String fileContent = readUsingScanner(file.toPath().toString());

        String dataSource = removeConsecutiveElementsFromString(fileContent, 3);
        System.out.println(dataSource);
    }

    private File getFile(String fileName) throws IOException
    {
      ClassLoader classLoader = getClass().getClassLoader();
          URL resource = classLoader.getResource(fileName);
           
          if (resource == null) {
              throw new IllegalArgumentException("file is not found!");
          } else {
              return new File(resource.getFile());
          }
    }

    private static String readUsingScanner(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        scanner.close();
        return builder.toString();
    }

    public static String removeConsecutiveElementsFromString(String dataSource, int maxCountAllowed) {
    StringBuilder builder = new StringBuilder(dataSource);
    for (int i=0; i<builder.length(); i++) {
        int foundCount = 0;
        for (int j=i-1; j>=0; j--) {
            if (builder.charAt(i) == builder.charAt(j)) {
                foundCount++;
                if (foundCount >= maxCountAllowed) {
                    builder.deleteCharAt(i);
                    i--;
                    break;
                }
            } else {
                break;
            }
        }
    }
    return builder.toString();
    }
}
