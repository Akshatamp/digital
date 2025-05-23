package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shreya.form_ex.DataBaseHelperClass;
import com.example.shreya.form_ex.R;
import com.example.shreya.form_ex.addbookdetailsactivity;

import java.util.List;

import Model.BookListModel;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListAdapterViewHolder> {

    private Context mCtx;
    private List<BookListModel> bookListModelList;
    DataBaseHelperClass databaseHelper;

    public BookListAdapter(Context mCtx, List<BookListModel> bookListModels) {
        this.mCtx = mCtx;
        this.bookListModelList = bookListModels;
        databaseHelper = new DataBaseHelperClass(mCtx);

    }

    @Override
    public int getItemCount() {
        return bookListModelList.size();
    }

    @NonNull
    @Override
    public BookListAdapter.BookListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.booklist_layout, null);

        return new BookListAdapter.BookListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookListAdapterViewHolder holder, int position) {

        final BookListModel model = bookListModelList.get(position);

        holder.txtBookName.setText("Book Name: " + model.getBookName());
        holder.txtQty.setText("Qty: " + model.getQty());
        holder.txtbdsc.setText("Book Description:" + model.getBookDes());
        holder.txtprice.setText("Price:" + model.getPrice());
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mCtx.startActivity(new Intent(mCtx, addbookdetailsactivity.class));

                Intent i = new Intent(mCtx, addbookdetailsactivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("BookId", model.getBookId());
                i.putExtras(bundle);
                mCtx.startActivity(i);

            }
        });


    }


    class BookListAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtBookName, txtQty, txtautname, txtprice, txtbdsc;
        ImageView imgedit, imgdelete;

        public BookListAdapterViewHolder(View itemView) {

            super(itemView);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtbdsc = itemView.findViewById(R.id.txtbdsc);
            txtautname = itemView.findViewById(R.id.txtautname);
            txtprice = itemView.findViewById(R.id.txtprice);
            imgedit = itemView.findViewById(R.id.imgedit);
            imgdelete = itemView.findViewById(R.id.imgdelete);


        }
    }
}
