package br.univali.mobile.contatos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddContato extends Fragment {
    private Button btnSave = null;
    private Button btnBack = null;
    private Spinner contatoTypeEdt = null;
    private EditText contatoNameEdt = null, contatoPhoneEdt = null, contatoLastnameEdt = null;

    private DBManager dbManager;

    public AddContato() {
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
        View v = inflater.inflate(R.layout.fragment_add_contato, container, false);

        contatoNameEdt = v.findViewById(R.id.add_name);
        contatoLastnameEdt = v.findViewById(R.id.add_lastname);
        contatoTypeEdt = v.findViewById(R.id.add_phone_type);
        contatoPhoneEdt = v.findViewById(R.id.add_phone_number);

        try {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.type_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            contatoTypeEdt.setAdapter(adapter);

            dbManager = new DBManager(getActivity());

        } catch (Exception e) {
            Log.e("AddContato", e.getMessage());
        }

        btnSave = v.findViewById(R.id.btn_add_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contatoName = contatoNameEdt.getText().toString() + contatoLastnameEdt.getText().toString();
                String contatoType = contatoTypeEdt.getSelectedItem().toString();
                String contatoPhone = contatoPhoneEdt.getText().toString();

                if (contatoName.isEmpty() || contatoType.isEmpty() || contatoPhone.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor preencha todos os campos..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbManager.addContato(contatoName, contatoType, contatoPhone);

                Toast.makeText(getActivity(), "Contato adicionado.", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new ListContatos()).commit();
            }
        });

        btnBack = v.findViewById(R.id.btn_add_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new ListContatos()).commit();
            }
        });

        return v;
    }
}