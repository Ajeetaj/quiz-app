package com.ajeet.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Display
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.ajeet.quizapp.api.ApiInterface
import com.ajeet.quizapp.api.RetrofitInstance
import com.ajeet.quizapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    //var chances=1
    var amount=5
    var player1_point=0
    var player2_point=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user1 = intent.getStringExtra("first")
        val user2 = intent.getStringExtra("second")
        binding.player1.text = "Player 1"
        binding.player2.text = "Player 2"

        var user_check=0
        binding.player1.setBackgroundColor(resources.getColor(R.color.currentUser))

        getValues(user_check)
        binding.player1Score.text= player1_point.toString()

        user_check=1
        binding.player2.setBackgroundColor(resources.getColor(R.color.currentUser))
        binding.player1.setBackgroundColor(resources.getColor(R.color.white))

        getValues(user_check)
        binding.player2Score.text= player2_point.toString()
        //Toast.makeText(this,"$player2_point",Toast.LENGTH_LONG).show()

        if (user_check==1){
            val intent=Intent(this,DisplayData::class.java)
            intent.putExtra("First",player1_point)
            intent.putExtra("Second",player2_point)
            startActivity(intent)
        }

    }
    private fun getValues(user_check:Int) {
            val api = RetrofitInstance.getInstance().create(ApiInterface::class.java)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val result = api.getQuiz(amount)
                    var counter=0
                    while (counter<amount){
                        if (result.body()!!.results.get(counter).type.equals("boolean")){
                            binding.option1.visibility=View.GONE
                            binding.option2.visibility=View.GONE
                            binding.option3.text= result.body()!!.results.get(counter).correct_answer
                            binding.option4.text= result.body()!!.results.get(counter).incorrect_answers.get(0)

                            binding.option3.setOnClickListener {
                                binding.option3.setBackgroundColor(resources.getColor(R.color.green))
                                if (user_check==0){
                                    player1_point+=5
                                }
                                else{
                                    player2_point+=5
                                }

                            }
                            binding.option4.setOnClickListener {
                                binding.option4.setBackgroundColor(resources.getColor(R.color.red))
                                if (user_check==0){
                                    player1_point-=2
                                }
                                else{
                                    player2_point-=2
                                }
                            }

                        }
                        else{
                            binding.option1.visibility=View.VISIBLE
                            binding.option2.visibility=View.VISIBLE
                            binding.option1.text=result.body()!!.results.get(counter).incorrect_answers.get(0)
                            binding.option2.text=result.body()!!.results.get(counter).incorrect_answers.get(1)
                            binding.option3.text= result.body()!!.results.get(counter).correct_answer
                            binding.option4.text= result.body()!!.results.get(counter).incorrect_answers.get(2)

                            binding.option1.setOnClickListener {
                                binding.option1.setBackgroundColor(resources.getColor(R.color.red))
                                if (user_check==0){
                                    player1_point-=2
                                }
                                else{
                                    player2_point-=2
                                }
                            }
                            binding.option2.setOnClickListener {
                                binding.option2.setBackgroundColor(resources.getColor(R.color.red))
                                if (user_check==0){
                                    player1_point-=2
                                }
                                else{
                                    player2_point-=2
                                }
                            }
                            binding.option3.setOnClickListener {
                                binding.option3.setBackgroundColor(resources.getColor(R.color.green))
                                if (user_check==0){
                                    player1_point+=5
                                }
                                else{
                                    player2_point+=5
                                }
                            }
                            binding.option4.setOnClickListener {
                                binding.option4.setBackgroundColor(resources.getColor(R.color.red))
                                if (user_check==0){
                                    player1_point-=2
                                }
                                else{
                                    player2_point-=2
                                }
                            }
                        }
                        binding.questions.text=result.body()!!.results.get(counter).question

                        delay(10000)
                        binding.option1.setBackgroundColor(resources.getColor(R.color.skinny))
                        binding.option2.setBackgroundColor(resources.getColor(R.color.skinny))
                        binding.option3.setBackgroundColor(resources.getColor(R.color.skinny))
                        binding.option4.setBackgroundColor(resources.getColor(R.color.skinny))
                        counter++

                    }

                } catch (t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
    }
}
