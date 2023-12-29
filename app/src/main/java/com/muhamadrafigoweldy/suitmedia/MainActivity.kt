package com.muhamadrafigoweldy.suitmedia

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muhamadrafigoweldy.suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val Palindrome = binding.palindrome.text.toString()

            if (Palindrome.isNotEmpty()) {
                val isPalindrome = isPalindrome(Palindrome)

                if (isPalindrome) {
                    showDialog("is Palindrome")
                } else {
                    showDialog("not palindrome")
                }
            } else {
                showDialog("Please enter sentence first")
            }
        }

        binding.btnNext.setOnClickListener {
            val edName = binding.nameLogin.text.toString()

            val toSecondActivity = Intent(this, SecondActivity::class.java)
            toSecondActivity.putExtra(SecondActivity.EXTRA_NAME, edName)
            startActivity(toSecondActivity)
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace(" ", "").lowercase()

        return cleanInput == cleanInput.reversed()
    }

    private fun showDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(true)
            .create()
        alertDialog.show()
        binding.palindrome.text?.clear()
    }

}