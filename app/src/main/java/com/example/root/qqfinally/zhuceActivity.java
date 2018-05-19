package com.example.root.qqfinally;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class zhuceActivity extends Activity {
    private EditText edittext1,edittext2,edittext3;
    private Button button;
    private DatebaseHelper databaseHelper;
    //数据库名称
    private static final String DATABASE_NAME="jscsd.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="username";
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zhuce);
        edittext1=(EditText)findViewById(R.id.editview1);
        edittext2=(EditText)findViewById(R.id.editview2);
        edittext3=(EditText)findViewById(R.id.editview3);

        button=(Button)findViewById(R.id.tijiao);
        button.setOnClickListener(new OnClickListener(){


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String namestring = edittext1.getText().toString();
                String passstring = edittext2.getText().toString();
                String repassstring=edittext3.getText().toString();
                if(passstring.equals(repassstring))
                {
                    databaseHelper=new DatebaseHelper(zhuceActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
                    db =  databaseHelper.getReadableDatabase();
                    db.execSQL("insert into username (name,password) values(?,?)",new String[]{namestring,passstring});
                    insertData (databaseHelper.getReadableDatabase(),edittext1,edittext2,edittext3);

                    Toast.makeText(zhuceActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
                    Intent b=new Intent(zhuceActivity.this,MainActivity.class);
                    startActivity(b);
                }
                else
                {
                    Toast.makeText(zhuceActivity.this,"两次密码不一致", Toast.LENGTH_LONG).show();
                }
            }

            private void insertData(SQLiteDatabase readableDatabase, EditText edittext1, EditText edittext2, EditText edittext3) {
                ContentValues values =new ContentValues();
                values.put("username", String.valueOf(edittext1));
                values.put("password", String.valueOf(edittext2));
                values.put("password", String.valueOf(edittext3));
                readableDatabase.insert("zhuce",null,values);
            }

        });
    }

}