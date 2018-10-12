package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.POST;

public interface borrowerCountApi {

    String Base_Url="http://13.233.83.25:7000/";
    @POST("borrower/count")
    Call<borrowerCountPojo> getCount();
}
