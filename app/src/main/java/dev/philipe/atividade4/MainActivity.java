package dev.philipe.atividade4;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText num1, num2, num3, num4;
    private TextView resultado;
    private Button botaoResultado, botaoLimpar;
    private boolean resultadoAlterado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.numero1);
        num2 = findViewById(R.id.numero2);
        num3 = findViewById(R.id.numero3);
        num4 = findViewById(R.id.numero4);
        resultado = findViewById(R.id.resultado);
        botaoResultado = findViewById(R.id.botaoResultado);

        botaoResultado.setOnClickListener(v -> {
            if (checarCamposPreenchidosENumeral()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Selecione uma opção de ordenação:");
                builder.setItems(new String[]{"Ordem de inserção", "Ordem crescente", "Ordem decrescente"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            agruparNumerosOrdemInsercao();
                            resultadoAlterado = true;
                            break;
                        case 1:
                            agruparNumerosOrdemCrescente();
                            resultadoAlterado = true;
                            break;
                        case 2:
                            agruparNumerosOrdemDecrescente();
                            resultadoAlterado = true;
                            break;
                    }
                    resultado.setVisibility(TextView.VISIBLE);
                });

                builder.setNegativeButton("Cancelar", (dialog, which) -> {
                    dialog.dismiss();
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        botaoLimpar = findViewById(R.id.botaoLimpar);
        botaoLimpar.setOnClickListener(v -> {
            limparResultado();
            limparCampos();
        });
    }

    private void agruparNumerosOrdemInsercao() {
        resultado.setText(
                String.format(
                        "%s, %s, %s, %s",
                        num1.getText().toString(),
                        num2.getText().toString(),
                        num3.getText().toString(),
                        num4.getText().toString()
                )
        );

    }

    private void agruparNumerosOrdemCrescente() {
        List<Integer> numeros = new ArrayList<>(
                Arrays.asList(
                        Integer.parseInt(num1.getText().toString()),
                        Integer.parseInt(num2.getText().toString()),
                        Integer.parseInt(num3.getText().toString()),
                        Integer.parseInt(num4.getText().toString())
                )
        );

        numeros.sort(Integer::compareTo);

        resultado.setText(
                String.format(
                        "%s, %s, %s, %s",
                        numeros.get(0),
                        numeros.get(1),
                        numeros.get(2),
                        numeros.get(3)
                )
        );
    }

    private void agruparNumerosOrdemDecrescente() {
        List<Integer> numeros = new ArrayList<>(
                Arrays.asList(
                        Integer.parseInt(num1.getText().toString()),
                        Integer.parseInt(num2.getText().toString()),
                        Integer.parseInt(num3.getText().toString()),
                        Integer.parseInt(num4.getText().toString())
                )
        );

        numeros.sort(Collections.reverseOrder());

        resultado.setText(
                String.format(
                        "%s, %s, %s, %s",
                        numeros.get(0),
                        numeros.get(1),
                        numeros.get(2),
                        numeros.get(3)
                )
        );
    }

    private void limparResultado() {
        if (resultadoAlterado) {
            resultado.setText("");
            this.resultado.setVisibility(TextView.GONE);
            resultadoAlterado = false;
        } else {
            Toast.makeText(
                    this, "O resultado já está vazio!", Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void limparCampos() {
        num1.setText("");
        num2.setText("");
        num3.setText("");
        num4.setText("");
    }

    private boolean checarCamposPreenchidosENumeral() {
        if (num1.getText().toString().isEmpty()
                || num2.getText().toString().isEmpty()
                || num3.getText().toString().isEmpty()
                || num4.getText().toString().isEmpty()) {
            Toast.makeText(
                    this, "Preencha todos os campos!", Toast.LENGTH_SHORT
            ).show();

            return false;
        }

        if (!num1.getText().toString().matches("[0-9]+")) {
            Toast.makeText(
                    this, "Digite apenas números!", Toast.LENGTH_SHORT
            ).show();

            return false;
        }
        return true;
    }
}