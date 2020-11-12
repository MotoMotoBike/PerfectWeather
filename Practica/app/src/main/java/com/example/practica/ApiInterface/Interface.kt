package ApiInterface

import com.example.practica.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

//var city = "London"

interface Interface {
    @GET("weather?q=Moscow&appid=feb0cd768c865126b95d4f3c4c1fb041")
    fun  getFile(/*@Path("city") city : String*/): Call<Weather>
}
