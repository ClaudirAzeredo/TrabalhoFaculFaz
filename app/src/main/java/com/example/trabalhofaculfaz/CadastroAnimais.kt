package com.example.trabalhofaculfaz

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class CadastroAnimais : AppCompatActivity() {

    // Declaração das variáveis para os elementos de UI
    private lateinit var spinnerSexo: Spinner
    private lateinit var etMachoDataVacina: EditText
    private lateinit var etMachoProximaVacina: EditText
    private lateinit var etMachoPeso: EditText
    private lateinit var etMachoRegistro: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvCadastroInfo: TextView

    // Lista para armazenar os animais cadastrados
    private val animaisCadastrados = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_animais)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Adiciona a seta de voltar


        // Ação ao clicar na seta de voltar
        toolbar.setNavigationOnClickListener {
            finish() // Fecha a activity e volta para a anterior
        }

        // Inicializando os elementos do layout
        spinnerSexo = findViewById(R.id.spinnerSexo)
        etMachoDataVacina = findViewById(R.id.etMachoDataVacina)
        etMachoProximaVacina = findViewById(R.id.etMachoProximaVacina)
        etMachoPeso = findViewById(R.id.etMachoPeso)
        etMachoRegistro = findViewById(R.id.etMachoRegistro)
        btnRegister = findViewById(R.id.btnRegister)
        tvCadastroInfo = findViewById(R.id.tvCadastroInfo)

        // Configurando os campos de data com DatePicker
        etMachoDataVacina.setOnClickListener {
            showDatePickerDialog { date ->
                etMachoDataVacina.setText(date)
            }
        }

        etMachoProximaVacina.setOnClickListener {
            showDatePickerDialog { date ->
                etMachoProximaVacina.setText(date)
            }
        }

        // Configuração do Spinner
        val sexoOptions = listOf("Selecione o sexo", "Macho", "Fêmea")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sexoOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSexo.adapter = adapter

        // Configuração do botão de cadastro
        btnRegister.setOnClickListener {
            // Obtendo os valores inseridos pelo usuário
            val sexoSelecionado = spinnerSexo.selectedItem.toString()
            val dataVacina = etMachoDataVacina.text.toString()
            val proximaVacina = etMachoProximaVacina.text.toString()
            val peso = etMachoPeso.text.toString()
            val registro = etMachoRegistro.text.toString()

            // Verificando se todos os campos estão preenchidos
            if (sexoSelecionado == "Selecione o sexo" || dataVacina.isEmpty() || proximaVacina.isEmpty() || peso.isEmpty() || registro.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            } else {
                // Criando uma string para o animal
                val cadastroInfo = """
                    Sexo: $sexoSelecionado
                    Data da Vacina: $dataVacina
                    Próxima Vacina: $proximaVacina
                    Peso: $peso kg
                    N° Registro: $registro
                """.trimIndent()

                // Adicionando o cadastro à lista de animais
                animaisCadastrados.add(cadastroInfo)

                // Atualizando o TextView com a lista de animais cadastrados
                tvCadastroInfo.text = animaisCadastrados.joinToString(separator = "\n\n") { it }

                // Limpando os campos após o cadastro
                spinnerSexo.setSelection(0) // Reseta o Spinner
                etMachoDataVacina.text.clear()
                etMachoProximaVacina.text.clear()
                etMachoPeso.text.clear()
                etMachoRegistro.text.clear()

                // Exibindo um Toast de confirmação
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
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
