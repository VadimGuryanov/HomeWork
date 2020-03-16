package kpfu.itis.task2.network

import kpfu.itis.task2.response.WeatherResponse
import kpfu.itis.task2.response.WeatherResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String) : WeatherResponse

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int) : WeatherResponse

    @GET("find")
    suspend fun weathersCitiesInCycle(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int
    ) : WeatherResponseList

}
