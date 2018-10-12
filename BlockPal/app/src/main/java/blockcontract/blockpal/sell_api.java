package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface sell_api {
    String BASE_URL="http://13.233.83.25:7000/";
    @FormUrlEncoded
    @POST("borrower/create")
    Call<SuccessResponse> getResponse(@Field("name") String name,
                                      @Field("StartUpName") String StartUpName,
                                      @Field("StartUpIdea")String StartUpIdea,
                                      @Field("LinkedInUrl")String LinkedInUrl,
                                      @Field("amt") int amt);
}
