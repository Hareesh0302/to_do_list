package com.example.kotlin2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var text:EditText
    lateinit var add:Button
    lateinit var listview:ListView
    var itemlist=ArrayList<String>()
    var fierhelper=filerhelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text=findViewById(R.id.edittext)
        add=findViewById(R.id.button)
        listview=findViewById(R.id.listview)
        itemlist=fierhelper.readdata(this)
        val arrayadapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemlist)


        listview.adapter=arrayadapter
        add.setOnClickListener {

            var itemname:String=text.text.toString()
            itemlist.add(itemname)
            text.setText("")
            fierhelper.writedata(itemlist,applicationContext)
            arrayadapter.notifyDataSetChanged()
        }
        listview.setOnItemClickListener { parent, view, position, id ->
            var alert=AlertDialog.Builder(this)
            alert.setTitle("Delete")

            alert.setMessage("Are you completed this Task?")
            alert.setCancelable(false)
            alert.setNegativeButton("N0", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alert.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which->
                itemlist.removeAt(position)
                arrayadapter.notifyDataSetChanged()
                fierhelper.writedata(itemlist,applicationContext)

            })
            alert.create().show()
        }

    }
}