package com.example.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.kotlinapp.controller.MyAdapter
import com.example.kotlinapp.model.CryptoCurrencies
import com.example.kotlinapp.networking.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MyAdapter.Listener {

    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var myCryptoList: ArrayList<CryptoCurrencies>? = null
    private val BASE_URL = "https://api.nomics.com/v1/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()

    }

    // Initialise the RecyclerView

    private fun initRecyclerView() {
        // Use a layout manager to position your items to look a standard ListView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        cryptocurrency_list.layoutManager = layoutManager

    }

    /**Implement loadData*/

    private fun loadData() {

        /**Define retrofit request*/
        val requestInterface = Retrofit.Builder()
            //Set the API’s base URL//
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)
        myCompositeDisposable?.add(
            requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )


    }

    private fun handleResponse(cryptoList: List<CryptoCurrencies>) {

        myCryptoList = ArrayList(cryptoList)
        myAdapter = MyAdapter(myCryptoList!!, this)

        cryptocurrency_list.adapter = myAdapter
    }

    override fun onItemClick(cryptoCurrencies: CryptoCurrencies) {
        Toast.makeText(this, "You Clicked : ${cryptoCurrencies.currency}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        myCompositeDisposable?.clear()
    }
}
