package rattclub.eCommerce.Admins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rattclub.eCommerce.Model.Cart;
import rattclub.eCommerce.R;
import rattclub.eCommerce.ViewHolder.CartViewHolder;

public class AdminUserProductsActivity extends AppCompatActivity {
    private RecyclerView productsList;
    private DatabaseReference cartListRef;
    private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_products);

        userID = getIntent().getStringExtra("uid");
        productsList = findViewById(R.id.admin_products_list);
        productsList.setLayoutManager(new LinearLayoutManager(this));

        cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List")
        .child("Admin View").child(userID).child("Products");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef, Cart.class)
                .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull Cart cart) {
                holder.txtProductQuantity.setText("Quantity = " + cart.getQuantity());
                holder.txtProductPrice.setText("Price = " + cart.getPrice());
                holder.txtProductName.setText(cart.getPname());
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.cart_items_layout, parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        productsList.setAdapter(adapter);
        adapter.startListening();
    }
}
