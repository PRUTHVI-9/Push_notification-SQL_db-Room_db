package com.example.push_notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    EditText first,dob,last;
    Button insert,update,delete,view,next;
    SqliteDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first=findViewById(R.id.first);
        dob=findViewById(R.id.dob);
        last=findViewById(R.id.last);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        view=findViewById(R.id.view);
        next=findViewById(R.id.next);
        db = new SqliteDB(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = first.getText().toString();
                String lastTXT = last.getText().toString();
                String dobTXT = dob.getText().toString();

                boolean checkinsertdata = db.insertuserdata(nameTXT, lastTXT, dobTXT);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Enter Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Enter Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = first.getText().toString();
                String lastTXT = last.getText().toString();
                String dobTXT = dob.getText().toString();

                boolean checkupdatedata = db.updateuserdata(nameTXT, lastTXT, dobTXT);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "New Enter Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Enter Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = first.getText().toString();

                boolean checkdeletedata = db.deleteuserdata(nameTXT);
                if (checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "New Enter Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Enter Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exits", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("last :"+res.getString(1)+"\n");
                    buffer.append("dob :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        statusbarcolor(); // change the status bar color
        FirebaseMessaging.getInstance().setAutoInitEnabled(true); // Push Notification send msg
    }

    private void statusbarcolor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.teal_200,this.getTheme()));
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.teal_200));
        }
    }
}