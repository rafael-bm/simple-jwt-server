package com.mulecode.jwtserver.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class RsaKeyUtils {

    private static String KEY_INSTANCE = "RSA";

    public static KeyPair generateRSAKey() throws NoSuchAlgorithmException {

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_INSTANCE);
        keyGenerator.initialize(2048, new SecureRandom());
        return keyGenerator.genKeyPair();
    }

    public static String serializeKey(final RSAPublicKey publicKey) throws Exception {

        X509EncodedKeySpec specPub = getKeyFactory().getKeySpec(
                publicKey,
                X509EncodedKeySpec.class
        );

        return Base64.encodeBase64String(specPub.getEncoded());
    }

    public static String serializeKey(final RSAPrivateKey privateKey) throws Exception {

        PKCS8EncodedKeySpec specPri = getKeyFactory().getKeySpec(
                privateKey,
                PKCS8EncodedKeySpec.class
        );

        byte[] packed = specPri.getEncoded();

        return Base64.encodeBase64String(packed);
    }

    public static RSAPrivateKey parseToRSAPrivate(final String privateKeyStr) throws Exception {

        byte[] keyBytes = Base64.decodeBase64(
                privateKeyStr.getBytes(StandardCharsets.UTF_8)
        );

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = getKeyFactory().generatePrivate(spec);

        return (RSAPrivateKey) privateKey;
    }

    public static RSAPublicKey parseToRSAPublic(final String publicKeyStr) throws Exception {

        byte[] keyBytes = Base64.decodeBase64(
                publicKeyStr.getBytes(StandardCharsets.UTF_8)
        );

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = getKeyFactory().generatePublic(spec);

        return (RSAPublicKey) publicKey;
    }

    private static KeyFactory getKeyFactory() throws NoSuchAlgorithmException {

        return KeyFactory.getInstance(KEY_INSTANCE);
    }

    public Boolean isPair(final PublicKey publicKey, final PrivateKey privateKey) throws Exception {

        Objects.requireNonNull(publicKey,"publicKey should not be null.");
        Objects.requireNonNull(privateKey,"privateKey should not be null.");

        String file = "qwerty";
        byte[] fileBytes = file.getBytes();
        byte[] digitalSignature = signData(fileBytes, privateKey);

        Boolean verified = verifySig(fileBytes, publicKey, digitalSignature);

        return verified;
    }

    private static byte[] signData(byte[] data, PrivateKey privateKey) throws Exception {

        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(data);

        return (signer.sign());
    }

    private static boolean verifySig(byte[] data, PublicKey publicKey, byte[] sig) throws Exception {

        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initVerify(publicKey);
        signer.update(data);

        return (signer.verify(sig));
    }

}
