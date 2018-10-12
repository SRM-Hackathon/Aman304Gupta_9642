package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface buyApi {
    String BASE_URL="http://13.126.150.180:3000/";
    @FormUrlEncoded
    @POST("buyFrom")
    Call<SuccessResponse> getResponse(@Field("sellerAddress") String sellerAddress,
                                      @Field("value") int value,
                                      @Field("wallet") String wallet,
                                      @Field("resident_address") String address);
}
