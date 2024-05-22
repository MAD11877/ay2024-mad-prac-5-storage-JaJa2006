package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView description;
    ImageView smallimage;
    ImageView bigimage;


    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.TVname);
        description = itemView.findViewById(R.id.TVdescription);
        smallimage = itemView.findViewById(R.id.IVprofile);
        bigimage = itemView.findViewById(R.id.IVprofile2);

    }
}
