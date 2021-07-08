package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Game first activity, when user opens app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //starting game, user need to insert his name
        binding.buttonStart.setOnClickListener {
            if (binding.nameInput.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter name!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FlagQuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.nameInput.text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}