package com.technosp.livecurrencyconvertor.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.technosp.livecurrencyconvertor.Models.CurrencyModel
import com.technosp.livecurrencyconvertor.R
import com.technosp.livecurrencyconvertor.Utils.CurrencyApiUtil
import com.technosp.livecurrencyconvertor.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var selectedOutputOption: String=""

    private var selectedInputOption: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val currencies = listOf("AUD", "BGN", "BRL", "CAD", "CHF","CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR")

        val inputAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)

        val outputAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)

        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        outputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.currencyInputOptions.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedInputOption=currencies[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {




            }

        }

        binding.currencyOutputOptions.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedOutputOption=currencies[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {


                selectedOutputOption=currencies[0]

            }

        }


        binding.currencyInputOptions.adapter = inputAdapter

        binding.currencyOutputOptions.adapter = outputAdapter


        binding.currencyConvert.setOnClickListener {

            CurrencyApiUtil.getCurrentApiInterface()
                ?.getCurrentCurrencyData(
                    "fca_live_5XelowiAeLxhqrlLbIhORaBchse6KfYW98bgis7e",
                    selectedOutputOption,
                    selectedInputOption
                )?.enqueue(object : Callback<CurrencyModel>{
                    override fun onResponse(
                        call: Call<CurrencyModel>,
                        response: Response<CurrencyModel>
                    ) {

                        if (response.isSuccessful){

                            val rates= response.body()?.rates

                            val finalRate= rates?.get(selectedOutputOption)

                            val finalOutput="${(finalRate?.toFloat()!! *binding.currencyInput.text.toString().toFloat())} $selectedOutputOption"

                            binding.currencyOutput.text= finalOutput

                        }


                    }

                    override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {



                    }

                })


        }





    }
}