package br.eng.pedro_mendes.github_followers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.github_followers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}