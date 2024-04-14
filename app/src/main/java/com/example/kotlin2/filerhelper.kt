package com.example.kotlin2

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class filerhelper {
    var FILENAME="listinfo.dat"
    fun writedata(item:ArrayList<String>,context:Context){
        var fos:FileOutputStream=context.openFileOutput(FILENAME,Context.MODE_PRIVATE)
        var oas=ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }
    fun readdata(context: Context):ArrayList<String>{
        var itemlist= ArrayList<String>()
        try {

            var fis:FileInputStream=context.openFileInput(FILENAME)
            var ois=ObjectInputStream(fis)
            itemlist= ois.readObject() as ArrayList<String>
        }catch (item:FileNotFoundException){
            itemlist=ArrayList()
        }
        return itemlist


    }
}