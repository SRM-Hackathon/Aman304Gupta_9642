package blockcontract.blockpal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class profile extends Fragment {
    Button sign_out,view_stats;
    Boolean success;
    String s1;
    Double bal;
    TextView wallet_address,username,wallet_balance;
    int amm=0;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        Hawk.init(getContext()).build();
        sign_out=view.findViewById(R.id.sign_out);
        wallet_address=view.findViewById(R.id.wallet_address);
        username=view.findViewById(R.id.user_name);
        wallet_balance=view.findViewById(R.id.water_bal);
        User user=Hawk.get("user");
        wallet_address.setText(user.getWallet_address());
        s1=user.getWallet_address();//to pass this in the profile field
        username.setText(user.getUsername());

        Retrofit retrofit3 = new Retrofit.Builder()
                .baseUrl(amountget.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        amountget api3=retrofit3.create(amountget.class);
        Call<amountresponse> call3=api3.getResponse(user.getEmail());
        call3.enqueue(new Callback<amountresponse>() {
            @Override
            public void onResponse(Call<amountresponse> call, Response<amountresponse> response) {

                amountresponse amountresponse=response.body();
                Data1 data=amountresponse.getData1();
                amm=data.getAmount();

                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<amountresponse> call, Throwable t) {
//                Toast.makeText(mcx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        wallet_balance.setText(String.valueOf(amm)+" ETH");
        view_stats=view.findViewById(R.id.view_stats);
        view_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.activity_stats);
                /**
                 * Graph
                 */

                Calendar calendar = Calendar.getInstance();
                String date1="28/8/2018";
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date d1 = null;
                try {
                    d1 = sdf.parse(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.add(Calendar.DATE, 1);
                Date d2 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d3 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d4 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d5 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d6 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                GraphView graph = (GraphView) dialog.findViewById(R.id.graph);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(d1, 1),
                        new DataPoint(d2, 5),
                        new DataPoint(d3, 3),
                        new DataPoint(d4,8),
                        new DataPoint(d5,4),
                        new DataPoint(d6,2)
                });
                graph.addSeries(series);
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getContext()));
                graph.getGridLabelRenderer().setNumHorizontalLabels(3);
                graph.getGridLabelRenderer().setHumanRounding(false);// only 4 because of the space

// set manual x bounds to have nice steps
                graph.getViewport().setMinX(d1.getTime());
                graph.getViewport().setMaxX(d3.getTime());
                graph.getViewport().setScalable(true);
                graph.getViewport().setScalableY(true);
                graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
                graph.getGridLabelRenderer().setHumanRounding(false);
                Button close=dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
//                Intent intent=new Intent(getActivity().getApplicationContext(),Stats.class);
//                startActivity(intent);
            }
        });
        return view;
    }
}