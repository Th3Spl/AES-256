package Classes;

import java.io.FileWriter;
import java.time.LocalDateTime;

//Imports
import javax.swing.JOptionPane;

public class Utils {
    
    //Error box
    public static void Messagebox_Err(String _content) {
        JOptionPane.showMessageDialog(null, _content, "Error!", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public static void saveToFile(String _content) {
        try
        {
            //Getting the date and creating the String that will be the name of the file
            LocalDateTime date = LocalDateTime.now();
            String fileName = "" + date.getMonthValue() + "-" + date.getDayOfMonth() + "-" + date.getYear() + ".txt";

            //Writing the file
            FileWriter fw = new FileWriter(fileName, true);
            fw.write("\n\n" + _content);
            fw.close();

        }catch (Exception e) { Messagebox_Err(e.toString()); }
    }

}
