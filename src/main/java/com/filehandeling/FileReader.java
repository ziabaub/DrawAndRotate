package com.filehandeling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public byte[] readFile(String fileName) {

        File file = new File(fileName);

        // Using java.io.FileInputStream
        byte[] bArray = readFileToByteArray(file);

        //displaying content of byte array
        for (int i = 0; i < bArray.length; i++) {
            System.out.print((char) bArray[i]);
        }
        return bArray;
    }

    /**
     * This method uses java.io.FileInputStream to read
     * file content into a byte array
     *
     * @param file
     * @return
     */
    private static byte[] readFileToByteArray(File file) {
        FileInputStream fis = null;

        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }
}
