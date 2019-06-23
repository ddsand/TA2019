package com.app.markeet.connection;

import com.app.markeet.connection.callbacks.CallbackCatSpinner;
import com.app.markeet.connection.callbacks.CallbackCategory;
import com.app.markeet.connection.callbacks.CallbackDevice;
import com.app.markeet.connection.callbacks.CallbackFeaturedNews;
import com.app.markeet.connection.callbacks.CallbackInProduct;
import com.app.markeet.connection.callbacks.CallbackInfo;
import com.app.markeet.connection.callbacks.CallbackNewsInfo;
import com.app.markeet.connection.callbacks.CallbackNewsInfoDetails;
import com.app.markeet.connection.callbacks.CallbackOrder;
import com.app.markeet.connection.callbacks.CallbackPayment;
import com.app.markeet.connection.callbacks.CallbackProduct;
import com.app.markeet.connection.callbacks.CallbackProductDetails;
import com.app.markeet.connection.callbacks.CallbackRegistUMKM;
import com.app.markeet.connection.callbacks.CallbackSaldo;
import com.app.markeet.data.Constant;
import com.app.markeet.model.Checkout;
import com.app.markeet.model.DeviceInfo;
import com.app.markeet.model.ListCategory;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface API {

    String CACHE = "Cache-Control: max-age=0";
    String AGENT = "User-Agent: Markeet";
    String SECURITY = "Security: " + Constant.SECURITY_CODE;

    /*LOGIN-------------------------------------------------*/
    @FormUrlEncoded
    @POST("services/processLog")
    Call<ResponseBody> loginRequest(@Field("email") String username,
                                    @Field("password") String pass);
    @FormUrlEncoded
    @POST("services/LogoutApi")
    Call<ResponseBody> logoutRequest(@Field("id") String iduser);

    @FormUrlEncoded
    @POST("services/processRegist")
    Call<ResponseBody> registerRequest(@Field("user") String username,
                                       @Field("password") String password,
                                       @Field("name") String name,
                                       @Field("email") String email,
                                       @Field("addr") String addr,
                                       @Field("gender") String gender,
                                       @Field("birth") String birth);

    /* Recipe API transaction ------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/info")
    Call<CallbackInfo> getInfo(
            @Query("version") int version
    );

    /* Fcm API ----------------------------------------------------------- */
    @Headers({CACHE, AGENT, SECURITY})
    @POST("services/insertOneFcm")
    Call<CallbackDevice> registerDevice(
            @Body DeviceInfo deviceInfo
    );

    /* News Info API ---------------------------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/listFeaturedNews")
    Call<CallbackFeaturedNews> getFeaturedNews();

    @Headers({CACHE, AGENT})
    @GET("services/listNews")
    Call<CallbackNewsInfo> getListNewsInfo(
            @Query("page") int page,
            @Query("count") int count,
            @Query("q") String query
    );

    @Headers({CACHE, AGENT})
    @GET("services/getNewsDetails")
    Call<CallbackNewsInfoDetails> getNewsDetails(
            @Query("id") long id
    );

    /* Category API ---------------------------------------------------  */
    @Headers({CACHE, AGENT})
    @GET("services/listCategory")
    Call<CallbackCategory> getListCategory();


    @Headers({CACHE, AGENT})
    @GET("services/listCategory")
    Call<CallbackCatSpinner> getCat();


    /* Product API ---------------------------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/listProduct")
    Call<CallbackProduct> getListProduct(
            @Query("page") int page,
            @Query("count") int count,
            @Query("q") String query,
            @Query("category_id") long category_id
    );

    @Headers({CACHE, AGENT})
    @GET("services/getProductDetails")
    Call<CallbackProductDetails> getProductDetails(
            @Query("id") long id
    );

    /* Checkout API ---------------------------------------------------- */
    @Headers({CACHE, AGENT, SECURITY})
    @POST("services/submitProductOrder")
    Call<CallbackOrder> submitProductOrder(
            @Body Checkout checkout
    );
    @FormUrlEncoded
    @POST("services/requestEzpy")
    Call<ResponseBody> EzpayPayment(@Field("order_id") String order_id,
                                    @Field("email") String email,
                                    @Field("name") String name,
                                    @Field("method") String method);

    @FormUrlEncoded
    @POST("services/requestEzpy")
    Call<CallbackPayment> VirtualPayment(@Field("order_id") String order_id,
                                         @Field("email") String email,
                                         @Field("name") String name,
                                         @Field("method") String method);
    /* Saldo cek===========================================*/

    @Headers({CACHE, AGENT, SECURITY})
    @FormUrlEncoded
    @POST("services/processSaldo")
    Call<CallbackSaldo> checkSaldo(@Field("id") String iduser);

    /* Registrasi UMKM ------------------------------------------------*/

    @Multipart
    @POST("service/registasiUMKM")
    Call<CallbackRegistUMKM> registUMKM(@Part MultipartBody.Part file,
                                        @Part("fotoktp") RequestBody fotoktp,
                                        @Part("iduser") RequestBody iduser,
                                        @Part("namausaha") RequestBody namausaha,
                                        @Part("noktp") RequestBody noktp
                                        );
    /*PRODUCT CRUD-----------------------------------------------------------------*/
    @Multipart
    @POST("services/insProduct")
    Call<CallbackInProduct> uploadFile(@Part MultipartBody.Part file,
                                       @Part("image") RequestBody image,
                                       @Part("name") RequestBody productname,
                                       @Part("price") RequestBody price,
                                       @Part("price_discount") RequestBody price_discount,
                                       @Part("stock") RequestBody stock,
                                       @Part("description") RequestBody description,
                                       @Part("status") RequestBody status,
                                       @Part("iduser") RequestBody iduser,
                                       @Part("category") RequestBody category);

}
