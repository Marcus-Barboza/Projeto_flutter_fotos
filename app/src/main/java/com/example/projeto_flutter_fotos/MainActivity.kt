package com.example.projeto_flutter_fotos

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var captureIV: ImageView
    private lateinit var imageUrl1 :  Uri

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()) {

        captureIV.setImageURI(null)
        captureIV.setImageURI(imageUrl1)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageUrl1 = createImageUri()
        captureIV = findViewById(R.id.captureImageView)
        val captureImageBtn = findViewById<Button>(R.id.captureImageBtn)
        captureImageBtn.setOnClickListener {
            contract.launch(imageUrl1)
        }

        }
        private fun createImageUri():Uri{
            val image = File(filesDir,"camera_photos.png")
            return FileProvider.getUriForFile(this,
                "com.example.projeto_flutter_fotos.FileProvider",
                image)
        }
    }

