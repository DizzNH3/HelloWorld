package com.example.yuta.helloworld;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Yuta on 2015/04/30.
 */
public  class AshiComClass {

    /**
     * トーストメッセージを表示する
     * @param context activityContext
     * @param str 表示文字
     * @param isLong 表示時間を長くする？
     */
    public static void showToastMessage(Context context,String str,boolean isLong){
        if(isLong)
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();

        return;
    }



    private static double calcTaxFromPriceWithTax_double(int priceWithTax,double taxPercent){
        return priceWithTax/(100.0+taxPercent)*taxPercent;
    }

    /**
     * 内税端数設定
     * @param context
     * @return 0:切り捨て1:切り上げ2:四捨五入
     */
    public static int getCalculationOption_Fraction_Tax_Inclusive(Context context){
        //四捨五入といった端数設定
        SharedPreferences _fractionOption = PreferenceManager.getDefaultSharedPreferences(context);
        return _fractionOption.getInt(Shared_Preferences.CALC_OPTION_TAX_INCLUSIVE, FRACTION_ENUM.FRACTION_OPTION.CEIL_FACTION.getId());
    }

    /**
     *税込み価格から税額を求める
     * @param priceWithTax
     * @param taxPercent
     * @return
     */
    public static int calcTaxFromPriceWithTax(int priceWithTax,double taxPercent,Context context){

        //四捨五入といった端数設定
        if(getCalculationOption_Fraction_Tax_Inclusive(context)
            == FRACTION_ENUM.FRACTION_OPTION.CEIL_FACTION.getId()) {
            showToastMessage(context,priceWithTax+"&"+taxPercent+"&"+String.valueOf(calcTaxFromPriceWithTax_double(priceWithTax,taxPercent)),false);
            return (int) Math.ceil(calcTaxFromPriceWithTax_double(priceWithTax, taxPercent));
        }
        if(getCalculationOption_Fraction_Tax_Inclusive(context)
                == FRACTION_ENUM.FRACTION_OPTION.FLOOR_FRACTION.getId())
            return (int)Math.floor(calcTaxFromPriceWithTax_double(priceWithTax,taxPercent));

        if(getCalculationOption_Fraction_Tax_Inclusive(context)
                == FRACTION_ENUM.FRACTION_OPTION.ROUND_FRACTION.getId())
            return (int)Math.round(calcTaxFromPriceWithTax_double(priceWithTax,taxPercent));


        return (int)calcTaxFromPriceWithTax_double(priceWithTax,taxPercent);
    }

    /**
     * 税込み価格から税額を引いた額を求める
     * @param priceWithTax
     * @param taxPercent
     * @return
     */
    public static int calcPriceWithoutTaxFromPriceWithTax(int priceWithTax,double taxPercent,Context context){
        return (int)priceWithTax - calcTaxFromPriceWithTax(priceWithTax,taxPercent,context);
    }

    private static double calcTaxFromPriceWithoutTax_double(int priceWithoutTax,double taxPercent){
        return priceWithoutTax*(0.01*taxPercent);
    }

    /**
     * 外税端数設定
     * @param context
     * @return 0:切り捨て1:切り上げ2:四捨五入
     */
    public static int getCalculationOption_Fraction_Tax_Exclusive(Context context) {
        SharedPreferences _fractionOption = PreferenceManager.getDefaultSharedPreferences(context);
        return _fractionOption.getInt(Shared_Preferences.CALC_OPTION_TAX_EXCLUSIVE, FRACTION_ENUM.FRACTION_OPTION.CEIL_FACTION.getId());
    }

    /**
     * 税抜き価格から税額を求める
     * @param priceWithoutTax
     * @param taxPercent
     * @return
     */
    public static int calcTaxFromPriceWithoutTax(int priceWithoutTax,double taxPercent,Context context){
        //四捨五入といった端数設定
        if(getCalculationOption_Fraction_Tax_Exclusive(context)
                == FRACTION_ENUM.FRACTION_OPTION.CEIL_FACTION.getId())
            return (int)Math.ceil(calcTaxFromPriceWithoutTax_double(priceWithoutTax,taxPercent));
        if(getCalculationOption_Fraction_Tax_Exclusive(context)
                == FRACTION_ENUM.FRACTION_OPTION.FLOOR_FRACTION.getId())
            return (int)Math.floor(calcTaxFromPriceWithoutTax_double(priceWithoutTax,taxPercent));
        if(getCalculationOption_Fraction_Tax_Exclusive(context)
                == FRACTION_ENUM.FRACTION_OPTION.ROUND_FRACTION.getId())
            return (int)Math.round(calcTaxFromPriceWithoutTax_double(priceWithoutTax,taxPercent));


        return (int)calcTaxFromPriceWithoutTax_double(priceWithoutTax,taxPercent);
    }

    /**
     * 税抜き価格から税込み価格価格を求める
     * @param priceWithoutTax
     * @param taxPercent
     * @param context
     * @return
     */
    public static int calcPriceWithTaxFromPriceWithoutTax(int priceWithoutTax,double taxPercent,Context context){
        return priceWithoutTax + calcTaxFromPriceWithoutTax(priceWithoutTax,taxPercent,context);
    }

    public static String editText2String(EditText et){
        return et.getText().toString();
    }

    public static Double editText2Double(EditText et){
        return Double.parseDouble(et.getText().toString());
    }

    public static int editText2Int(EditText et){
        return Integer.parseInt(et.getText().toString());
    }
}
