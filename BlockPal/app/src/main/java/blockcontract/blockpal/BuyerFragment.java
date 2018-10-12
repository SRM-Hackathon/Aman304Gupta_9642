package blockcontract.blockpal;
/*will be showing all the sellers available*/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyerFragment extends Fragment {
    public BuyerFragment(){

    }
    Boolean success;
    String count,s1,s2,s3,s4,s5,s6;
    int count_int;
    RecyclerView recyclerView;
    productAdapter adapter;
    List<borrower> borrowersList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_seller,container,false);
        borrowersList=new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        /* To fetch count from server--------------------*/
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(countApi.Base_url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        countApi api = retrofit.create(countApi.class);
        Call<countPojo> call = api.getCount();
        call.enqueue(new Callback<countPojo>() {
            @Override
            public void onResponse(Call<countPojo> call, Response<countPojo> response) {
                countPojo countPojo=response.body();
                if(countPojo.getSuccess())
                {
                    success=countPojo.getSuccess();
                    count=countPojo.getCount();
                    System.out.println(count);
                    count_int= Integer.parseInt(count);
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(sellerApi.Base_Url)
                            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                            .build();
                    sellerApi api1 = retrofit1.create(sellerApi.class);
                    for(int i=count_int-1;i>=0;i--) {

                        Call<sellerPojo> call1 = api1.getSellers(i);
                        call1.enqueue(new Callback<sellerPojo>() {
                            @Override
                            public void onResponse(Call<sellerPojo> call, Response<sellerPojo> response) {
                                sellerPojo sellerPojo = response.body();
                                s1=sellerPojo.getUsername();
                                s2=sellerPojo.getSupply();
                                s3=sellerPojo.getWalletAddress();
                                System.out.println(s3);
                                borrowersList.add(new borrower(s1, s2, s3,s4,s5,s6));
                                adapter=new productAdapter(getActivity().getApplicationContext(),sellerList);
                                recyclerView.setAdapter(adapter);

                            }

                            @Override
                            public void onFailure(Call<sellerPojo> call, Throwable t) {



                            }
                        });

                    }


                }
            }

            @Override
            public void onFailure(Call<countPojo> call, Throwable t) {

            }
        });

           // sellerList.add(new seller("kishore", count, count));

        //the no of sellers
        /* ----------------------------------count fetched*/
        /*to fetch sellers according to id*/


        /*to add at last*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(borrowerCountApi.Base_Url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        final borrowerCountApi api = retrofit.create(borrowerCountApi.class);
         Call<borrowerCountPojo> call=api.getCount();
        call.enqueue(new Callback<borrowerCountPojo>() {
            @Override
            public void onResponse(Call<borrowerCountPojo> call, Response<borrowerCountPojo> response) {

                borrowerCountPojo borrowerCountPojo=response.body();
                if(borrowerCountPojo.getSuccess()) {
                    success = borrowerCountPojo.getSuccess();
                    count = borrowerCountPojo.getCount();
                    count_int=Integer.parseInt(count);
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(borrowerApi.Base_Url)
                            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                            .build();
                    borrowerApi api1=retrofit1.create(borrowerApi.class);
                    for(int i=0;i<count_int;i++)
                    {
                        Call<borrowerPojo> call1 = api1.getBorrower(i);
                        call1.enqueue(new Callback<borrowerPojo>() {
                            @Override
                            public void onResponse(Call<borrowerPojo> call, Response<borrowerPojo> response) {
                                borrowerPojo borrowerPojo=response.body();
                                s1=borrowerPojo.getData().get0();
                                s2=borrowerPojo.getData().get1();
                                s3=borrowerPojo.getData().get2();
                                s4=borrowerPojo.getData().get3();
                                s5=borrowerPojo.getData().get4();
                                s6=borrowerPojo.getData().get5();
                                borrowersList.add(new borrower(s1,s2,s3,s4,s5,s6));
                                adapter=new productAdapter(getActivity().getApplicationContext(),borrowersList);//getActivity();
                                recyclerView.setAdapter(adapter);


                            }

                            @Override
                            public void onFailure(Call<borrowerPojo> call, Throwable t) {

                            }
                        });
                    }


                }

            }


            @Override
            public void onFailure(Call<borrowerCountPojo> call, Throwable t) {

            }
        });

        return view;


    }

}

