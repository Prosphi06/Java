package com.java.hsaalgorithm;

public class Main {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input");
        String input = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(input.getBytes());

        byte[] digest = md.digest();

        System.out.println(digest);

        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i< digest.length; i++){
            hexString.append(Integer.toHexString(0xFF & digest[i]));
            // hexString.append(String.format("%02x", i));
        }
        System.out.println("Hex decimal format : " +hexString.toString());
    }
}
