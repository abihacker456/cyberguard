package com.abinet.cyberguard.ui

data class QuizQuestion(
    val scenario: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

object QuizData {

    val QUESTIONS = listOf(

        QuizQuestion(
            scenario = "Email from \"PayPa1 Support\":\n\n\"Your account is suspended. Verify within 24 hours: paypa1-secure.com/verify\"",
            options = listOf("Safe", "Suspicious"),
            correctIndex = 1,
            explanation = "Three red flags: the domain uses a digit '1' instead of 'L', the message creates false urgency, and it asks you to log in through a link. Always type the real domain manually or open the official app."
        ),

        QuizQuestion(
            scenario = "SMS from an unknown number:\n\n\"Your parcel is waiting. A $1.99 redelivery fee is required: bit.ly/xzK9\"",
            options = listOf("Safe", "Suspicious"),
            correctIndex = 1,
            explanation = "Shortened URLs hide the real destination, the fee is unusually small to reduce suspicion, and you did not order anything. Delivery scams are one of the most common SMS phishing patterns."
        ),

        QuizQuestion(
            scenario = "Email from your bank:\n\n\"A new device signed in to your account. If this was not you, call the number on the back of your card.\"",
            options = listOf("Safe", "Suspicious"),
            correctIndex = 0,
            explanation = "No link, no request for credentials, and it tells you to verify through a channel you already trust. This is a well-formed security notice. Always call the number on your card, not a number in the message."
        ),

        QuizQuestion(
            scenario = "WhatsApp from \"Mom (new number)\":\n\n\"I'm using a friend's phone. My line is blocked. Can you send me $50 urgently?\"",
            options = listOf("Safe", "Suspicious"),
            correctIndex = 1,
            explanation = "Classic family-emergency scam. Claims about a new number, urgency, and a money request are textbook signals. Verify by calling your mother's known number before doing anything."
        ),

        QuizQuestion(
            scenario = "Email from \"IT Department\":\n\n\"We are migrating accounts. Confirm your password by clicking here to avoid losing access.\"",
            options = listOf("Safe", "Suspicious"),
            correctIndex = 1,
            explanation = "No legitimate IT department asks for your password. Ever. Migration notices come through internal channels and never require users to 'confirm' a password. Report this to your real IT team."
        )
    )
}