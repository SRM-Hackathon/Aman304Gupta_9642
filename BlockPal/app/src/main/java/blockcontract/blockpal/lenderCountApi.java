package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.POST;

public interface lenderCountApi {

    String Base_Url="http://13.233.83.25:7000/";
    @POST("lender/count")
    Call<lenderCountPojo> getCount();
}
