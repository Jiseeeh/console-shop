package Helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    public static void makeFile (String filePath,String header)  {
        File file = new File(filePath);

        try {
            if (new File("src/CSV").mkdir())
                if (file.createNewFile()) writeToFile(file,header);

            else {
                if (file.createNewFile()) writeToFile(file, header);
                // read
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile (File file, String content) {
        try (FileWriter writer = new FileWriter(file,true)) {
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
