package com.ajeet.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.ajeet.quizapp.databinding.ActivityUserDataBinding

class UserData : AppCompatActivity() {
    private lateinit var binding: ActivityUserDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            var firstEdt=binding.firstP.toString()
            var secondEdt=binding.secondP.toString()
            if(firstEdt==null || secondEdt==null){
                Toast.makeText(this,"Please provide the Required Details",Toast.LENGTH_LONG).show()
            }
            else{
                val intent=Intent(this,MainActivity::class.java)
                intent.putExtra("first",firstEdt)
                intent.putExtra("second",secondEdt)
                startActivity(intent)
            }
        }

    }
}