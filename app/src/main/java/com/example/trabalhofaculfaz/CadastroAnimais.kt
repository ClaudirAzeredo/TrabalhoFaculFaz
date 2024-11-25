package com.example.trabalhofaculfaz

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class CadastroAnimais : AppCompatActivity() {

    private lateinit var spinnerSexo: Spinner
    private lateinit var etMachoDataVacina: EditText
    private lateinit var etMachoProximaVacina: EditText
    private lateinit var etMachoPeso: EditText
    private lateinit var etTipoAnimal: EditText
    private lateinit var etMachoRegistro: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvCadastroInfo: TextView

    private val animaisCadastrados = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_animais)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        spinnerSexo = findViewById(R.id.spinnerSexo)
        etMachoDataVacina = findViewById(R.id.etMachoDataVacina)
        etMachoProximaVacina = findViewById(R.id.etMachoProximaVacina)
        etMachoPeso = findViewById(R.id.etMachoPeso)
        etMachoRegistro = findViewById(R.id.etMachoRegistro)
        etTipoAnimal = findViewById(R.id.etTipoAnimal)
        btnRegister = findViewById(R.id.btnRegister)
        tvCadastroInfo = findViewById(R.id.tvCadastroInfo)

        val selectedAnimais = intent.getStringExtra("selectedAnimais")
        etTipoAnimal.setText(selectedAnimais)

        etMachoProximaVacina.setOnClickListener {
            showDatePickerDialog { date ->
                etMachoProximaVacina.setText(date)
            }
        }

        val sexoOptions = listOf("Selecione o sexo", "Macho", "Fêmea")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sexoOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSexo.adapter = adapter

        btnRegister.setOnClickListener {

            val tipoAnimais = etTipoAnimal.text.toString()
            val sexoSelecionado = spinnerSexo.selectedItem.toString()
            val dataVacina = etMachoDataVacina.text.toString()
            val proximaVacina = etMachoProximaVacina.text.toString()
            val peso = etMachoPeso.text.toString()
            val registro = etMachoRegistro.text.toString()

            if (sexoSelecionado == "Selecione o sexo" || tipoAnimais.isNotEmpty() ||dataVacina.isNotEmpty() || proximaVacina.isNotEmpty()
                || peso.isNotEmpty() || registro.isNotEmpty())

            else {
                val animalInfo = """
            Tipo de Animal: $tipoAnimais
            Sexo: $sexoSelecionado
            Data da Vacina: $dataVacina
            Próxima Vacina: $proximaVacina
            Peso: $peso kg
            N° Registro: $registro
        """.trimIndent()

                animaisCadastrados.add(animalInfo)

                tvCadastroInfo.text = animaisCadastrados.joinToString(separator = "\n\n") { it }

                spinnerSexo.setSelection(0)
                etMachoDataVacina.text.clear()
                etMachoProximaVacina.text.clear()
                etMachoPeso.text.clear()
                etMachoRegistro.text.clear()


                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog(onDateSet: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->

                val formattedDate = "${selectedDay.toString().padStart(2, '0')}/" +
                        "${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
                onDateSet(formattedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
