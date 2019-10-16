package com.techease.aidenassign2application.networking;

import com.techease.aidenassign2application.model.AddPatientResponseModel;
import com.techease.aidenassign2application.model.AddTestResponseModel;
import com.techease.aidenassign2application.model.GetByPatientId.GetByPatientResponseModel;
import com.techease.aidenassign2application.model.getAllPatientsModel.AllPatientResponseModel;
import com.techease.aidenassign2application.model.getPatientTest.GetPatientTestResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface

APIServices {


    @FormUrlEncoded
    @POST("addPatient?")
    Call<AddPatientResponseModel> addPatient(@Field("name") String name,
                                                      @Field("nurseid") String nurseId,
                                                      @Field("roomnum") String roomNum,
                                                      @Field("department") String department,
                                                      @Field("gender") String gender);



    @FormUrlEncoded
    @POST("addTest")
    Call<AddTestResponseModel> addTest(@Field("patient_id") String patientId,
                                       @Field("pressure") String pressure,
                                       @Field("cholesterol") String cholesterol,
                                       @Field("temperature") String temperature,
                                       @Field("date") String date);




    @GET("seeAllPatients")
    Call<AllPatientResponseModel> allPatients();



    @GET("healthid")
    Call<GetPatientTestResponseModel> getByPatientTest(@Query("patient_id") String patientId);



    @GET("patientid")
    Call<GetByPatientResponseModel> getByPatientId(@Query("nurseid") String patientId);


    @GET("department")
    Call<GetByPatientResponseModel> getByPatientDepartment(@Query("department") String patientDepartment);
//



//    @FormUrlEncoded
//    @POST("login")
//    Call<LoginResponseModel> userLogin(@Field("email") String email,
//                                       @Field("password") String password,
//                                       @Field("deviceToken") String deviceToken);
//
//
//    @Multipart
//    @POST("register")
//    Call<SignUpResponseModel> userSignUp(@Part("name") RequestBody name,
//                                         @Part("email") RequestBody email,
//                                         @Part("password") RequestBody password,
//                                         @Part("confirmPassword") RequestBody passwordConfirmation,
//                                         @Part("phoneNumber") RequestBody phoneNumber,
//                                         @Part("deviceType") RequestBody deviceType,
//                                         @Part("deviceToken") RequestBody deviceToken,
//                                         @Part("planId") RequestBody planId,
//                                         @Part("adults") RequestBody adults,
//                                         @Part("kidsUnder14") RequestBody kidsUder14,
//                                         @Part MultipartBody.Part photo,
//                                         @Part("profilePicture") RequestBody fileName);
//
//
//
//    @GET("recipe-detail?")
//    Call<RecipeDetailResponseModel> recipeDetail(@Query("recipeId") int id);
//
//
//    @GET("user-profile")
//    Call<GetProfileResponseModel> profile();
//
//
//    @GET("get-favouite-recipies")
//    Call<GetFavouriteResponseModel> favourite();
//
//
//    @FormUrlEncoded
//    @POST("update-favouite-recipe-status")
//    Call<BaseResponse> favoriteRecipe(@Field("recipeId") String recipeId,
//                                      @Field("favouriteStatus") String favoriteStatus);
//
//
//    @Multipart
//    @POST("update-profile-picture")
//    Call<ProfileUpdateResponseModel> updateProfilePic(@Part MultipartBody.Part photo,
//                                                      @Part("profilePicture") RequestBody fileName);
//
//
//    @FormUrlEncoded
//    @POST("update-profile")
//    Call<ProfileUpdateResponseModel> updateProfile(@Field("name") String name,
//                                                   @Field("phoneNumber") String phoneNumber,
//                                                   @Field("location") String location,
//                                                   @Field("postalCode") String postalCode,
//                                                   @Field("gender") String gender,
//                                                   @Field("adults") String adults,
//                                                   @Field("kidsUnder14") String kidsUnder14);
//
//

//
//    @FormUrlEncoded
//    @POST("reset-password")
//    Call<ForgotPasswordResponseModel> forgotPassword(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("reset-password-verify")
//    Call<VerifyCodeResponseModel> verifyCode(@Field("email") String email,
//                                             @Field("code") String code);
//
//
//    @FormUrlEncoded
//    @POST("change-password")
//    Call<ChangePasswordResponseModel> changePassword(@Field("newPassword") String newPassword,
//                                                     @Field("confirmPassword") String confirmPassword);
//
//
//    @FormUrlEncoded
//    @POST("update-notification-status")
//    Call<NotificationResponseModel> notificationStatus(@Field("status") String notificationStatus);
//
//
//    @POST("deactive-account")
//    Call<DeactiveResponseModel> deactiveAccount();
//
//
//    @FormUrlEncoded
//    @POST("contact-us")
//    Call<ContactUsResponseModel> contactUs(@Field("description") String description);
//
//
//    @GET("get-super-markets")
//    Call<GetSuperMarketResponseModel> getSuperMarket();
//
//
//    @GET("recipe-ingredients?")
//    Call<GetSpecificRecipeIngredientsResponseModel> specificRecipeIngredients(@Query("recipeId") int id);
//
//
//    @FormUrlEncoded
//    @POST("shoping")
//    Call<ShoppingResponseModel> shopping(@Field("superMarket") String superMarket,
//                                         @Field("postalCode") String strPostalCode,
//                                         @Field("shippingAddress") String shoppingAddress,
//                                         @Field("items") String items);
//
//
//    @FormUrlEncoded
//    @POST("rating")
//    Call<UpdateRecipeRatingResponseModel> updateRecipeRating(@Field("recipeId") int recipeId,
//                                                             @Field("rating") float rating);
//
//
//    @GET("orders")
//    Call<GetOrderResponseModel> getMyOrder();
//
//
//    @GET("ingredients-list")
//    Call<GetAllIngredientsWithOutCategoryResponseModel> getSpecificIngredientsWithOutCategory();
//
//
//    @GET("ingredients-list?")
//    Call<GetSpecificIngredientsWithOutCategoryResponseModel> specificRecipeIngredientsWithOutCategory(@Query("recipeId") int id);
//
//
//    @FormUrlEncoded
//    @POST("swipe-or-delete-recipe")
//    Call<SwipeRecipeResponseModel> swipeOrDelete(@Field("recipeId") int recipeId, @Field("type") String swipeType);


}
