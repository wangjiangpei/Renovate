package com.dscs.renovate;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class WriteUtils {
    private static final String SEPARATOR = File.separator;//路径分隔符

    public static void writeStr(String mes, File txtFile) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(txtFile, true);
            writer.write(mes);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
