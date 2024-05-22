package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = new User("John Doe", "MAD Developer", 1, false);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        tvName.setText(user.name);
        tvDescription.setText(user.description);

        Intent receivingEnd = getIntent();
        String Name = receivingEnd.getStringExtra("Name");
        String Desc = receivingEnd.getStringExtra("Desc");
        int Id = receivingEnd.getIntExtra("ID",0);
        Boolean Follow = receivingEnd.getBooleanExtra("Follow",false);

        User user2 = new User(Name,Desc,Id,Follow);

        DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this, null, null, 1);

        tvName.setText(user2.name);
        tvDescription.setText(user2.description);
        if (user2.followed) {
            btnFollow.setText("Unfollow");
            dbHandler.updateUsers(user2);
        }
        else{
            btnFollow.setText("Follow");
            dbHandler.updateUsers(user2);
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnFollow.getText().toString()=="Follow") {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    user.followed = true;
                }
                else{
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    user.followed = false;
                }

            }

        });
    }
}