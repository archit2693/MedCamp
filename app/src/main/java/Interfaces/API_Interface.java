package Interfaces;


import model.result;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Iron_Man on 04/02/17.
 */

public interface API_Interface {

    String URL_BASE = "http://tiiciiitm.com/medcamp/index.php/api/";



    @GET("verifyuser2/{username}/{password}")
    Call<result> getCommitsByName(@Path("username") String name,@Path("password") String password);

    @GET("newfeedback/{name}/{gender}/{age}/{q1}/{q2}/{q3}/{q4}/{q5}/{q6}/{q7}/{q8}/{q9}/{vol}/{contact}/{address}")
    Call<result> getFeedback(@Path("name") String name,@Path("gender") String gender,@Path("age") String age,@Path("q1") String q1,@Path("q2") String q2,@Path("q3") String q3,@Path("q4") String q4,@Path("q5") String q5,@Path("q6") String q6,@Path("q7") String q7,@Path("q8") String q8,@Path("q9") String q9,@Path("vol") String vol,@Path("contact") String contact,@Path("address") String address);

    class Factory {

        private static API_Interface service;

        public static API_Interface getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL_BASE)
                        .build();

                service = retrofit.create(API_Interface.class);
                return service;
            } else {
                return service;
            }
        }
    }


}
