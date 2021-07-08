package com.example.flagquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityFlagQuizQuestionsBinding


class FlagQuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFlagQuizQuestionsBinding
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null
    private var mAnswerSelected: Boolean = false
    private var mSubmitPressed: Boolean = false


    //on creation creating first question an set listeners
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        binding = ActivityFlagQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)

        binding.btnSubmit.setOnClickListener(this)
    }

    //method for creating question and showing it to user
    private fun setQuestion() {

        val question = mQuestionsList!![mCurrentPosition - 1]

        mAnswerSelected = false
        mSubmitPressed = false

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "Finish"
        } else {
            binding.btnSubmit.text = "Submit"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        binding.question.text = question.question
        binding.image.setImageResource(question.image)

        binding.tvOption1.text = question.option1
        binding.tvOption2.text = question.option2
        binding.tvOption3.text = question.option3
        binding.tvOption4.text = question.option4
    }

    //method for showing 4 choices all in one color
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOption1)
        options.add(1, binding.tvOption2)
        options.add(2, binding.tvOption3)
        options.add(3, binding.tvOption4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }


    //on click method where we find out which button is pressed
    override fun onClick(v: View?) {
        when (v?.id) {
            //if choice buttons is pressed
            R.id.tv_option1 -> {
                selectedOptionView(binding.tvOption1, 1)
            }
            R.id.tv_option2 -> {
                selectedOptionView(binding.tvOption2, 2)
            }
            R.id.tv_option3 -> {
                selectedOptionView(binding.tvOption3, 3)
            }
            R.id.tv_option4 -> {
                selectedOptionView(binding.tvOption4, 4)
            }
            //if submit button is pressed
            R.id.btn_submit -> {
                if (!mAnswerSelected) {
                    Toast.makeText(this, "Select answer!", Toast.LENGTH_SHORT).show()
                } else if (mSelectedOptionPosition == 0 && mAnswerSelected) { //if user is selected submit second time to go to the next question
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> { // if there are still questions
                            setQuestion()
                        }
                        else -> { //if there are no more questions
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else { // when the users pressed submit first time in this question
                    mSubmitPressed = true
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    mAnswerSelected = true

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "Finish"
                    } else {
                        binding.btnSubmit.text = "Next question"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    //method for changing answer colors
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOption1.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                binding.tvOption2.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                binding.tvOption3.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                binding.tvOption4.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    //method for showing selected user answer
    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        if (!mSubmitPressed) {
            defaultOptionsView()
            mAnswerSelected = true
            mSelectedOptionPosition = selectedOptionNumber
            tv.setTextColor(Color.BLACK)
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
    }
}