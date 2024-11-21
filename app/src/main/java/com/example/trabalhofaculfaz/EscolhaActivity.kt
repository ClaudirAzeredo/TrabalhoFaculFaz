package com.example.trabalhofaculfaz

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EscolhaActivity : AppCompatActivity() {

    // Declaração das variáveis para os elementos de UI
    private lateinit var tipoAnimalSpinner: Spinner
    private lateinit var tipoColheitaSpinner: Spinner
    private lateinit var cadastrarAnimalButton: Button
    private lateinit var cadastrarColheitaButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha)

        // Inicializando os elementos do layout
        tipoAnimalSpinner = findViewById(R.id.tipoAnimalSpinner)
        tipoColheitaSpinner = findViewById(R.id.tipoColheitaSpinner)
        cadastrarAnimalButton = findViewById(R.id.cadastrarAnimalButton)
        cadastrarColheitaButton = findViewById(R.id.cadastrarColheitaButton)

        // Criando o ArrayAdapter para o Spinner do tipo de animal
        val animalOptions = resources.getStringArray(R.array.tipo_animais)
        val animalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, animalOptions)
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoAnimalSpinner.adapter = animalAdapter

        // Criando o ArrayAdapter para o Spinner do tipo de colheita
        val colheitaOptions = resources.getStringArray(R.array.tipo_colheitas)
        val colheitaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colheitaOptions)
        colheitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoColheitaSpinner.adapter = colheitaAdapter

        // Configurando o clique do botão "Cadastrar Animal"
        cadastrarAnimalButton.setOnClickListener {
            // Obtendo o tipo de animal selecionado
            val selectedAnimal = tipoAnimalSpinner.selectedItem.toString()

            // Criando a Intent para navegar para a tela CadastroAnimais
            val intent = Intent(this, CadastroAnimais::class.java)

            // Passando a informação do tipo de animal para a próxima tela
            intent.putExtra("selectedAnimal", selectedAnimal)

            // Iniciando a nova Activity
            startActivity(intent)

            // Exibindo um Toast de confirmação
            Toast.makeText(this, "Animal $selectedAnimal selecionado!", Toast.LENGTH_SHORT).show()
        }

        // Configurando o clique do botão "Cadastrar Colheita"
        cadastrarColheitaButton.setOnClickListener {
            // Obtendo o tipo de colheita selecionado
            val selectedColheita = tipoColheitaSpinner.selectedItem.toString()

            // Criando a Intent para navegar para a tela CadastroColheitas
            val intent = Intent(this, CadastroColheitas::class.java)

            // Passando a informação do tipo de colheita para a próxima tela
            intent.putExtra("selectedColheita", selectedColheita)

            // Iniciando a nova Activity
            startActivity(intent)

            // Exibindo um Toast de confirmação
            Toast.makeText(this, "Colheita $selectedColheita cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}
