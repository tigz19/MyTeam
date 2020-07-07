package com.kocha.myteam.system.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Networking {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public CurrenciesApi currenciesApi = retrofit.create(CurrenciesApi.class);
}
