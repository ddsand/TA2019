package com.app.markeet.connection;

import com.app.markeet.connection.callbacks.CallbackCatSpinner;
import com.app.markeet.connection.callbacks.CallbackCategory;
import com.app.markeet.connection.callbacks.CallbackDetailOrder;
import com.app.markeet.connection.callbacks.CallbackDetailProduct;
import com.app.markeet.connection.callbacks.CallbackDevice;
import com.app.markeet.connection.callbacks.CallbackFeaturedNews;
import com.app.markeet.connection.callbacks.CallbackInProduct;
import com.app.markeet.connection.callbacks.CallbackInfo;
import com.app.markeet.connection.callbacks.CallbackListOrder;
import com.app.markeet.connection.callbacks.CallbackListUMKM;
import com.app.markeet.connection.callbacks.CallbackManualOrder;
import com.app.markeet.connection.callbacks.CallbackNewsInfo;
import com.app.markeet.connection.callbacks.CallbackNewsInfoDetails;
import com.app.markeet.connection.callbacks.CallbackOrder;
import com.app.markeet.connection.callbacks.CallbackPayment;
import com.app.markeet.connection.callbacks.CallbackProduct;
import com.app.markeet.connection.callbacks.CallbackProductDetails;
import com.app.markeet.connection.callbacks.CallbackRegistUMKM;
import com.app.markeet.connection.callbacks.CallbackSaldo;
import com.app.markeet.connection.callbacks.CallbackUmkmProduct;
import com.app.markeet.connection.callbacks.CallbackUnverifiedUMKM;
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
    String AGENT = "User-Agent: CE-Market";
    String SECURITY = "Security: " + Constant.SECURITY_CODE;

    /*Admin---*/
    @GET("services/processListOrder")
    Call<CallbackManualOrder> AllOrderManual();

    @FormUrlEncoded
    @POST("services/processUpOrder")
    Call<ResponseBody> upOrder(@Field("idorder") String idorder,
                               @Field("serialuser") String serialuser,
                               @Field("statusorder") String statusorder);

    @GET("services/processAllUMKM")
    Call<CallbackListUMKM> AllUMKM();

    @GET("services/processUnverified")
    Call<CallbackUnverifiedUMKM> UnverfiedUMKM();

    @FormUrlEncoded
    @POST("services/processUMKM")
    Call<ResponseBody> verifyUMKM(@Field("isistatus") String data,
                                    @Field("idumkm") String idumkm,
                                    @Field("serial") String serial);

    /*LOGIN-------------------------------------------------*/
    @FormUrlEncoded
    @POST("services/processLog")
    Call<ResponseBody> loginRequest(@Field("email") String username,
                                    @Field("password") String pass,
                                    @Field("serial") String serial);
    @FormUrlEncoded
    @POST("services/LogoutApi")
    Call<ResponseBody> logoutRequest(@Field("id") String iduser);

    @FormUrlEncoded
    @POST("services/processRegist")
    Call<ResponseBody> registerRequest(@Field("password") String password,
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
    @FormUrlEncoded
    @POST("services/ManualPay")
    Call<ResponseBody> ManualPayment(@Field("order_id") String order_id,
                                    @Field("pay") String payment,
                                     @Field("rekening") String rekening,
                                     @Field("namarekening") String namarekening);

    /* Saldo cek===========================================*/

    @Headers({CACHE, AGENT, SECURITY})
    @FormUrlEncoded
    @POST("services/processSaldo")
    Call<CallbackSaldo> checkSaldo(@Field("id") String iduser);

    /*--------------------------------------------------------*/
    @FormUrlEncoded
    @POST("services/allOrder")
    Call<CallbackListOrder> checkOrder(@Field("iduser") String iduser,
                                       @Field("isi") String isi);

    @FormUrlEncoded
    @POST("services/detailOrder")
    Call<CallbackDetailOrder> detOrder(@Field("order_id") String order_id,
                                       @Field("iduser") String iduser);
    @FormUrlEncoded
    @POST("services/processSet")
    Call<ResponseBody> setOrder(@Field("order_id") String order_id,
                                @Field("iduser") String iduser,
                                @Field("serial") String serial);
    /* Registrasi UMKM ------------------------------------------------*/
    @Multipart
    @POST("services/registasiUMKM")
    Call<CallbackRegistUMKM> registUMKM(@Part MultipartBody.Part file,
                                        @Part("fotoktp") RequestBody fotoktp,
                                        @Part("iduser") RequestBody iduser,
                                        @Part("namausaha") RequestBody namausaha,
                                        @Part("noktp") RequestBody noktp,
                                        @Part("deskripsi") RequestBody deskripsi
                                        );


    /*PRODUCT CRUD-----------------------------------------------------------------*/
    @FormUrlEncoded
    @POST("services/allproductuser")
    Call<CallbackUmkmProduct> Allproduct(@Field("iduser") String userid);

    @FormUrlEncoded
    @POST("services/productUser")
    Call<CallbackDetailProduct> DetailProduct(@Field("iduser") String userid,
                                              @Field("id") String idproduk);
    @FormUrlEncoded
    @POST("services/delsProduct")
    Call<ResponseBody> hapusProduct(@Field("id") String idproduk);

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
