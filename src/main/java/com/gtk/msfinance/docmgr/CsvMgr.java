package com.gtk.msfinance.docmgr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gtk.msfinance.Stock;

public class CsvMgr {

    static private File file = null;
    static private BufferedWriter bw = null;

    static public void initStaticBufferedWriter(String path) {
        try {
            file = new File(path);
            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static public void closeAll() {
        if(bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            bw = null;
        }


        if(file != null) {
            file = null;
        }
    }

    static public void writeFileNoneStop(String data) {
        try {
            bw.write(data);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static public void writeFile(String path, List<String> data) {
        File file = null;
        BufferedWriter bw = null;

        try {

            file = new File(path);
            bw = new BufferedWriter(new FileWriter(file));

            for(String item : data) {
                bw.write(item);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static public <T extends Object> ArrayList<T> getList(String path, int categoryCnt) {
        ArrayList<T> list = new ArrayList<T>();

        int passCnt = categoryCnt;

        File file = null;
        BufferedReader br = null;

        try {

            file = new File(path);
            br = new BufferedReader(new FileReader(file));

            for(int i = 0; i< passCnt; i++)
                br.readLine();

            String line;
            while((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                while(arr[0].length() < 6)
                {
                    arr[0] = "0" + arr[0];
                }

                list.add((T) new Stock(arr[1], arr[0]));
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                br.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return list;
    }
}
