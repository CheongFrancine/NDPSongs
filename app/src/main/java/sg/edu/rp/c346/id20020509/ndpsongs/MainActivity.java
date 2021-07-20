package sg.edu.rp.c346.id20020509.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btInsert, btShowList;
    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter<Song> aa;

    @Override
    protected void onResume() {
        super.onResume();

        btShowList.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        btInsert = findViewById(R.id.btInsert);
        btShowList = findViewById(R.id.btShowList);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = 1;
                if (rb2.isChecked()) {
                    stars = 2;
                }
                else if (rb3.isChecked()) {
                    stars = 3;
                }
                else if (rb4.isChecked()) {
                    stars = 4;
                }
                else if (rb5.isChecked()) {
                    stars = 5;
                }
                else {
                    stars = 1;
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, stars);

                if (inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btShowList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });



    }
}