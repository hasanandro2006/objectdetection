package org.hasancottage.objectdetection.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import org.hasancottage.objectdetection.R
import org.hasancottage.objectdetection.databinding.ActivityForGalarryBinding
import org.hasancottage.objectdetection.dataclass.BoxLable

class ActivityForGalarry : AppCompatActivity() {


    lateinit var objectDetection: ObjectDetector
    lateinit var binding : ActivityForGalarryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForGalarryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receivedBitmap: Uri? = intent.getParcelableExtra("bitmap")

        // Multiple object detection in static images
        val options = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .enableClassification()  // Optional
            .build()
        objectDetection = ObjectDetection.getClient(options)

        binding.imageView5.setOnClickListener {
            onBackPressed()
        }

        if (receivedBitmap != null) {
            try {
                val inputStream = contentResolver.openInputStream(receivedBitmap)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                if (bitmap != null) {
                    binding.imageView.setImageBitmap(bitmap)
                   setLable(bitmap)
                } else {
                    Toast.makeText(this,"Image Not Come", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun setLable(bitmap: Bitmap) {

        var image = InputImage.fromBitmap(bitmap, 0)

        objectDetection.process(image)
            .addOnSuccessListener { ObjectD ->

                if (ObjectD.isNotEmpty()){
                    var stringBuilder = StringBuilder()
                    val boxes = ArrayList<BoxLable>()
                    for ( objectd: DetectedObject in ObjectD){
                        if (objectd.labels.isNotEmpty()){
                            var lable = objectd.labels[0].text
                            stringBuilder.append(lable).append(" : ").append(objectd.labels.get(0).confidence).append("\n")
                            boxes.add(BoxLable(objectd.boundingBox,lable))
                        }
                    }
                    drawDetctionResult(boxes,bitmap)
                    binding.nameItem.text = stringBuilder
                }else{
                    binding.nameItem.text = "Unknown Item"
                }

            }
            .addOnFailureListener { e ->

            }
    }

    fun drawDetctionResult(label : List<BoxLable>,bitmap: Bitmap){

        var storeImg = bitmap.copy(Bitmap.Config.ARGB_8888,true)

        var canvas = Canvas(storeImg)

        val paintRec = Paint()
        paintRec.setColor(Color.RED)
        paintRec.style = Paint.Style.STROKE
        paintRec.strokeWidth = 5f

        val painLable = Paint()
        painLable.setColor(Color.GREEN)
        painLable.style = Paint.Style.FILL_AND_STROKE
        painLable.textSize = 40f

        for ( box in label){
            canvas.drawRect(box.rect, paintRec)

            val lableSize = Rect(0,0,0,0)
            painLable.getTextBounds(box.lable,0,box.lable.length,lableSize)

            var fontSize: Float = painLable.textSize * box.rect.width() / lableSize.width()

            if (fontSize < painLable.textSize){
                painLable.textSize = fontSize
            }

            canvas.drawText(box.lable, box.rect.left.toFloat(),box.rect.top + lableSize.height().toFloat(),painLable)

            binding.imageView.setImageBitmap(storeImg)

        }


    }
}