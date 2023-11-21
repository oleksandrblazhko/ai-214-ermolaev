package com.example.tspp

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tspp.databinding.ActivityMedicialChatBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MedicialChat : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityMedicialChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicialChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        val bSend = binding.bSend
        val eName = binding.eName
        val eMessage = binding.eMessage

        eName.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        eName.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source.toString().isEmpty() || source.toString().matches("[a-zA-Z]+".toRegex())) {
                source
            } else {
                Toast.makeText(applicationContext, "Ім'я може складатися лише з літер.", Toast.LENGTH_SHORT).show()
                ""
            }
        })


        val maxLengthMessage = 1000
        val inputFilter = InputFilter.LengthFilter(maxLengthMessage)
        eMessage.filters = arrayOf(inputFilter)
        eMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                val currentLength = editable?.length ?: 0
                if (currentLength == maxLengthMessage) {
                    Toast.makeText(applicationContext, "Досягнута максимальна кількість символів.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        bSend.setOnClickListener {
            val userName = eName.text.toString()
            val userMessage = eMessage.text.toString()
            if (userMessage.isNotEmpty()) {
                writeToDatabase(userName, userMessage)
                eName.setText("")
                eMessage.setText("")
            } else {
                Toast.makeText(applicationContext, "Ви не можете відправити пустий запит.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getMessageInfo(userMessage: String): Int {
        if (isValidMessage(userMessage)) {
            return 1 // Користувач коректно ввів дані
        }

        if (!isValidMessage(userMessage)) {
            return -1 // Текст не відповідає 
            умові або умовам
        }
    }
    fun (userMessage: String): Boolean {
        return userMessage.length in 1..1000 && userMessage.all { it.isLetter() || it.isWhitespace() }
    }


    private fun writeToDatabase(userName: String, userMessage: String) {
        val key = databaseReference.child("messages").push().key
        val message = Message(userName, userMessage)
        }
    }
}
