package com.example.trabalhofaculfaz

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private val NOTIFICATION_PERMISSION_CODE = 1001
    private val CHANNEL_ID = "welcome_channel"

    private lateinit var edtNome: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtSenha: EditText
    private lateinit var btnCadastro: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNome = findViewById(R.id.nameEditText)
        edtEmail = findViewById(R.id.emailEditText)
        edtSenha = findViewById(R.id.passwordEditText)
        btnCadastro = findViewById(R.id.cadastrarButton)
        btnLogin = findViewById(R.id.acessarButton)

        val nomeRecebido = intent.getStringExtra("nome") ?: ""
        val emailRecebido = intent.getStringExtra("email") ?: ""
        val senhaRecebida = intent.getStringExtra("senha") ?: ""

        // Preencher os campos com os dados recebidos (se existirem)
        if (nomeRecebido.isNotEmpty() && emailRecebido.isNotEmpty()) {
            edtNome.setText(nomeRecebido)
            edtEmail.setText(emailRecebido)
        }

        // Verificar permissão de notificações quando o app iniciar
        checkNotificationPermission()

        // Ação para o botão de cadastro
        btnCadastro.setOnClickListener {

            val intent = Intent(this, TelaCadastro::class.java)
                startActivity(intent)
            }

        // Ação para o botão de login
        btnLogin.setOnClickListener {
            val nome = edtNome.text.toString()
            val senha = edtSenha.text.toString()

            if (nome.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                showWelcomeNotification(nome)

                // Navegar para a EscolhaActivity após login bem-sucedido
                val intent = Intent(this, EscolhaActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Acesso realizado com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    // Método para exibir a notificação de "Bem-vindo"
    private fun showWelcomeNotification(nome: String) {
        // Criação do canal de notificação (necessário para Android 8.0 e superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Canal de Boas-vindas",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Canal para notificações de boas-vindas"
            }

            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        // Criar a notificação
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Bem-vindo, $nome!")
            .setContentText("Obrigado por fazer login no aplicativo de Gestão de Fazenda!")
            .setSmallIcon(R.drawable.ic_notification_background) // Defina um ícone de notificação
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Exibir a notificação
        val notificationManager: NotificationManager =
            getSystemService(NotificationManager::class.java)
        notificationManager.notify(0, notification)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão de notificações concedida!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissão de notificações negada.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
