public class Tester {

    private static final String PASSPHRASE = "test";

    private static final String DE_INPUT = "x.pgp";
    private static final String DE_OUTPUT = "x_decoded.txt";
    private static final String DE_KEY_FILE = "secring.skr";

    private static final String E_INPUT = "x.txt";
    private static final String E_OUTPUT = "x.pgp";
    private static final String E_KEY_FILE = "pubring.pkr";

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

    public static void main(String [] args){
        try{
            testDecrypt();

        }catch (Exception e){
            e.printStackTrace();
        }
//        testDecrypt();
    }
}

