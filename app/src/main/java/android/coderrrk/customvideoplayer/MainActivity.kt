package android.coderrrk.customvideoplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){

    lateinit var button1: Button
    lateinit var button2: Button
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button1 = findViewById(R.id.button1)
        button1.setOnClickListener(View.OnClickListener {
            val webUrl: String? = getString(R.string.first)
            val intent: Intent = Intent(this@MainActivity, NewIntentClass::class.java)
            Log.d("AA", "" + webUrl)
            intent.putExtra("WEB_VIEW", webUrl)
            startActivity(intent)
        })

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener(View.OnClickListener {
            val webUrl: String? = getString(R.string.second)
            val intent: Intent = Intent(this@MainActivity, NewIntentClass::class.java)
            Log.d("AA", "" + webUrl)
            intent.putExtra("WEB_VIEW", webUrl)
            startActivity(intent)
        })
    }
}






