package org.hasancottage.objectdetection

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import org.hasancottage.objectdetection.activity.AboutThisApp
import org.hasancottage.objectdetection.activity.ActivityForGalarry
import org.hasancottage.objectdetection.activity.ObjectActivity
import org.hasancottage.objectdetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val CODE = 101
    private var reviewInfo: ReviewInfo? = null
    private lateinit var reviewManager: ReviewManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ReviewManager
        reviewManager = ReviewManagerFactory.create(this)
        // Request review flow and handle the result
        val reviewInfoTask = reviewManager.requestReviewFlow()
        reviewInfoTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                reviewInfo = task.result
            } else {
                Toast.makeText(this, "Failed to request review flow", Toast.LENGTH_SHORT).show()
            }
        }

        binding.camer.setOnClickListener {
            startActivity(Intent(Intent(this,ObjectActivity::class.java)))
        }
        binding.gallary.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent,CODE)

        }

        binding.rateUs.setOnClickListener {
            reviewApp()
        }
        binding.aboutApp.setOnClickListener {
            startActivity(Intent(Intent(this,AboutThisApp::class.java)))
        }


    }
    private fun reviewApp() {

        reviewInfo?.let { info ->
            val flow = reviewManager.launchReviewFlow(this, info)
            flow.addOnCompleteListener { _ ->
                Toast.makeText(this, "Review completed", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Review info not available yet", Toast.LENGTH_SHORT).show()
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