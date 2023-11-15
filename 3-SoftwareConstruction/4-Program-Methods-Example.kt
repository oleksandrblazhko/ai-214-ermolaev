@Parcelize
data class User(
    var userName:String = "",
    var userMessage:String = "") : Parcelable

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
            // Проверка на пустой ввод или наличие только букв в имени
            if (source.toString().isEmpty() || source.toString().matches("[a-zA-Z]+".toRegex())) {
                source
            } else {
                // Вывод сообщения об ошибке при нарушении правил ввода
                Toast.makeText(applicationContext, "Ім'я може бути тільки з літер.", Toast.LENGTH_SHORT).show()
                ""
            }
        })
    }
}
class MedicialChat : AppCompatActivity() {
        val maxLengthMessage = 1000
        val inputFilter = InputFilter.LengthFilter(maxLengthMessage)
        eMessage.filters = arrayOf(inputFilter)
        eMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                val currentLength = editable?.length ?: 0
                try{
                    if (currentLength == maxLengthMessage) {
                        Toast.makeText(applicationContext, "Досягнута максимальна кількість символів.", Toast.LENGTH_SHORT).show()
                        return -1
                    }
                } catch (maxLengthMessage == 0){
                    Toast.makeText(applicationContext, "Ви не можете відправити запит із пустим текстом.", Toast.LENGTH_SHORT).show()
                    return -1
                }
            }
        })

        bSend.setOnClickListener {
            val userName = eName.text.toString()
            val userMessage = eMessage.text.toString()
            writeToDatabase(userName, userMessage)
            eName.setText("")
            eMessage.setText("")
        }

    private fun writeToDatabase(userName: String, userMessage: String) {
        val key = databaseReference.child("messages").push().key
        val message = Message(userName, userMessage)

        if (key != null) {
            databaseReference.child("messages").child(key).setValue(message)
        }
    }
}



