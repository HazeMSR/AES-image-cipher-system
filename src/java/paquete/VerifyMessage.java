package paquete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyMessage {

    private List<byte[]> list;

    @SuppressWarnings("unchecked")
    //The constructor of VerifyMessage class retrieves the byte arrays from the File and prints the message only if the signature is verified.
    public VerifyMessage(String filename, String keyFile,String ruta){
        System.out.println("filename: "+filename);
        System.out.println("keyFile: "+keyFile);
        System.out.println("ruta: "+ruta);
        try {
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(new FileInputStream(filename));
            } catch (FileNotFoundException ex) {
                System.out.println("Error leyendo la ruta del mensaje: "+ex);
                Logger.getLogger(VerifyMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println("Error en el manejo de la entrada del archivo: "+ex);
                Logger.getLogger(VerifyMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.list = (List<byte[]>) in.readObject();
            in.close();
            String ret = verifySignature(list.get(0), list.get(1), keyFile) ? "VERIFIED MESSAGE: \n" +  new String(list.get(0)) : "Could not verify the signature.";
            byte[] aux = ret.getBytes();
            FileOutputStream out = new FileOutputStream(ruta);
            out.write(aux);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error creando el archivo "+ex);
            Logger.getLogger(VerifyMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error clase no encontrada: "+ex);
            Logger.getLogger(VerifyMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("Error ha habido una excepcion: "+ex);
            Logger.getLogger(VerifyMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method for signature verification that initializes with the Public Key, updates the data to be verified and then verifies them using the signature
    private boolean verifySignature(byte[] data, byte[] signature, String keyFile) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(getPublic(keyFile));
        sig.update(data);

        return sig.verify(Base64.getDecoder().decode(signature));
    }

    //Method to retrieve the Public Key from a file
    public PublicKey getPublic(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(keyBytes));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    
    public static void main(String[] args) throws Exception {
        new VerifyMessage("C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/prueba.txt", "C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/userpubk1985.txt","C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/Dec1prueba.txt");
    }
    
}
