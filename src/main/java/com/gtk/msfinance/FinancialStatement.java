package com.gtk.msfinance;

public class FinancialStatement {
    final static public String STR_FINANCIAL_STATEMENT_TOKEN_LIST[] = {
            "재무에관한사항",
            "재무제표등",
            "연결재무제표",
            "결합재무제표" // 저정신고에 재무제표가 있는 경우인데, 이 경우 예외처리가 너무 복잡하여 정정대신 최초 사업보고서를 참조하도록하기위해
            // 해당 토큰은 토큰리스트에 포함하지 않음(http://dart.fss.or.kr/dsaf001/main.do?rcpNo=20070629000498)
    };

}
