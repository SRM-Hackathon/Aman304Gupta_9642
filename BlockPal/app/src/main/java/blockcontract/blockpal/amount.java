package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface amount {
    String BASE_URL="http://13.233.83.25:7000/";
    @FormUrlEncoded
    @POST("/api/amount/create")
    Call<SuccessResponse> getResponse(@Field("email")String email,
                                      @Field("amount")int amount
                                      );
}
