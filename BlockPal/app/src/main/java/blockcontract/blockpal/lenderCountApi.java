package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface lenderCountApi {

    String Base_Url="http://13.233.83.25:7000/";
    @FormUrlEncoded

    @POST("borrower/list/read")
    Call<lenderCountPojo> getCount(@Field("userid")String userid);
}
