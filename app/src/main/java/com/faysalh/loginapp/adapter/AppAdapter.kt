package com.faysalh.loginapp.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faysalh.loginapp.*
import com.faysalh.loginapp.database.AppDatabase
import com.faysalh.loginapp.databinding.RowItemBinding
import com.faysalh.loginapp.entity.AppModel



class AppAdapter(val context: Context,var callback: (AppModel, String) -> Unit):ListAdapter<AppModel,AppAdapter.AppViewHolder>(TodoDeffCallback()) {
   private lateinit var binding: RowItemBinding


    class AppViewHolder(
        private val context: Context, private val binding: RowItemBinding, private val callback: (AppModel, String) -> Unit
        ) :RecyclerView.ViewHolder(binding.root) {
        fun bind(appModel: AppModel){
           binding.titleET.text=appModel.title
             binding.descripetET.text = appModel.description
             binding.DateET.text = formattedDateTime(appModel.date,"dd/MM/yyyy")
             binding.timeET.text = formattedDateTime(appModel.time,"hh:mm a")


            //Favorite
           /* binding.cbHard.setOnCheckedChangeListener { checkBox, isChecked ->
                if (isChecked){
                    Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                    appModel.isFavorite = true
                    Log.e("TAG", "bind:${appModel.isFavorite} ", )
                    callback(appModel, on)


                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                    appModel.isFavorite = false
                    Log.e("TAG", "End:${appModel.isFavorite} ", )
                    callback(appModel, off)
                }
            }*/

            //Delete
            binding.deletebtn.setOnClickListener {
               callback(appModel, deleteClick)
            }

            //Edit
            binding.allItem.setOnClickListener {
                val intent = Intent(context,EditMainActivity::class.java)
                intent.putExtra("note_id",appModel.id)
                intent.putExtra("title",appModel.title)
                intent.putExtra("descrip",appModel.description)
                intent.putExtra("favorite",appModel.isFavorite)
               context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        binding= RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AppViewHolder(context,binding,callback)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
       val positions = getItem(position)
        holder.bind(positions)


    }


}

class TodoDeffCallback:DiffUtil.ItemCallback<AppModel>() {
    override fun areItemsTheSame(oldItem: AppModel, newItem: AppModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppModel, newItem: AppModel): Boolean {
        return oldItem == newItem
    }



}
