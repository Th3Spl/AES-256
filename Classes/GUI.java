package Classes;

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GUI {

    protected static void renderMessage(String title, String content) {

        JFrame frame = new JFrame(title);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setLayout(new FlowLayout());
        frame.setLocation(400, 400);

        JLabel label = new JLabel("Result: \n");
        frame.add(label);

        JLabel labelCont = new JLabel(content);
        frame.add(labelCont);

        JButton CopyToClipboard = new JButton("Copy to clipboard.");
        frame.add(CopyToClipboard);

        CopyToClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection strSel = new StringSelection(content);
                clipboard.setContents(strSel, null);
            }
        });
        frame.setVisible(true);
    }

    public static void RenderGUI(String iv) {

        JFrame frame = new JFrame("AES-256 Encrypter By: Th3Spl");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.setLocation(400, 400);
        

        //password field
        JLabel pswLabel = new JLabel("Password:");
        frame.add(pswLabel);

        JTextField passwordField = new JTextField(27);
        frame.add(passwordField);


        //target field
        JLabel targetLabel = new JLabel("Text:");
        frame.add(targetLabel);

        JTextField targetText = new JTextField(27);
        frame.add(targetText);

        JButton btnEncrypt = new JButton("Encrypt");
        frame.add(btnEncrypt);

        JButton btnDecrypt = new JButton("Decrypt");
        frame.add(btnDecrypt);

        JCheckBox saveToFile = new JCheckBox("Save within a file.");
        frame.add(saveToFile);

        // Encrypt the string
        btnEncrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String key = passwordField.getText();
                key = new String(Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 32));

                //Creating the object that we'll use to encrypt the data
                CryptoAES crypt = new CryptoAES(key, iv);
                String encString = crypt.EncryptString(targetText.getText());

                renderMessage("Encryption Result:", encString);

                if (saveToFile.isSelected())
                {
                    Utils.saveToFile(encString);   
                }
            }
        });


        // Decrypting the string
        btnDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String key = passwordField.getText();
                key = new String(Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 32));

                //Creating the object that we'll use to decrypt the data
                CryptoAES crypt = new CryptoAES(key, iv);
                String decString = crypt.DecryptString(targetText.getText());

                renderMessage("Decryption Result:", decString);

                if (saveToFile.isSelected()) 
                {
                    Utils.saveToFile(decString);
                }
            };
        });

        frame.setVisible(true);
    }
}
