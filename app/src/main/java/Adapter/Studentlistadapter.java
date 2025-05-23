package Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shreya.form_ex.DataBaseHelperClass;
import com.example.shreya.form_ex.R;

import java.util.List;

import Model.Studentlistmodel;

public class Studentlistadapter extends RecyclerView.Adapter<Studentlistadapter.StudentListAdapterViewHolder> {
    private Context mCtx;
    private List<Studentlistmodel> studentListModelList;
    DataBaseHelperClass databaseHelper;

    public Studentlistadapter(Context mCtx, List<Studentlistmodel> studentListModels) {
        this.mCtx = mCtx;
        this.studentListModelList = studentListModels;
        databaseHelper = new DataBaseHelperClass(mCtx);
}
    @Override
    public int getItemCount() {
        return studentListModelList.size();
    }

    @NonNull
    @Override
    public Studentlistadapter.StudentListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.studentlist_layout, null);

        return new Studentlistadapter.StudentListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Studentlistadapter.StudentListAdapterViewHolder holder, int position) {

        final Studentlistmodel model = studentListModelList.get(position);

        holder.txtfirstname.setText("First Name: " + model.getFirstname());
        holder.txtlastname.setText("Last name: " + model.getLastname());
        holder.txtgender.setText("Gender:" + model.getGender());
        holder.txtusnnumber.setText("Usnnumber:" + model.getUSNnumber());
        holder.txtemailid.setText("Email:" + model.getEmail());
        holder.txtaddress.setText("Address:" + model.getAddress());


    }
    class StudentListAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtfirstname, txtlastname, txtgender, txtusnnumber, txtemailid,txtaddress;

        public StudentListAdapterViewHolder(View itemView) {

            super(itemView);
            txtfirstname = itemView.findViewById(R.id.txtfirstname);
            txtlastname = itemView.findViewById(R.id.txtlastname);
            txtgender = itemView.findViewById(R.id.txtgender);
            txtusnnumber = itemView.findViewById(R.id.txtusnnumber);
            txtemailid = itemView.findViewById(R.id.txtemailid);
            txtaddress = itemView.findViewById(R.id.txtaddress);


        }
    }
}

