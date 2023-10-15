//Imports
import Classes.GUI;

public class Source {
    public static void main(String[] args) 
    {

        /*
        Note:
        We could have used a random IV (and you can use it by changing this value)
        but i prefer to keep it the same always so the user needs only a password in order 
        to decrypt the desired String, in case you want to use a random one you'll need to save it
        and use the same one for the decryption so PAY ATTENTION
        */
        String initVector = "7342djj737rfhh37";

        //Rendering the GUI
        GUI.RenderGUI(initVector);

        return;
    }
}