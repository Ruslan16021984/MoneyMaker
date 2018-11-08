package carbit3333333.gmail.com.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemlistAdapter extends RecyclerView.Adapter<ItemlistAdapter.RecordViewHolder>{

    private List<Record> data = new ArrayList<>();

    public ItemlistAdapter() {
        createData();
    }

    @NonNull
    @Override
    public ItemlistAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemlistAdapter.RecordViewHolder holder, int position) {
        Record record = data.get(position);
        holder.eplayData(record);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public RecordViewHolder(View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            price =itemView.findViewById(R.id.price);
        }
        public void eplayData(Record record){
            title.setText(record.getTitle());
            price.setText(String.valueOf(record.getPrice()));
        }
    }
    private void createData() {
        data.add(new Record("Milk", 23));
        data.add(new Record("Bread", 1));
        data.add(new Record("Life", 14));
        data.add(new Record("Snow", 100));
        data.add(new Record("Book", 11));
        data.add(new Record("macBook", 2000));
        data.add(new Record("Lego one hondred socol", 400));
        data.add(new Record("noutbook", 2000));
        data.add(new Record("Шоколадка", 4));
        data.add(new Record("Шкаф", 1445));
    }
}
