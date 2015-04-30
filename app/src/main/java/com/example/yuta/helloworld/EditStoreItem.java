package com.example.yuta.helloworld;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class EditStoreItem extends Activity{

    protected void _calcAndSet_TaxAndPriceWithTaxFromActivity(){
        /*EditTextから価格と税率などの取得*/
        int mNum = AshiComClass.editText2Int((EditText) findViewById(R.id.Price_EditText));
        double mTaxPercent = AshiComClass.editText2Double((EditText) findViewById(R.id.TaxPercent_EditText));

        //パーツの情報取得
        TextView mAmountPriceTextView = (TextView)findViewById(R.id.AmountPrice_Te);
        TextView mTaxTextView = (TextView)findViewById(R.id.Tax_TextView);
        RadioGroup _priceType_RG = (RadioGroup)findViewById(R.id.PriceTypeGroup_RadioGroup);



        switch(_priceType_RG.getCheckedRadioButtonId()){
            case R.id.PriceWithoutTax_RadioButton:
                //税額
                mTaxTextView.setText(
                        String.valueOf(AshiComClass.calcTaxFromPriceWithoutTax(mNum,mTaxPercent,this)));
                //税込み価格
                mAmountPriceTextView.setText(
                        String.valueOf(AshiComClass.calcPriceWithTaxFromPriceWithoutTax(mNum,mTaxPercent,this)));

                return;

            case R.id.PriceWithTax_RadioButton:
                //税額計算
                mTaxTextView.setText(
                        String.valueOf(AshiComClass.calcTaxFromPriceWithTax(mNum,mTaxPercent,this))
                );
                //税込価格
                mAmountPriceTextView.setText(
                        String.valueOf(mNum)
                );

                return;
            case R.id.TaxFree_RadioButton:
                //税額計算
                mTaxTextView.setText(String.valueOf(0));
                //税込み価格
                mAmountPriceTextView.setText(
                        String.valueOf(mNum)
                );

                return;
            default:

        }

        RadioButton _priceWithTax_RB = (RadioButton)findViewById(R.id.PriceWithTax_RadioButton);
        RadioButton _priceWithoutTax_RB = (RadioButton)findViewById(R.id.PriceWithoutTax_RadioButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_store_item);

        final EditText priceText = (EditText)findViewById(R.id.Price_EditText);
        priceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(0==priceText.length()) {
                    priceText.setText("0");
                    priceText.selectAll();
                }
                _calcAndSet_TaxAndPriceWithTaxFromActivity();
            }
        });

        final RadioGroup mRadioGroup = (RadioGroup)findViewById(R.id.PriceTypeGroup_RadioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                _calcAndSet_TaxAndPriceWithTaxFromActivity();
            }
        });

        final EditText taxEditText = (EditText)findViewById(R.id.TaxPercent_EditText);
        taxEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(0==taxEditText.length()){
                    taxEditText.setText("0");
                    taxEditText.selectAll();
                }
                _calcAndSet_TaxAndPriceWithTaxFromActivity();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_store_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 商品コードを入力後、商品を検索するボタン
     * @param vieww
     */
    public void onClick_EnterButton(View vieww){



        /* 商品を表示する */
        AshiComClass.showToastMessage(this,getString(R.string.No_Item),false);
    }


}
