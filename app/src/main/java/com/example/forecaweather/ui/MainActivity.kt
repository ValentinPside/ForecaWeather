package com.example.forecaweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forecaweather.R
import com.example.forecaweather.domain.ForecaRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создали репозиторий и вызвали нужный метод
        val forecaRepository = ForecaRepository()
        forecaRepository.getCurrentWeather()
    }
}