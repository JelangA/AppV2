package com.example.tokov2.Activity.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.tokov2.databinding.FragmentHomeBinding;
import com.example.tokov2.util.ListviewAdapter;

public class HomeFragment extends Fragment {

    String[] listdata = {"data 1", "data 2", "data 3", "data 4", "data 5", "data 6", "data 7"};
    ListView listView;

    Button btnTampil;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listviewbarang;
        ListviewAdapter adapter = new ListviewAdapter(getContext(), listdata);
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}