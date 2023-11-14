package com.example.tspp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tspp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Используем ViewBinding для доступа к элементам пользовательского интерфейса
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Присвоение переменным ссылок на элементы пользовательского интерфейса
        val bMedcicialChat = binding.bMedcicialChat
        val bSingChat = binding.bSingChat

        // Обработчик нажатия кнопки Medical Chat
        bMedcicialChat.setOnClickListener {
            val intent = Intent(this, MedicialChat::class.java)
            startActivity(intent)
        }

        // Обработчик нажатия кнопки Sing Chat
        bSingChat.setOnClickListener {
            // Здесь добавьте код для перехода на Sing Chat
        }
    }
}
