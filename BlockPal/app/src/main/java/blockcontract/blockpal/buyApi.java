package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface buyApi {
    String BASE_URL="http://13.233.83.25:7000/";
    @FormUrlEncoded
    @POST("loanterms/create")
    Call<SuccessResponse> getResponse(@Field("userid") int userid,
                                      @Field("period") int period,
                                      @Field("Rinterest") int Rinterest);
}
