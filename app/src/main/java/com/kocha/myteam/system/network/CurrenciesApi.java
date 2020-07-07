package com.kocha.myteam.system.network;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrenciesApi {

    @GET("latest")
    Single<CurrencyModel> getDollarRate(@Query("base") String base, @Query("symbols") String symbols);
}
