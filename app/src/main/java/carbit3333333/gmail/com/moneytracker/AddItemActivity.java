package carbit3333333.gmail.com.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText price;
    private Button addBtn;
    private static final String TAG = "AddItemActivity";
    public static final String TYPE_KEY = "type";
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.add_item_screen_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);
        type = getIntent().getStringExtra(TYPE_KEY);


//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                addBtn.setEnabled(!TextUtils.isEmpty(editable));
//            }
//        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String priceValue = price.getText().toString();
                if (nameValue.isEmpty() || priceValue.isEmpty()){
                    return;
                }

                Item item = new Item(nameValue, priceValue, type);
                Intent intent= new Intent();
                intent.putExtra("item", item);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
