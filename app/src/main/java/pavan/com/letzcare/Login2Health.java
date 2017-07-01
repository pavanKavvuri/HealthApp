package pavan.com.letzcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login2Health extends AppCompatActivity {


    EditText _username;
    EditText _passwordText;
    Button _loginButton;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public static final String PREFS_NAME = "LoginPrefs";
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2_health);

       settings = getSharedPreferences(PREFS_NAME, 0);
         editor = settings.edit();

        if (settings.getString("logged", "").equals("logged")) {

            Intent intent = new Intent(Login2Health.this, Vitals.class);
            startActivity(intent);
            finish();
        }

        _username = (EditText) findViewById(R.id.user);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                authenticate();
            }
        });


    }


    private void authenticate(){


            String pwd= String.valueOf(_passwordText.getText()).trim();
            String usr= String.valueOf(_username.getText()).trim();

        if((pwd.equals("123456"))&&(usr.equals("Mohan11"))){


            editor.putString("logged", "logged");
            editor.putString("User", usr);
            editor.putString("Password", pwd);
            editor.commit();

            startActivity(new Intent(Login2Health.this, Vitals.class));
            finish();
        }

        else if((pwd.equals("123456"))&&(usr.equals("Prakash"))){

            editor.putString("logged", "logged");
            editor.putString("User", usr);
            editor.putString("Password", pwd);
            editor.commit();

            startActivity(new Intent(Login2Health.this, Vitals.class));
            finish();

        }


        else if((pwd.equals("123456"))&&(usr.equals("user1"))){

            editor.putString("logged", "logged");
            editor.putString("User", usr);
            editor.putString("Password", pwd);
            editor.commit();

            startActivity(new Intent(Login2Health.this, Vitals.class));
            finish();

        }

        else if((pwd.equals("123456"))&&(usr.equals("user2"))){

            editor.putString("logged", "logged");
            editor.putString("User", usr);
            editor.putString("Password", pwd);
            editor.commit();

            startActivity(new Intent(Login2Health.this, Vitals.class));
            finish();

        }


        else {

            _username.setError("Wrong username or password");
            _passwordText.setError("Wrong username or password");

            }

    }
}
