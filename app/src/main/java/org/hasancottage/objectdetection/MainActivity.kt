package org.hasancottage.objectdetection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.hasancottage.objectdetection.activity.ObjectActivity
import org.hasancottage.objectdetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(Intent(this,ObjectActivity::class.java)))
        }


    }
}