package blockcontract.blockpal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.ramotion.fluidslider.FluidSlider;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class sell extends Fragment {
    int quantity;
    Boolean success;
    String count,s1,s2,s3,s4,s5,s6;
    int count_int,x;
    RecyclerView recyclerView;
    lenderAdapter adapter;
    List<lender> lenderList;
    User user;
    public sell(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sell, container, false);
        Button sell=view.findViewById(R.id.sell_button);
        lenderList=new ArrayList<>();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        Hawk.init(getContext()).build();
        user=new User();
        user=Hawk.get("user");
        System.out.println(user.getEmail());

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_sell);

                final int max = 130;
                final int min = 10;
                final int total = max - min;

                final FluidSlider slider = dialog.findViewById(R.id.slider);
                slider.setBeginTrackingListener(new Function0<Unit>() {
                    @Override
                    public Unit invoke() {
                        return Unit.INSTANCE;
                    }
                });

                slider.setEndTrackingListener(new Function0<Unit>() {
                    @Override
                    public Unit invoke() {
                        return Unit.INSTANCE;
                    }
                });

                // Java 8 lambda
                slider.setPositionListener(pos -> {
                    final String value = String.valueOf((int) (min + total * pos));
                    slider.setBubbleText(value);
                    quantity= Integer.parseInt(value);
                    return Unit.INSTANCE;
                });
                Button sell=dialog.findViewById(R.id.sell_button);
                EditText name=dialog.findViewById(R.id.name);
                EditText startupname=dialog.findViewById(R.id.startupname);
                EditText startupidea=dialog.findViewById(R.id.startupidea);
                EditText linkedin_url=dialog.findViewById(R.id.linkedin);
                Button upload=dialog.findViewById(R.id.upload_button);
                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });
                sell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(sell_api.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                .build();

                        sell_api api=retrofit.create(sell_api.class);

                        if(Hawk.contains("user")){
                            Toast.makeText(getContext(),"No value", Toast.LENGTH_LONG).show();
                        }
                        System.out.println(user.getWallet_address());
                        Call<SuccessResponse> call=api.getResponse(name.getText().toString(),startupname.getText().toString(),startupidea.getText().toString(),linkedin_url.getText().toString(),quantity);
                        call.enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                                SuccessResponse successResponse=response.body();
                                if(successResponse.getSuccess())
                                    Toast.makeText(getContext(),"Item successfully added for sale!!", Toast.LENGTH_LONG).show();
                                //now we can do whatever we want with this list

                            }

                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                System.out.println(quantity);
                slider.setPosition(0.3f);
                slider.setStartText(String.valueOf(min));
                slider.setEndText(String.valueOf(max));
                dialog.show();

            }
        });
//        Retrofit retrofit1 = new Retrofit.Builder()
//                .baseUrl(lenderCountApi.Base_Url)
//                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                .build();
//
//        lenderCountApi api1=retrofit1.create(lenderCountApi.class);
//        Call<lenderCountPojo> call1 = api1.getCount(user.getEmail());
//        call1.enqueue(new Callback<lenderCountPojo>() {
//            @Override
//            public void onResponse(Call<lenderCountPojo> call, Response<lenderCountPojo> response) {
//
//                lenderCountPojo lenderCountPojo=response.body();
//                Toast.makeText(getActivity().getApplicationContext(),"reached",Toast.LENGTH_SHORT).show();
//                success = lenderCountPojo.getSuccess();
//                System.out.println(lenderCountPojo.getData().get(1)+"hello world"+lenderCountPojo.getData().size());
//            count_int=lenderCountPojo.getData().size();
//
//                System.out.println("/nhello world ---------------/n"+count_int);
//                Retrofit retrofit2 = new Retrofit.Builder()
//                        .baseUrl(lenderApi.Base_Url)
//                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                        .build();
//                lenderApi api2=retrofit2.create(lenderApi.class);
//                for(int i=0;i<count_int;i++)
//                { String id="0x80900317AF1CD10b4277CA76aC48AF8b932E674A";//hcode here
//                    Call<lenderPojo> call2=api2.getLender(id);
//                    call2.enqueue(new Callback<lenderPojo>() {
//                        @Override
//                        public void onResponse(Call<lenderPojo> call, Response<lenderPojo> response) {
//                            lenderPojo lenderPojo=response.body();
//                            s1=lenderPojo.getData().get0();
//                            s2=lenderPojo.getData().get1();
//                            s3=lenderPojo.getData().get2();
//                            s4=lenderPojo.getData().get3();
//                            lenderList.add(new lender(s1,s2,s3,s4));
//                            adapter=new lenderAdapter(getActivity().getApplicationContext(),lenderList);//getActivity();
//                            recyclerView.setAdapter(adapter);
//
//
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<lenderPojo> call, Throwable t) {
//
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<lenderCountPojo> call, Throwable t) {
//
//            }
//        });
//
//
        return view;

    }
}
