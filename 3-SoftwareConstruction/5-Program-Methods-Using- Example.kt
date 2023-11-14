// Приклад правильної взаємодії
val repository = MedicialChat()
val userCode = "123"
val userName = "Mak"
val userMessage = "I want to make some questions for u."
val result = getWorksInfo(userName, userMessage)
                when (result) {
                    1 -> {
                        val message = "Коректно введено дані"
                        Toast.makeText(message)
                    }
                }
                
// Приклад неправильної взаємодії
val repository = MedicialChat()
val userCode = "123"
val userName = "Mak"
val userMessage = " "
val result = getWorksInfo(userName, userMessage)
                when (result) {
                    1 -> {
                        val message = "Запит не відповідає умові або умовам (довжина рядка не може бути пустою та більше 1000 літер)"
                        Toast.makeText(message)
                    }
                }


// Приклад неправильної взаємодії
val repository = MedicialChat()
val userCode = "123"
val userName = "1"
val userMessage = "I want to make some questions for u."
val result = getWorksInfo(userName, userMessage)
                when (result) {
                    1 -> {
                        val message = "Ім'я не може складатися не із літер."
                        Toast.makeText(message)
                    }
                }
