package Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shreya.form_ex.R;

import java.util.List;

import Model.BookListModel;

public class searchstudentbooksadapter extends RecyclerView.Adapter<searchstudentbooksadapter.searchstdbookadapterViewHolder> {

    private Context mCtx;
    private List<BookListModel> bookListModelList;

    public searchstudentbooksadapter(Context mCtx, List<BookListModel> bookListModels) {
        this.mCtx = mCtx;
        this.bookListModelList = bookListModels;
    }

    @NonNull
    @Override
    public searchstdbookadapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.searchbooklist_layout, parent, false);
        return new searchstdbookadapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchstdbookadapterViewHolder holder, int position) {
        BookListModel model = bookListModelList.get(position);

        holder.txtBookName.setText("Book Name: " + model.getBookName());
        holder.txtQty.setText("Qty: " + model.getQty());
        holder.txtbdsc.setText("Book Description: " + model.getBookDes());
        holder.txtprice.setText("Price: " + model.getPrice());
        holder.txtautname.setText("Author: " + model.getBookAuth());
    }

    @Override
    public int getItemCount() {
        return bookListModelList.size();
    }

    class searchstdbookadapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtBookName, txtQty, txtautname, txtprice, txtbdsc;

        public searchstdbookadapterViewHolder(View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtbdsc = itemView.findViewById(R.id.txtbdsc);
            txtautname = itemView.findViewById(R.id.txtautname);
            txtprice = itemView.findViewById(R.id.txtprice);
        }
    }
}
