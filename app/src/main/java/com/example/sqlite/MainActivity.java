package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editTitle, editAuthor, editISPN, editDescription, editID;
    Button addButton, viewUpdate, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        editTitle = (EditText)findViewById(R.id.Title);
        editAuthor = (EditText)findViewById(R.id.Author);
        editISPN = (EditText)findViewById(R.id.ISPN);
        editDescription = (EditText)findViewById(R.id.Description);
        editID = (EditText) findViewById(R.id.editID);
        addButton = (Button)findViewById(R.id.addButton);
        viewUpdate = (Button) findViewById(R.id.viewUpdate);
        delete = (Button) findViewById(R.id.delete);
        AddData();
        UpdateData();

    }

    public void deleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deleteRows = mydb.deleteData(editID.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }
    public void UpdateData(){
        viewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean isUpdate = mydb.updateData(editID.getText().toString(),
                                editTitle.getText().toString(),
                                editAuthor.getText().toString(),
                                editISPN.getText().toString(),
                                editDescription.getText().toString());

                        if (isUpdate)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData (){
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.addData(editTitle.getText().toString(),
                                editAuthor.getText().toString(),
                                editISPN.getText().toString(),
                                editDescription.getText().toString());

                        if (isInserted)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
