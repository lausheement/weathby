package com.example.weathby

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.weathby.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val repository = WeatherRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCurrentForecast()
    }

    private fun getCurrentForecast() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                runCatching {
                    repository.getForecast("London")
                }.onSuccess {
                    Log.i("success", "onCreate: ${it.body()}")
                }.onFailure {
                    Log.i("fail", "onCreate: $it")
                }
            }
        }
    }
}