package sg.edu.rp.c346.id20020509.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etSongID, etSongTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btUpdate, btDelete, btCancel;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etSongID = findViewById(R.id.etSongID);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        btUpdate = findViewById(R.id.btInsert);
        btDelete = findViewById(R.id.btShowList);
        btCancel = findViewById(R.id.btCancel);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        etSongID.setHint(song.get_id());
        etSongTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(song.getYear());
        if (song.getStars() == 1) {
            rb1.setChecked(true);
        }
        else if (song.getStars() == 2) {
            rb2.setChecked(true);
        }
        else if (song.getStars() == 3) {
            rb3.setChecked(true);
        }
        else if (song.getStars() == 4) {
            rb4.setChecked(true);
        }
        else if (song.getStars() == 5) {
            rb5.setChecked(true);
        }

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                song.setTitle(etSongTitle.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));
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
                dbh.updateSong(song);
                dbh.close();
                finish();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DBHelper dbh = new DBHelper(EditActivity.this);
               dbh.deleteSong(song.get_id());
               finish();
           }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}