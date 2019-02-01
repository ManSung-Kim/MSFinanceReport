package com.gtk.msfinance;

import java.util.ArrayList;
import java.util.Scanner;

import com.gtk.msfinance.docmgr.CsvMgr;
import com.gtk.msfinance.math.Polynomials;
import com.gtk.msfinance.util.Prt;



public class Main {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                CsvMgr.closeAll();
            }
        }));

        String strMenu = "1. record from all stocks\n"
                +"2. analysis1\n"
                + "select menu : ";
        System.out.print(strMenu);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch(Integer.parseInt(input)) {
            case 1:
                recFromAllStocks();
                break;
            case 2:
                analysis1();
                break;
            default:
                break;
        }


    }

    private static void analysis1() { // title not defined yet
        Polynomials.test(3);
    }

    private static void recFromAllStocks() {
        ArrayList<Stock> listStock;

        final boolean isTestMode = false;
        if(isTestMode) { // test data
            listStock = new ArrayList<Stock>();
            listStock.add(new Stock("썔바이오","049960"));
            listStock.add(new Stock("계양전기","012200"));
            listStock.add(new Stock("삼성전자","005930"));
        } else { // read all dat from csv
            listStock = CsvMgr.getList("csv\\data_stocks.csv", 1);
        }

        String strWriteFilePath = "csv\\data" + System.currentTimeMillis() + ".csv";
        CsvMgr.initStaticBufferedWriter(strWriteFilePath);

        //List<String> listFinancialStatement = new ArrayList<String>();

        String strYearTag = "";
        for(int i = 2018; i >= 1970; i--)
            strYearTag += "," + i;
        //listFinancialStatement.add(strYearTag);
        CsvMgr.writeFileNoneStop(strYearTag);
        int stocksSize = listStock.size();
        for(int i = 0; i < stocksSize; i++) {
            Stock stock = listStock.get(i);
            String strStockCsv = stock.getName();

            stock.updateYearReport();

            int reportSize = stock.getYearReportCnt();
            String out = "";
            out += i + "/" +stocksSize + " ";
            out += stock.getName() + "] ";
            Prt.w(out);

            //
            String strYearProfits = "";
            String strNetIncomes = "";
            String strTotalAssetTotals = "";
            String strROAs = "";

            strYearProfits += "영업이익] ";
            strNetIncomes += "당기순이익] ";
            strTotalAssetTotals += "자산총계] ";
            strROAs += "자산수익률ROA] ";

            String strYear = "";
            String strYearProfit = "";
            String strNetIncome = "";
            String strTotalAssets = "";
            double roa = .0f;

            for(int j = 0; j < reportSize; j++) {
                strYear = stock.getYear(j);
                strYearProfit = stock.getYearProfit(j);
                strNetIncome = stock.getNetIncome(j);
                strTotalAssets = stock.getTotalAssets(j);
                roa = stock.getROA(j);

                strYearProfits += strYear + "," + strYearProfit + "  ";
                strNetIncomes += strYear + "," + strNetIncome + "  ";
                strTotalAssetTotals += strYear + "," + strTotalAssets + "  ";
                strROAs += strYear + "," + roa + "  ";

                // csv
                strStockCsv += "," + strYearProfit;
            }

            Prt.w(strYearProfits);
            Prt.w(strNetIncomes);
            Prt.w(strTotalAssetTotals);
            Prt.w(strROAs);

            //listFinancialStatement.add(strStockCsv);
            CsvMgr.writeFileNoneStop(strStockCsv);
        }
        //CsvMgr.writeFile("csv\\data.csv", listFinancialStatement);
        CsvMgr.closeAll();
    }

}
