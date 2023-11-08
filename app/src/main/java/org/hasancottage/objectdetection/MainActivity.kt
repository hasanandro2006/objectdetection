package org.hasancottage.objectdetection

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.hasancottage.objectdetection.activity.ActivityForGalarry
import org.hasancottage.objectdetection.activity.ObjectActivity
import org.hasancottage.objectdetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.camer.setOnClickListener {
            startActivity(Intent(Intent(this,ObjectActivity::class.java)))
        }
        binding.gallary.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent,CODE)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE && resultCode == RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            val intent = Intent(Intent(this,ActivityForGalarry::class.java))
            intent.putExtra("bitmap",selectedImageUri)
            startActivity(intent)

        }
    }
}