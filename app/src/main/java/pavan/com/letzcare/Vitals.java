package pavan.com.letzcare;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.List;
import java.util.Map;

public class Vitals extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String PREFS_NAME = "LoginPrefs";

    List<String> updatedList;

    ValueLineChart mCubicValueLineChart;
    ValueLineSeries series11;

    boolean p= true;
    Context ctx=this;
    Toolbar toolbar;
    Firebase myFirebaseRef;
    Button ecg,vitals,skype,dev;
    int k=0;
    private LineGraphSeries<DataPoint> series,series1;
    private int lastX = 0;
    GraphView graph , graph1;

    TextView BP,SP,PUL,TEMP,RES,COND,POS,GLU;



    double[] pqr= new double[1100];



    long  m=0;int i=0;

    int t=0;int g=0;

    Map<String,Object> value;


    double[] AirFlowVal={ 1,45,65,72,120,98,60,35,18,4,34,62,143,348,400};



    double[] ECGVal={
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,

            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794,
            1.852,            1.960,            1.720,            1.994,            1.940,            1.882,            1.838,            1.838,            1.862,            1.857,           1.804,            1.804,            1.808,            1.804,            1.867,           1.818,            1.799,            1.799,            1.799,          1.818,            1.843,            1.848,            1.486,            0.963,            2.258,            2.053,            1.872,            1.828,            1.799,            1.711,            1.701,            1.716,            1.764,            1.764,            1.740,            1.691,            1.691,            1.745,            1.799,            1.852,            1.857,            1.916,            1.945,            1.989,            1.940,            1.931,            1.896,            1.833,            1.794};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_vitals);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

        mCubicValueLineChart.setBackgroundColor(Color.BLACK);
       // mCubicValueLineChart.getIndicatorLineColor()

        series11 = new ValueLineSeries();
        series11.setColor(0xFF56B7F1);

        healthInit();

        fab();





        assert ecg != null;
        ecg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letsPlotECG();
            }
        });

        assert vitals != null;
        vitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                letsWatchVitals();
            }
        });

        assert skype != null;
        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letsCallSkype();
            }
        });


        assert dev != null;
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent i = ctx.getPackageManager().getLaunchIntentForPackage("comm.cchong.BloodAssistant");
                    ctx.startActivity(i);

                }catch (ActivityNotFoundException e) {
                    Log.e("dev", "dev activity failed", e);
                }catch(Exception e){
                    e.printStackTrace();

                }
            }
        });






    }



    private void fab(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // ecg= (Button) findViewById(R.id.ecg);
        //respire= (Button) findViewById(R.id.respire);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myFirebaseRef.addChildEventListener(new ChildEventListener() {



                    // Retrieve new posts as they are added to the database
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {


try{

                                value = (Map<String, Object>) snapshot.getValue();


    String string = "Patient Id: "+value.get("Patient_ID")+"\nDate:"+value.get("Date")+"\nTime: "+value.get("Time")+"\nAirFlow: "+value.get("Airflow")+"\nECG: "+value.get("ECG")+"\nPosition: "+value.get("Pos")+"\nSpO2: "+value.get("Spo2")+"\nConductance: "+value.get("Conductance")+"\nResistance: "+value.get("Resistance")+"\nTemperature: "+value.get("Temperature")+"\n\n";

    SP.setText(String.valueOf(value.get("Spo2"))+" %");
    COND.setText(String.valueOf(value.get("Conductance"))+" S");
    //if()

    //POS.setText(String.valueOf(value.get("Pos")));
    TEMP.setText(String.valueOf(value.get("Temperature"))+" F");
    RES.setText(String.valueOf(value.get("Resistance"))+ " ohms");
    PUL.setText(String.valueOf(value.get("Bpm")));
    GLU.setText(String.valueOf(value.get("glucose"))+" mg/dL");
    BP.setText("128/85 ");

    if(value.get("Pos").equals(1)){
        POS.setText("Prone");
    }else if(value.get("Pos").equals(2)){
        POS.setText("Stand/Sit");
    }else if(value.get("Pos").equals(3)){
        POS.setText("Left Lateral");
    }else if(value.get("Pos").equals(4)){
        POS.setText("Supine");
    }else if(value.get("Pos").equals(5)){
        POS.setText("Right Lateral");
    }else if(value.get("Pos").equals(6)){
        POS.setText("Non-defined");
    }

    series11.addPoint(new ValueLinePoint("jan", Integer.parseInt((String) value.get("Airflow"))));
    mCubicValueLineChart.addSeries(series11);

   // mCubicValueLineChart.startAnimation();

 //   mCubicValueLineChart.isUseDynamicScaling();
//
    //9c




                       }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        String p= s;

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        String p= s;

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vitals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

          startActivity(new Intent(Vitals.this,About_Us.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


       if (id == R.id.nav_share) {

        } else if (id == R.id.logout) {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("logged");
            editor.apply();

            startActivity(new Intent(Vitals.this, Login2Health.class));
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void letsPlotECG(){

        // we're going to simulate real time with thread that append data to the graph

        assert graph != null;
        graph.addSeries(series);

        Viewport viewport = graph.getViewport();
        // viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);

        // viewport.setMinY(0);
        //viewport.setMaxY(2.4);


        viewport.setMinX(0);

        viewport.setMaxX(2000);
        viewport.setScrollable(true);
        viewport.setScalable(true);


        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (  i = 0; i <1000; i++) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // addEntry();
                            try {
                                series.appendData(new DataPoint(lastX, ECGVal[g]*0.7), true, 1000);


                                g++;

                                lastX = lastX + 10;

                            }catch (ArrayIndexOutOfBoundsException a){a.printStackTrace();}

                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }



                }
            }
        }).start();

    }


    private void letsPlotAirFlow(){

        try {

            mCubicValueLineChart.addSeries(series11);
            mCubicValueLineChart.startAnimation();

           // mCubicValueLineChart.setS

            //series.addPoint(new ValueLinePoint("jan", Integer.parseInt((String) value.get("Airflow"))));


        }catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private void healthInit(){


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        ecg= (Button) findViewById(R.id.ecg);

        skype= (Button) findViewById(R.id.sky);

        myFirebaseRef = new Firebase("https://health03.firebaseio.com/");

        graph = (GraphView) findViewById(R.id.graph);


        series = new LineGraphSeries<DataPoint>();

        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(3);
        series.setThickness(2);
        //   series.setDrawBackground(true);
        graph.setBackgroundColor(Color.BLACK);

        series1 = new LineGraphSeries<DataPoint>();

       // series.setAnimated(true);

        series1.setAnimated(true);

       vitals= (Button) findViewById(R.id.vitals);
        dev= (Button) findViewById(R.id.devl);
        BP= (TextView)findViewById(R.id.bp);
        PUL= (TextView)findViewById(R.id.pulse);
        SP= (TextView)findViewById(R.id.sp);
        TEMP= (TextView)findViewById(R.id.temp);
        RES= (TextView)findViewById(R.id.sr);
        COND= (TextView)findViewById(R.id.cond);
        BP= (TextView)findViewById(R.id.bp);

        GLU= (TextView)findViewById(R.id.glu);
        POS= (TextView)findViewById(R.id.pos);



        // customize a little bit viewport





    }

    private void letsCallSkype(){

      try{

        Intent sky = new Intent("android.intent.action.VIEW");
        sky.setData(Uri.parse("skype:" + "mohan.ph"));//live:4ec999f369c381e4
        startActivity(sky);

    }catch (ActivityNotFoundException e) {
        Log.e("SKYPE CALL", "Skype failed", e);
    }catch (Exception e){
        e.printStackTrace();
    }

}


    private void letsWatchVitals(){
        myFirebaseRef.addChildEventListener(new ChildEventListener() {



            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {


                try{

                    value = (Map<String, Object>) snapshot.getValue();


                    String string = "Patient Id: "+value.get("Patient_ID")+"\nDate:"+value.get("Date")+"\nTime: "+value.get("Time")+"\nAirFlow: "+value.get("Airflow")+"\nECG: "+value.get("ECG")+"\nPosition: "+value.get("Pos")+"\nSpO2: "+value.get("Spo2")+"\nConductance: "+value.get("Conductance")+"\nResistance: "+value.get("Resistance")+"\nTemperature: "+value.get("Temperature")+"\n\n";

                    SP.setText(String.valueOf(value.get("Spo2"))+" %");
                    COND.setText(String.valueOf(value.get("Conductance"))+" S");

                    if(value.get("Pos").equals(1)){
                        POS.setText("Sit/Stand");
                    }else if(value.get("Pos").equals(2)){
                        POS.setText("Right Lateral");
                    }else if(value.get("Pos").equals(2)){
                        POS.setText("Left Lateral");
                    }else {
                        POS.setText("Non-defined");
                    }

                    TEMP.setText(String.valueOf(value.get("Temperature")));
                    RES.setText(String.valueOf(value.get("Resistance"))+ " ohms");
                    PUL.setText(String.valueOf(value.get("Bpm")));
                    GLU.setText(String.valueOf(value.get("glucose"))+" mg/dL");
                    BP.setText(String.valueOf(value.get("bp")));

                    series11.addPoint(new ValueLinePoint("jan", Integer.parseInt((String) value.get("Airflow"))));
                    mCubicValueLineChart.addSeries(series11);
                    mCubicValueLineChart.setShowIndicator(true);




                    // mCubicValueLineChart.startAnimation();

                    //   mCubicValueLineChart.isUseDynamicScaling();





                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String p= s;

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                String p= s;

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }





}
