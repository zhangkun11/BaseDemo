
package com.rolmex.android.oa.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class TrippleDes {

    private static final String UNICODE_FORMAT = "UTF8";

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    private static byte[] getMD5Digest(byte[] buffer) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] result = new byte[md5.getDigestLength()];
            md5.reset();
            md5.update(buffer);
            result = md5.digest();

            StringBuffer buf = new StringBuffer(result.length * 2);

            for (int i = 0; i < result.length; i++) {
                int intVal = result[i] & 0xff;
                if (intVal < 0x10) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(intVal));
            }

            return result;
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String decrypt(String key, String target) {
        byte[] arrayBytes = null;
        try {
            arrayBytes = getMD5Digest(key.getBytes("ascii"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        arrayBytes = copyOfRange(arrayBytes, 0, 24);
        System.arraycopy(arrayBytes, 0, arrayBytes, 16, 8);
        KeySpec ks;
        String result = null;
        try {
            ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            SecretKey sk = skf.generateSecret(ks);
            cipher.init(Cipher.DECRYPT_MODE, sk);
            byte[] encryptedText = Base64.decode(target, 0);
            byte[] plainText = cipher.doFinal(encryptedText);
            result = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Copies elements from {@code original} into a new array, from indexes
     * start (inclusive) to end (exclusive). The original order of elements is
     * preserved. If {@code end} is greater than {@code original.length}, the
     * result is padded with the value {@code (byte) 0}.
     * 
     * @param original the original array
     * @param start the start index, inclusive
     * @param end the end index, exclusive
     * @return the new array
     * @throws ArrayIndexOutOfBoundsException if
     *             {@code start < 0 || start > original.length}
     * @throws IllegalArgumentException if {@code start > end}
     * @throws NullPointerException if {@code original == null}
     * @since 1.6
     */
    public static byte[] copyOfRange(byte[] original, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        int originalLength = original.length;
        if (start < 0 || start > originalLength) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int resultLength = end - start;
        int copyLength = Math.min(resultLength, originalLength - start);
        byte[] result = new byte[resultLength];
        System.arraycopy(original, start, result, 0, copyLength);
        return result;
    }

    public static String encrypt(String key, String target) {
        byte[] arrayBytes = null;
        try {
            arrayBytes = getMD5Digest(key.getBytes("ascii"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        arrayBytes = copyOfRange(arrayBytes, 0, 24);
        System.arraycopy(arrayBytes, 0, arrayBytes, 16, 8);
        KeySpec ks;
        String result = null;
        try {
            ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            SecretKey sk = skf.generateSecret(ks);
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            byte[] plainText = target.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            result = new String(Base64.encode(encryptedText, Base64.NO_WRAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
