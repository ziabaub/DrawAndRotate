package com.filehandeling;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileWriter {

    public void writeByte(byte[] bytes, String file) {
        try {
            // Initialize a pointer
            // in file using OutputStream
            OutputStream os = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(bytes);
            System.out.println("Successfully" + " byte inserted");

            // Close the file
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
