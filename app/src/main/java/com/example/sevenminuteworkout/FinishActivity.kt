package com.example.sevenminuteworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuteworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarExercise)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.finishButton.setOnClickListener {
            finish()
        }

    }
}
