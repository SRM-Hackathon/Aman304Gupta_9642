package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface borrowerApi {

    String Base_Url="http://13.233.83.25:7000/";
    @POST("borrower/get/{id}")
    Call<borrowerPojo> getBorrower(@Path("id")int id);
}
