package com.faysalh.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.faysalh.loginapp.databinding.ActivityEditMainBinding
import com.faysalh.loginapp.entity.AppModel
import com.faysalh.loginapp.viewmodel.AppViewModel
import com.google.android.material.snackbar.Snackbar

class EditMainActivity : AppCompatActivity() {
    private lateinit var noteEntiry :AppModel
    private var  titleS:String? = null
    private var descript:String? = null
    val date =System.currentTimeMillis()
    val time = System.currentTimeMillis()
     var noteId :Int= 0
    lateinit var binding: ActivityEditMainBinding
   private val appViewModel:AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        noteId = intent.getIntExtra("note_id",0)

        titleS = intent.getStringExtra("title")
        descript =intent.getStringExtra("descrip")
         binding.titleEdtET.setText(titleS)
        binding.descriptEdtET.setText(descript)

        binding.backBtnEdt.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.doneEdtBtn.setOnClickListener {

            var titleChange = binding.titleEdtET.text.toString()
            var descripChange = binding.descriptEdtET.text.toString()

            if (titleChange.isNotEmpty()|| descripChange.isNotEmpty()){
                noteEntiry = AppModel(id = noteId!!, title = titleChange, description = descripChange, time = time, date = date)
                appViewModel.edit(noteEntiry)

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }else{
                Snackbar.make(it,"Title and Description cannot be Empty", Snackbar.LENGTH_LONG).show()
            }




        }
    }
}