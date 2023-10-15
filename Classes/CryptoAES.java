package Classes;

//Imports
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoAES {
    
    //Attributes
    private IvParameterSpec iv;
    private SecretKeySpec secKey;
    private Cipher cipher;

    //Constructor
    public CryptoAES(String key, String intiVector) {
        try 
        {
            
            //Initializing the Iv and the sec key (Encoding: UTF-8)
            this.iv = new IvParameterSpec(intiVector.getBytes("UTF-8"));

            //Initializing the Secret key
            this.secKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            //Initializing the Cypher
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        }
        catch(UnsupportedEncodingException e) { Utils.Messagebox_Err("Error generated! \nUnsopported encoding found!"); } 
        catch(Exception e) { Utils.Messagebox_Err(e.toString()); };
    }

    //Encrypt the String
    public String EncryptString(String target) {
        try
        {
            //Encrypting the string
            this.cipher.init(Cipher.ENCRYPT_MODE, secKey, iv);
            byte bytes[] = cipher.doFinal(target.getBytes());

            //Returning the encoded String
            return Base64.getEncoder().encodeToString(bytes);
        }
        catch (BadPaddingException e) { Utils.Messagebox_Err("Error generated! \nBad padding exception!"); }
        catch (InvalidKeyException e) { Utils.Messagebox_Err("Error generated! \nInvalid key provided!"); }
        catch (InvalidAlgorithmParameterException e) { Utils.Messagebox_Err("Error generated! \nInvalid paramters for the algorithm!"); }
        catch (Exception e) { Utils.Messagebox_Err(e.toString()); } 
        return "";  //If it fails then we return an empty string
    }

    //Decrypt the String
    public String DecryptString(String target) {
        try
        {
            //Decrypting the string
            this.cipher.init(Cipher.DECRYPT_MODE, secKey, iv);
            byte bytes[] = cipher.doFinal(Base64.getDecoder().decode(target));

            //Returning the uncrypted String
            return new String(bytes);
        }
        catch (BadPaddingException e) { Utils.Messagebox_Err("Error generated! \nBad padding exception!"); }
        catch (InvalidKeyException e) { Utils.Messagebox_Err("Error generated! \nInvalid key provided!"); }
        catch (Exception e) { Utils.Messagebox_Err(e.toString()); } 
        return "";  //If it fails then we return an empty string
    }

}