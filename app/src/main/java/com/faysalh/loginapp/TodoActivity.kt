package com.faysalh.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.faysalh.loginapp.databinding.ActivityTodoBinding
import com.faysalh.loginapp.entity.AppModel
import com.faysalh.loginapp.viewmodel.AppViewModel

class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding
    private lateinit var appViewModel:AppViewModel

    private var passed:Int? = null
    val favorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val date =System.currentTimeMillis()
        val time = System.currentTimeMillis()

        appViewModel = ViewModelProvider(this)[AppViewModel::class.java]

        binding.backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.doneBtn.setOnClickListener {
          //  Toast.makeText(this, "In:$passed", Toast.LENGTH_SHORT).show()

               val title = binding.titleET.text.toString()
               val description = binding.descriptET.text.toString()

               val data = AppModel(id = 0, title = title, description =description,
                   date = date, time = time , isFavorite = favorite)
               Log.e("TAG", "onCreate:$data ", )

               appViewModel.insert(data)
               val intent = Intent(this,MainActivity::class.java)
               startActivity(intent)
        }
    }




}