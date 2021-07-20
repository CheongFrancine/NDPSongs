package sg.edu.rp.c346.id20020509.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btShow;
    Spinner spinner;
    ArrayList<Song> al, al2;
    ListView lv;
    ArrayAdapter<Song> aa, aa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btShow = findViewById(R.id.btShow);
        spinner = findViewById(R.id.dynamic_spinner);

        al = new ArrayList<Song>();
        al2 = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        aa2 = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al2);
        lv.setAdapter(aa);
        spinner.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Song song = al.get(position);
                Intent i = new Intent(MainActivity2.this, EditActivity.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });

        DBHelper dbh = new DBHelper(MainActivity2.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                lv.setAdapter(aa2);
                al2.clear();
                al2.addAll(dbh.getAllSongsByStars(5));
                aa2.notifyDataSetChanged();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }
}