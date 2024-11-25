package com.example.trabalhofaculfaz

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EscolhaActivity : AppCompatActivity() {

    private lateinit var tipoAnimalSpinner: Spinner
    private lateinit var tipoColheitaSpinner: Spinner
    private lateinit var cadastrarAnimalButton: Button
    private lateinit var cadastrarColheitaButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha)

        tipoAnimalSpinner = findViewById(R.id.tipoAnimalSpinner)
        tipoColheitaSpinner = findViewById(R.id.tipoColheitaSpinner)
        cadastrarAnimalButton = findViewById(R.id.cadastrarAnimalButton)
        cadastrarColheitaButton = findViewById(R.id.cadastrarColheitaButton)

        val animalOptions = resources.getStringArray(R.array.tipo_animais)
        val animalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, animalOptions)
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoAnimalSpinner.adapter = animalAdapter

        val colheitaOptions = resources.getStringArray(R.array.tipo_colheitas)
        val colheitaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colheitaOptions)
        colheitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoColheitaSpinner.adapter = colheitaAdapter

        cadastrarAnimalButton.setOnClickListener {
            val selectedAnimal = tipoAnimalSpinner.selectedItem.toString()

            val intent = Intent(this, CadastroAnimais::class.java)

            intent.putExtra("selectedAnimal", selectedAnimal)

            startActivity(intent)

            Toast.makeText(this, "Animal $selectedAnimal selecionado!", Toast.LENGTH_SHORT).show()
        }

        cadastrarColheitaButton.setOnClickListener {
            val selectedColheita = tipoColheitaSpinner.selectedItem.toString()

            val intent = Intent(this, CadastroColheitas::class.java)

            intent.putExtra("selectedColheita", selectedColheita)

            startActivity(intent)

            Toast.makeText(this, "Colheita $selectedColheita cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}
