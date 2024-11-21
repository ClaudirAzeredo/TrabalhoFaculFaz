package com.example.trabalhofaculfaz

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


class TelaCadastro : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var notaProdutorEditText: EditText
    private lateinit var dataNascimentoEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var cadastrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_cadastro)

        // Inicializando os campos de texto e o botão
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        notaProdutorEditText = findViewById(R.id.notaProdutorEditText)
        dataNascimentoEditText = findViewById(R.id.dataNascimentoEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        cadastrarButton = findViewById(R.id.cadastrarButton)


        dataNascimentoEditText.setOnClickListener {
            showDatePickerDialog { date ->
                dataNascimentoEditText.setText(date)
            }
        }

        // Evento de clique do botão
        cadastrarButton.setOnClickListener {
            val nome = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val notaProdutor = notaProdutorEditText.text.toString().trim()
            val dataNascimento = dataNascimentoEditText.text.toString().trim()
            val senha = passwordEditText.text.toString().trim()

            // Verificando se todos os campos estão preenchidos
            if (nome.isEmpty() || email.isEmpty() || notaProdutor.isEmpty() || dataNascimento.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("nome", nome)
                    putExtra("email", email)
                    putExtra("senha", senha)
                }
                startActivity(intent)

                // Exibindo um toast de sucesso
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()


                // Fechar a TelaCadastro após a navegação (opcional)
                finish()
            }
        }
    }
    // Método para exibir o DatePickerDialog
    private fun showDatePickerDialog(onDateSet: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formatar a data para o formato desejado
                val formattedDate = "${selectedDay.toString().padStart(2, '0')}/" +
                        "${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
                onDateSet(formattedDate) // Passa a data formatada para o EditText
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
