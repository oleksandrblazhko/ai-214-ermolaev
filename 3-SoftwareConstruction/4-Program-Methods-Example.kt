@Parcelize
data class User(
    var userName:String = "",
    var userMessage:String = "") : Parcelable

class MedicialChat : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityMedicialChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация привязок и базы данных Firebase
        binding = ActivityMedicialChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference

        // Получение ссылок на элементы интерфейса
        val bSend = binding.bSend
        val eName = binding.eName
        val eMessage = binding.eMessage

        // Настройка поля ввода для имени
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

        // Настройка ограничения на количество символов в поле ввода сообщения
        val maxLengthMessage = 1000
        val inputFilter = InputFilter.LengthFilter(maxLengthMessage)
        eMessage.filters = arrayOf(inputFilter)
        eMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Проверка на достижение максимального числа символов в сообщении
                val currentLength = editable?.length ?: 0
                if (currentLength == maxLengthMessage) {
                    Toast.makeText(applicationContext, "Досягнута максимальна кількість символів.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Обработчик нажатия на кнопку "Отправить"
        bSend.setOnClickListener {
            // Получение имени пользователя и сообщения из полей ввода
            val userName = eName.text.toString()
            val userMessage = eMessage.text.toString()

            // Запись сообщения в базу данных Firebase
            writeToDatabase(userName, userMessage)

            // Очистка полей ввода после отправки сообщения
            eName.setText("")
            eMessage.setText("")
        }
    }

    // Метод для записи сообщения в базу данных Firebase
    private fun writeToDatabase(userName: String, userMessage: String) {
        // Генерация уникального ключа для сообщения
        val key = databaseReference.child("messages").push().key
        val message = Message(userName, userMessage)

        // Запись сообщения в базу данных, если ключ не пустой
        if (key != null) {
            databaseReference.child("messages").child(key).setValue(message)
        }
    }
}
