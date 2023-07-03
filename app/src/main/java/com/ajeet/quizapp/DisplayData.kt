package com.ajeet.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajeet.quizapp.databinding.ActivityDisplayDataBinding

class DisplayData : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val first=intent.getStringExtra("First")
        val second=intent.getStringExtra("Second")
        if (first != null) {
            if(first > second!!){
                binding.tv1.text="Winner"
                binding.tv2.text="Runner Up"
            }
            else{
                binding.tv1.text="Runner Up"
                binding.tv2.text="Winner"
            }
        }
        binding.player1Report.text="$first/25"
        binding.player2Report.text="$second/25"
    }
}