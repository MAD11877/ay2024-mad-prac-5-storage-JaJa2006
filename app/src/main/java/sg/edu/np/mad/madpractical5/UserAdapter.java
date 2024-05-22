package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    ArrayList<User> data;
    public UserAdapter(ArrayList<User> input, Context context) {
        this.data = input;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(item);
    }
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = data.get(position);
        if (user.name.charAt(user.name.length() - 1) != '7') {
            holder.name.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.VISIBLE);
            holder.smallimage.setVisibility(View.VISIBLE);
            holder.bigimage.setVisibility(View.GONE);
            holder.name.setText(user.name);
            holder.description.setText(user.description);
            holder.smallimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    builder.setTitle("Profile");
                    builder.setMessage(user.name);
                    builder.setCancelable(true);
                    builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            Intent activityMain = new Intent(v.getContext(), MainActivity.class);
                            activityMain.putExtra("Name",user.name);
                            activityMain.putExtra("Desc",user.description);
                            activityMain.putExtra("ID",user.id);
                            activityMain.putExtra("Follow",user.followed);
                            v.getContext().startActivity(activityMain);
                        }
                    });
                    builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else {
            holder.name.setVisibility(View.GONE);
            holder.description.setVisibility(View.GONE);
            holder.smallimage.setVisibility(View.GONE);
            holder.bigimage.setVisibility(View.VISIBLE);
            holder.bigimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    builder.setTitle("Profile");
                    builder.setMessage(user.name);
                    builder.setCancelable(true);
                    builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            Intent activityMain = new Intent(v.getContext(), MainActivity.class);
                            activityMain.putExtra("Name",user.name);
                            activityMain.putExtra("Desc",user.description);
                            v.getContext().startActivity(activityMain);
                        }
                    });
                    builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }
    public int getItemCount() {
        return data.size();
    }
}
