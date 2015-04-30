package com.example.yuta.helloworld;

/**
 * Created by Yuta on 2015/02/20.
 */
public class BarcodeUtil {


    public static Boolean isJAN13(String strbarcode){
        if(strbarcode.length() != 13)
            return false;

        if(!isCorrectCheckDigit(strbarcode))
            return false;

        return true;
    }

    /**
     * チェックデジットの計算
     * @param strbarcode
     * @return
     */
    public static Boolean isCorrectCheckDigit(String strbarcode){

        if(strbarcode.charAt(strbarcode.length()-1)==intCheckDigit(strbarcode))
            return true;
        return false;
    }

    //todo: ようテスト
    /**
     * チェックデジットを返す
     * @param strbarcode_with_checkdegit
     * @return
     */
    public static int intCheckDigit(String strbarcode_with_checkdegit){
        int sum=0;

        //注意チェックデジットは除くため、-2がスタート
        for(int i = strbarcode_with_checkdegit.length()-2;i >=0 ;i--)
            sum+=Integer.valueOf(strbarcode_with_checkdegit.charAt(i))*((i%2 == 0)?3:1);

        int re=sum%10;

        if (0==re)return re;

        return 10-re;
    }



}
