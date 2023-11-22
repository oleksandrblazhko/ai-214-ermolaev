import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

data class Message(val userName: String = "", val userMessage: String = "")

object TestCases {
    fun getMessageInfo(userMessage: String): Int {
        if (isValidMessage(userMessage)) {
            return 1 // User entered data correctly
        }

        if (!isValidMessage(userMessage)) {
            return -1 // Text does not meet the condition(s)
        }
        return -4 // Added a default value if no other matches are found
    }

    fun isValidMessage(userMessage: String): Boolean {
        return userMessage.length in 1..1000 && userMessage.all { it.isLetter() || it.isWhitespace() }
    }
}

@RunWith(AndroidJUnit4::class)
class MedicialChatTest {

    @Test
    fun testValidMessage() {
        // Launch the activity and perform the test
        ActivityScenario.launch<MedicialChat>(MedicialChat::class.java).onActivity { medicalchat ->
            val res = TestCases.getMessageInfo("Hi I want to be free")
            assertEquals(1, res)
        }
    }

    @Test
    fun testInvalidMessage() {
        // Launch the activity and perform the test
        ActivityScenario.launch<MedicialChat>(MedicialChat::class.java).onActivity { medicalchat ->
            val res = TestCases.getMessageInfo("")
            assertEquals(-1, res)
        }
    }

    @Test
    fun testInvalidMessage2() {
        // Launch the activity and perform the test
        ActivityScenario.launch<MedicialChat>(MedicialChat::class.java).onActivity { medicalchat ->

}
