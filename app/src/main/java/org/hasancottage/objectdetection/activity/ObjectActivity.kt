

package org.hasancottage.objectdetection.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.hasancottage.objectdetection.databinding.ActivityObjectBinding


class ObjectActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityObjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityObjectBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}
