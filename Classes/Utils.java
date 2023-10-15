package Classes;

import java.io.FileWriter;
import java.time.LocalDateTime;

//Imports
import javax.swing.JOptionPane;

public class Utils {
    
    //Information box
    public static void MessageBox_Info(String _content) {
        JOptionPane.showMessageDialog(null, _content, "Info!", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    //Warning box
    public static void MessageBox_Warn(String _content) {
        JOptionPane.showMessageDialog(null, _content, "Warn!", JOptionPane.WARNING_MESSAGE);
        return;
    }

    //Error box
    public static void Messagebox_Err(String _content) {
        JOptionPane.showMessageDialog(null, _content, "Error!", JOptionPane.ERROR_MESSAGE);
        return;
    }

    //Plain box
    public static void MessageBox_Plain(String _content, String _title) {
        JOptionPane.showMessageDialog(null, _content, _title, JOptionPane.PLAIN_MESSAGE);
    }

    public static void saveToFile(String _content) {
        try
        {
            //Getting the date and creating the String that will be the name of the file
            LocalDateTime date = LocalDateTime.now();
            String fileName = "" + date.getMonthValue() + "-" + date.getDayOfMonth() + "-" + date.getYear() + ".txt";

            //Writing the file
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(_content);
            fw.close();

        }catch (Exception e) { Messagebox_Err(e.toString()); }
    }

}
