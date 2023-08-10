package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

public class Validation {
    public static String tryParseInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            str = "0";
        }

        return str;
    }

    public static int idManipulator(String fileName) {
        int tempReturn = 0;
        try {
            FileReader pidReaderFile = new FileReader(fileName);
            // "staticScore.sms"
            BufferedReader pidReader = new BufferedReader(pidReaderFile);
            char[] ch = new char[100];
            pidReader.read(ch);
            String[] arr = new String(ch).split("=");
            int pidNo = Integer.parseInt(arr[1]);
            // System.out.println(pidNo);
            pidReader.close();
            pidReaderFile.close();
            tempReturn = pidNo;
            pidNo++;
            FileWriter pidWriterFile = new FileWriter(fileName);
            BufferedWriter pidWriter = new BufferedWriter(pidWriterFile);
            pidWriter.write(arr[0] + "=" + pidNo + "=");
            pidWriter.close();
            pidWriter.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        ;
        return tempReturn;
    }

    public static void print(JPanel panel_print) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1,1);

                panel_print.print(g2);

                return Printable.PAGE_EXISTS;

            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {

                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}