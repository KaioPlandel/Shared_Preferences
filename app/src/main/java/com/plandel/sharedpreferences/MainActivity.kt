package com.plandel.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plandel.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.buttonSave.setOnClickListener {

            val name = binding.editName.text.toString()
            val age  = binding.editAge.text.toString().toInt()
            val isAdult = binding.radioButton.isChecked

            editor.apply {
                putString("name",name)
                putInt("age",age)
                putBoolean("isAdult", isAdult)
                apply()
            }
        }

        binding.buttonLoad.setOnClickListener {
            val name = sharedPref.getString("name",null) //em caso name não existe retorne null
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult",false)

            binding.editName.setText(name)
            binding.editAge.setText(age.toString())
            binding.radioButton.isChecked = isAdult

        }
    }
}