package com.example.flagquiz

object Constants {

    //Constants for storing all data for this app.

    //to store user inserted username
    const val USER_NAME: String = "user name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    //storing all questions
    fun getQuestions(): ArrayList<Question> {

        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Latvia", "Austria", "Russia",
            1
        )

        questionsList.add(q1)

        val q2 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Japan", "Australia", "Austria", "USA",
            2
        )

        questionsList.add(q2)

        val q3 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Lithuania", "Latvia", "Germany", "Belgium",
            4
        )

        questionsList.add(q3)

        val q4 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Costa Rica", "Brazil", "Estonia", "Russia",
            2
        )

        questionsList.add(q4)

        val q5 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark", "Finland", "Norway", "Sweden",
            1
        )

        questionsList.add(q5)

        val q6 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Fiji", "Argentina", "Austria", "Greece",
            1
        )

        questionsList.add(q6)

        val q7 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Belgium", "Hungary", "Montenegro", "Germany",
            4
        )

        questionsList.add(q7)

        val q8 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Kazakhstan", "Pakistan", "India", "South Africa",
            3
        )

        questionsList.add(q8)

        val q9 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Belarus", "Ukraine", "Nigeria",
            1
        )

        questionsList.add(q9)

        val q10 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "New Zealand", "Australia", "Italy", "France",
            1
        )

        questionsList.add(q10)

        return questionsList
    }
}