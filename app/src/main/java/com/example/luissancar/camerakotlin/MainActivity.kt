package com.example.luissancar.camerakotlin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {

                requestPermissions(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), CAMERA_REQUEST_CODE)
                print("p");
            }


            if (callCameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap=data.extras.get("data") as Bitmap

                    imageView.setImageBitmap(bitmap)
                    convierteBitmap(bitmap)
                }
            }

            else -> {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        }


    }


    fun convierteBitmap(bitmap: Bitmap) {
        println(bitmap.width)
        println(bitmap.height)


        for (x in 0..bitmap.width-1) {
            for (y in 0..bitmap.height-1) {
              // print("x ") ; println(x)
               // print("y "); println(y)
                val color=bitmap.getPixel(x,y)
                val A = color shr 24 and 0xff // or color >>> 24
                val R = color shr 16 and 0xff
                val G = color shr 8 and 0xff
                val B = color and 0xff
                println("rojo $R, verde $G,azul $B")
            }
        }

    }
}
