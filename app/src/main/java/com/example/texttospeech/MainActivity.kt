package com.example.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.texttospeech.databinding.ActivityMainBinding
import com.example.texttospeech.ui.theme.TextToSpeechTheme
import java.util.Locale

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {

    private var tts : TextToSpeech?= null
    private var binding : ActivityMainBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        tts= TextToSpeech(this, this)

        binding?.btn?.setOnClickListener {
            if(binding?.editTxt?.text!!.isEmpty())
            {
                Toast.makeText(this, "Please enter some text ",Toast.LENGTH_LONG).show()
            }
            else
            {
                speakOut(binding?.editTxt?.text!!.toString())
            }
        }

    }

    override fun onInit(status: Int) {

        if(status== TextToSpeech.SUCCESS)
        {
            val result=tts!!.setLanguage(Locale("hi","IN"))

            if(result== TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("lang","This is not supported")
            }

        }
    }

    private fun speakOut(text : String)
    {

        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()

        if(tts !=null)
        {
            tts?.stop()
            tts?.shutdown()
        }

        binding=null
    }
}
// initialazation
//onit function
//speak function
//ondestroy
