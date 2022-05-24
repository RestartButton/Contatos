package br.univali.mobile.contatos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class ListContatos extends Fragment {
    private Button btnAdd = null;
    private DBManager dbManager = null;
    private ListView lv = null;

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

        View v = inflater.inflate(R.layout.fragment_list_contatos, container, false);

        dbManager = new DBManager(getActivity());
        lv = v.findViewById(R.id.list_contatos);
        dbManager.getContatos(getActivity(), lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView sId = view.findViewById(R.id.textViewIdListContato);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(sId.getText().toString()));

                EditContato eFrag = new EditContato();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                eFrag.setArguments(b);

                ft.replace(R.id.main_fragment_container, eFrag).commit();
            }
        });

        btnAdd = v.findViewById(R.id.btn_list_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new AddContato()).commit();
            }
        });


        return v;
    }
}