package br.univali.mobile.contatos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListContatos extends Fragment {
    private Button btnAdd = null;
    private Button btnEdit = null;

    public ListContatos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_contatos, container, false);

        btnAdd = v.findViewById(R.id.btn_list_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new AddContato()).commit();
            }
        });

        btnEdit = v.findViewById(R.id.btn_list_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new EditContato()).commit();
            }
        });

        return v;
    }
}