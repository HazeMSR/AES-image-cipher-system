package paquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Message {

    private List<byte[]> list;

    //The constructor of Message class builds the list that will be written to the file. The list consists of the message and the signature.
    public Message(String data, String keyFile) throws InvalidKeyException, Exception {
        list = new ArrayList<byte[]>();
        list.add(data.getBytes());
        list.add(sign(data, keyFile));
    }

    //The method that signs the data using the private key that is stored in keyFile path
    public byte[] sign(String data, String keyFile) throws InvalidKeyException, Exception {
        Signature dsa = Signature.getInstance("SHA256withRSA");
        dsa.initSign(getPrivate(keyFile));
        dsa.update(data.getBytes());
        return Base64.getEncoder().encode(dsa.sign());
    }

    //Method to retrieve the Private Key from a file
    public PrivateKey getPrivate(String filename)  {
        KeyFactory kf=null;
        byte[] keyBytes = null;
        PKCS8EncodedKeySpec spec = null;
        try{
            
        keyBytes = Files.readAllBytes(new File(filename).toPath());
            
            System.out.println("keyBytes: "+keyBytes);
            System.out.println("keyBytes Length: "+keyBytes.length);
            System.out.println("fileName: "+filename);
            
         spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyBytes));
        //X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(keyBytes));
        kf = KeyFactory.getInstance("RSA");
        }
        catch(IOException e){
            System.out.println("keyBytes: "+keyBytes);
            System.out.println("fileName: "+filename);
            System.out.println("Error : "+e);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error No such Algorithm: "+ex);
            System.out.println("keyBytes: "+keyBytes);
            System.out.println("fileName: "+filename);
        }
        try {
            return kf.generatePrivate(spec);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Invalid Key: "+ex);
            System.out.println("keyBytes: "+keyBytes);
            System.out.println("fileName: "+filename);
        }
        return null;
    }

    //Method to write the List of byte[] to a file
    void writeToFile(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        f.getParentFile().mkdirs();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(list);
        out.close();
        System.out.println("Your file is ready.");
    }
/*
    public static void main(String[] args) throws InvalidKeyException, IOException, Exception {
        String data = JOptionPane.showInputDialog("Type your message here");

        new Message(data, "llaves/privateKey.txt").writeToFile("Mensaje/SignedData.txt");
    }
*/
}
