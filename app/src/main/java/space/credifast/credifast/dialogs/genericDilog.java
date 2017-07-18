package space.credifast.credifast.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import space.credifast.credifast.LoginActivity;
import space.credifast.credifast.MainActivity;
import space.credifast.credifast.R;

//import com.lamarrulla.www.tiendafacil.LoginActivity;
//import com.lamarrulla.www.tiendafacil.MainActivity;
//import com.lamarrulla.www.tiendafacil.R;
//import com.lamarrulla.www.tiendafacil.preference.UserAccount;

public class genericDilog extends Activity implements View.OnClickListener {
    boolean isOk = false;
    boolean isOkLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_generic_dilog);
        //getSupportActionBar().hide();
        getWindow().getAttributes().windowAnimations = R.style.dialog_animation;

        TextView tvMessage=(TextView) findViewById(R.id.tv_message_dialog);
        TextView btnAgree=(TextView) findViewById(R.id.btn_agree);


        ImageView iconDialog=(ImageView) findViewById(R.id.iv_generic_dialog);

        findViewById(R.id.btn_agree).setOnClickListener(this);



        Bundle extras = getIntent().getExtras();
        String message="";


        if (extras != null) {
            message = extras.getString("message");
            isOk=extras.getBoolean("isOk");
            isOkLogin=extras.getBoolean("isOkLogin");

        }


        if (isOk || isOkLogin){
            iconDialog.setImageResource(R.drawable.ic_ok_dialog);
            btnAgree.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }


        tvMessage.setText(message);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agree:

                if (isOk){
                    Intent i = new Intent(this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);

                }else if(isOkLogin){

                    /*UserAccount userAccount;

                    userAccount = new UserAccount(this);
                    userAccount.setUserId("");
                    userAccount.setEmail("");
                    userAccount.setToken("");
                    userAccount.setSessionStart(true);*/

                    Intent i = new Intent(this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);

            }else{
                   finish();
                }


                break;
        }
    }
}
