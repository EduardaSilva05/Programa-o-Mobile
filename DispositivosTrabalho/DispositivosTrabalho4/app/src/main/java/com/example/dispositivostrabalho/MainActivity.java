package com.example.dispositivostrabalho;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nome, email, disciplina, idade, nota02, nota01;
    private TextView textErro, textInformacoes;
    private Button limpar, validar, enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextEmail);
        idade = findViewById(R.id.editTextIdade);
        disciplina = findViewById(R.id.editTextDisciplina);
        nota02 = findViewById(R.id.editTextNota02);
        nota01 = findViewById(R.id.editTextNota01);
        enviar = findViewById(R.id.buttonEnvio);
        limpar = findViewById(R.id.buttonLimpar);
        //textErro = findViewById(R.id.textViewErro);
        textInformacoes = findViewById(R.id.textViewInformacoes);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparTela();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarInformacoes();
            }
        });


    }

    private void limparTela(){
        nome.setText("");
        email.setText("");
        idade.setText("");
        disciplina.setText("");
        nota02.setText("");
        nota01.setText("");
        textErro.setText("");
        //textInformacoes.setText("");
    }

    private void enviarInformacoes(){
        double nota01Nr = 0;
        double nota02Nr = 0;
        double mediaFinal = 0;
        String textoFinal;
        String nota01Text = nota01.getText().toString();
        String nota02Text = nota02.getText().toString();
        String nomeFinal = nome.getText().toString();
        String emailFinal = email.getText().toString();
        String idadeFinal = idade.getText().toString();
        String disciplinaFinal = disciplina.getText().toString();
        String mensagemAprovReprov;

        try{

        nota01Nr = Double.parseDouble(nota01Text);
        nota02Nr = Double.parseDouble(nota02Text);

        mediaFinal = nota01Nr + nota02Nr / 2;

        if (mediaFinal >= 6){
            mensagemAprovReprov = "APROVADO";
        } else {
            mensagemAprovReprov = "REPROVADO";
        }


        if(nomeFinal.isEmpty()){
            throw new erros("Nome deve ser inserido!");
        }

        if (!emailFinal.contains("@")){
            throw new erros("O email deve ser válido!");
        }

        if(emailFinal.indexOf("@") < 4){
            throw new erros("O email deve ser válido!");
        }

        if(idadeFinal.isEmpty()){
            throw new erros("A idade deve ser informada");
        }

        if(disciplinaFinal.isEmpty()){
            throw new erros("A disciplina deve ser informada");
        }

        if(nota01Text.isEmpty()){
            throw new erros("A primeira nota deve ser informada!");
        }

        if(nota02Text.isEmpty()){
            throw new erros("A segunda nota deve ser informada!");
        }

        if(nota01Nr < 0 || nota01Nr > 10){
            throw new erros("O valor da nota deve ser entre 0 e 10");
        }

        if(nota02Nr < 0 || nota02Nr > 10) {
            throw new erros("O valor da nota deve ser entre 0 e 10");
        }




        textoFinal = "Nome: [" + nomeFinal + "]" + "\n" +
                     "Email: [ " + emailFinal + "]" + "\n" +
                     "Idade: [ " + idadeFinal + " ]" + "\n" +
                     "Disciplna: [" + disciplinaFinal + "]" + "\n" +
                     "Notas 1o e 2o Bimestres [ " + nota01Text + ", " + nota02Text + " ]" + "\n" +
                     "Média = " + mediaFinal + "\n" +
                     "Situação: " + mensagemAprovReprov;

        textInformacoes.setText(textoFinal);

        }catch (erros ms) {
            textInformacoes.setText(ms.getMessage());
        }


    }
    public class erros extends Exception {
        public erros(String ms) {
            super(ms);
        }
    }

}