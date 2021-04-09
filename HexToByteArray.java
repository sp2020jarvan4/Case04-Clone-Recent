public class Key {
    public KeyPair genKey() throws NoSuchAlgorithmException{
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator gen;

        gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048, sr);

        KeyPair kp = gen.genKeyPair();
        return kp;
    }

    public String priKeyToString(PrivateKey pKey) throws  Exception{
        return new String(byteArrayToHex(pKey.getEncoded()));
    }


    public String pubKeyToString(PublicKey pubKey) throws  Exception{
        return new String(byteArrayToHex(pubKey.getEncoded()));
    }



    public PublicKey stringToPublicKey(String pubKey) throws Exception{
        X509EncodedKeySpec uKeySpec = new X509EncodedKeySpec(hexToByteArray(pubKey));
        KeyFactory uKeyFactor = KeyFactory.getInstance("RSA");
        PublicKey uKey;
        uKey = uKeyFactor.generatePublic(uKeySpec);
        return uKey;

    }
    public static byte[] hexToByteArray(String hex){
        if (hex == null || hex.length() == 0){
            return null;
        }
        byte [] ba = new byte[hex.length() / 2];
        for (int i = 0; i< ba.length; i++){
            ba[i]  = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16 );
        }
        return ba;
    }
}