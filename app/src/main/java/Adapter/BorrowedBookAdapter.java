package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shreya.form_ex.R;
import java.util.List;

import Model.BorrowedBookModel;

public class BorrowedBookAdapter extends RecyclerView.Adapter<BorrowedBookAdapter.ViewHolder> {

    private Context context;
    private List<BorrowedBookModel> borrowedBooks;

    public BorrowedBookAdapter(Context context, List<BorrowedBookModel> borrowedBooks) {
        this.context = context;
        this.borrowedBooks = borrowedBooks;
    }

    @NonNull
    @Override
    public BorrowedBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.borrowed_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowedBookAdapter.ViewHolder holder, int position) {
        BorrowedBookModel model = borrowedBooks.get(position);

        holder.txtBookName.setText("Book: " + model.getBookName());
        holder.txtBorrowDate.setText("Borrowed: " + model.getBorrowDate());
        holder.txtDueDate.setText("Due: " + model.getDueDate());
        holder.txtDueDays.setText("Duration: " + model.getDueDays() + " days");
    }

    @Override
    public int getItemCount() {
        return borrowedBooks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBookName, txtBorrowDate, txtDueDate, txtDueDays;

        public ViewHolder(View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtBorrowDate = itemView.findViewById(R.id.txtBorrowDate);
            txtDueDate = itemView.findViewById(R.id.txtDueDate);
            txtDueDays = itemView.findViewById(R.id.txtDueDays);
        }
    }
}
