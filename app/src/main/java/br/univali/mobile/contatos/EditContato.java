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

public class EditContato extends Fragment {
    private Button btnSave = null;
    private Button btnDelete = null;
    private Spinner contatoTypeEdt = null;
    private EditText contatoNameEdt = null, contatoPhoneEdt = null, contatoLastnameEdt = null;
    private String contatoName = null, contatoType = null, contatoPhone = null;

    private DBManager dbManager;

    public EditContato() {
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
        View v = inflater.inflate(R.layout.fragment_edit_contato, container, false);

        contatoNameEdt = v.findViewById(R.id.edit_name);
        contatoLastnameEdt = v.findViewById(R.id.edit_lastname);
        contatoTypeEdt = v.findViewById(R.id.edit_phone_type);
        contatoPhoneEdt = v.findViewById(R.id.edit_phone_number);

        contatoName = contatoNameEdt.getText().toString() + contatoLastnameEdt.getText().toString();
        contatoType = contatoTypeEdt.getSelectedItem().toString();
        contatoPhone = contatoPhoneEdt.getText().toString();

        try {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.type_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            contatoTypeEdt.setAdapter(adapter);

            dbManager = new DBManager(getActivity());
        } catch (Exception e) {
            Log.e("EditContato", e.getMessage());
        }

        btnSave = v.findViewById(R.id.btn_edit_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = contatoNameEdt.getText().toString();
                String newType = contatoTypeEdt.getSelectedItem().toString();
                String newPhone = contatoPhoneEdt.getText().toString();

                if (contatoName.isEmpty() || contatoType.isEmpty() || contatoPhone.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor preencha todos os campos..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbManager.editContato(contatoName, contatoType, contatoPhone, newName, newType, newPhone);

                Toast.makeText(getActivity(), "Contato salvo.", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new ListContatos()).commit();
            }
        });

        btnDelete = v.findViewById(R.id.btn_edit_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.deleteContato(contatoName, contatoType, contatoPhone);

                Toast.makeText(getActivity(), "Contato deletado.", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.main_fragment_container, new ListContatos()).commit();
            }
        });

        return v;
    }
}