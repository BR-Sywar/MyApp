package com.example.ui.Accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.data.adapters.CategoryAdapter;
import com.example.data.models.Category;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.AuthApi;
import com.example.network.api.CategoryApi;
import com.example.network.response.CategoryResponse;
import com.example.utils.OnRecyclerViewItemClickListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView ;
    private CategoryAdapter categoryAdapter ;
    private CategoryApi api ;
    private ProgressBar progressBar ;
    View root ;
    public View onCreateView (@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.activity_homedashbord, container, false);
        recyclerView = root.findViewById(R.id.recycler_categories);
        progressBar = root.findViewById(R.id.progressBar);
        api = RetrofitInstance.getInstance().create(CategoryApi.class);
        prepareRecyclerView();
        getCategories();
        return root;
    }
    private void prepareRecyclerView() {
        categoryAdapter = new CategoryAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryAdapter);
            categoryAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void onRecyclerViewItemClicked(int position, int id) {
                    Category category = categoryAdapter.getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("cat",category);
                    Navigation.findNavController(root).navigate(R.id.nav_entre,bundle);
                }
            });
    }

    private void getCategories() {
        Call<CategoryResponse> call = api.getCategories();
            progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                progressBar.setVisibility(View.GONE);
                CategoryResponse categoryResponse = response.body() ;
                if (categoryResponse != null && categoryResponse.getCategories() != null) {
                        categoryAdapter.setCategoryList(categoryResponse.getCategories());
                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
