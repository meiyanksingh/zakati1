package com.mks.zakati.networkapi;


import com.mks.zakati.models.events.ListResp;
import com.mks.zakati.models.events.res.SignUpRes;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by rahil on 9/9/15.
 */
public interface Apis {

    @Multipart
    @POST(ApiConstants.SIGNUP)
    Call<ListResp<SignUpRes>> signUP(@Part("profile_pic\"; filename=\"profile.jpg") RequestBody profilePic, @PartMap Map<String,Object> map);

//    @POST(ApiConstants.REGISTRATION)
//    Call<ObjResp<RegisterRes>> signUp(@Body SignUpReq req);
//
//    @POST(ApiConstants.LOGIN)
//    Call<ObjResp<LoginRes>> login(@Body LoginReq req);
//
//    @Multipart
//    @POST(ApiConstants.SALON_DETAILS)
//    Call<GeneralResp> salonDetails(@Part("business_logo\"; filename=\"abc.jpg") RequestBody businessLogo, @Part("business_cover_pic\"; filename=\"xyz.jpg") RequestBody businessCoverPic, @Part("json") SalonDetailsReq req);
//
//    @POST(ApiConstants.PRICE_LIST)
//    Call<GeneralResp> priceList(@Body PriceListReq req);
//
//    @POST(ApiConstants.GET_SERVICE_NAME)
//    Call<ListResp<ServiceName>> getServiceName(@Body GeneralReq req);
//
//    @Multipart
//    @POST(ApiConstants.ADD_CLIPAZ)
//    Call<ObjResp<AddClipazResp>> addClipaz(@Part("profilePic\"; filename=\"abc.jpg") RequestBody profilePic, @Part("coverPic\"; filename=\"xyz.jpg") RequestBody businessCoverPic, @Part("json") AddClipazReq req);
//
//    @POST(ApiConstants.APPOINTMENT_DETAILS)
//    Call<ListResp<AppointmentDetailRes>> getAppointmentDetails(@Body GeneralReq req);
//
//    @POST(ApiConstants.GET_NEXT_AVAILABLE_TIME)
//    Call<ListResp<NextAvailableTimeRes>> getNextAvailTime(@Body GeneralReq req);
//
//    @POST(ApiConstants.CANCEL_APPOINTMENT)
//    Call<GeneralResp> cancelAppointment(@Body GeneralReq req);
//
//    @POST(ApiConstants.MOVE_APPOINTMENT)
//    Call<GeneralResp> moveAppointment(@Body GeneralReq req);
//
//    @POST(ApiConstants.INSIGHT_REPORT)
//    Call<InsightRes> insightReport(@Body GeneralReq req);
//
//    @POST(ApiConstants.TRIMSPIRATION_IMAGE)
//    Call<ListResp<TrimspirationImgRes>> getTrimspirationImg(@Body GeneralReq req);
//
//    @Multipart
//    @POST(ApiConstants.UPLOAD_IMAGE)
//    Call<ObjResp<UploadImageRes>> uploadImg(@Part("image\"; filename=\"abc.jpg") RequestBody uploadImage, @Part("json") GeneralReq req);
//
//    @POST(ApiConstants.DELETE_TRIM_IMAGE)
//    Call<GeneralResp> deleteTrimImage(@Body GeneralReq req);
//
//    @POST(ApiConstants.GET_PRICE_LIST)
//    Call<ListResp<GetPriceListRes>> getPriceList(@Body GeneralReq req);
//
//    @POST(ApiConstants.UPDATE_PRICE)
//    Call<GeneralResp> updatePrice(@Body UpdatePriceListReq req);
//
//    @POST(ApiConstants.GET_SALOON_DETAIL)
//    Call<ObjResp<SaloonDetailRes>> getSaloonDetail(@Body GeneralReq req);
//
//    @POST(ApiConstants.UPDATE_PIN)
//    Call<GeneralResp> changePasswdApi(@Body GeneralReq req);
//
//    @POST(ApiConstants.GET_OFFER)
//    Call<ObjResp<GetOfferRes>> getOffer(@Body GeneralReq req);
//
//    @POST(ApiConstants.UPDATE_OFFER)
//    Call<GeneralResp> updateOffer(@Body GeneralReq req);
//
//    @POST(ApiConstants.FORGOT_PASSWORD)
//    Call<GeneralResp> forgotPassword(@Body GeneralReq req);
//
//    @POST(ApiConstants.REMOVE_CLIPAZ)
//    Call<GeneralResp> removeClipaz(@Body GeneralReq req);
//
//    @POST(ApiConstants.PROFILE_DETAILS)
//    Call<ObjResp<ProfileDetailRes>> profileDetail(@Body GeneralReq req);
//
//    @POST(ApiConstants.GET_CLIPAZ)
//    Call<ListResp<GetClipazRes>> getClipaz(@Body GeneralReq req);
//
//    @POST(ApiConstants.GET_CLIPAZ_DETAIL)
//    Call<ObjResp<GetClipazDetailRes>> getClipazDetail(@Body GeneralReq req);



}

