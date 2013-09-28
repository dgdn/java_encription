import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class Tester {

    private static final String PASSPHRASE = "hello";
    private static final String E_MAIL_ADDR = "alice@example.com";


    private static final String DE_INPUT = "x.pgp";
    private static final String DE_OUTPUT = "x_decoded.txt";
    private static final String DE_KEY_FILE = "dummy.skr";

    private static final String E_INPUT = "x.txt";
    private static final String E_OUTPUT = "x.pgp";
    private static final String E_KEY_FILE = "dummy.pkr";

    public static void testDecrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(DE_INPUT);
        p.setOutputFileName(DE_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setSecretKeyFileName(DE_KEY_FILE);
        System.out.println(p.decrypt());
    }

    public static void testEncrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(E_INPUT);
        p.setOutputFileName(E_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setPublicKeyFileName(E_KEY_FILE);
        System.out.println(p.encrypt());
    }

    public static void testGenKey() throws Exception{
        char pass[] = PASSPHRASE.toCharArray();
        PGPKeyRingGenerator krgen = RSAGen.generateKeyRingGenerator
                (E_MAIL_ADDR, pass);

        // Generate public key ring, dump to file.
        PGPPublicKeyRing pkr = krgen.generatePublicKeyRing();
        BufferedOutputStream pubout = new BufferedOutputStream
                (new FileOutputStream(E_KEY_FILE));
        pkr.encode(pubout);
        pubout.close();

        // Generate private key, dump to file.
        PGPSecretKeyRing skr = krgen.generateSecretKeyRing();
        BufferedOutputStream secout = new BufferedOutputStream
                (new FileOutputStream(DE_KEY_FILE));
        skr.encode(secout);
        secout.close();
    }

    public static void main(String [] args){
        try{
            testGenKey();
            testEncrypt();
            testDecrypt();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

