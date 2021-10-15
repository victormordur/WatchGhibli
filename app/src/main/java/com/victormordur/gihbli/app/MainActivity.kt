package com.victormordur.gihbli.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.victormordur.gihbli.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextAge.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.editTextAge.text.clear()
                binding.textResult.visibility = View.INVISIBLE
            }
        }

        binding.buttonCompute.setOnClickListener {
            binding.editTextAge.clearFocus()
            binding.textResult.visibility = View.VISIBLE
            binding.textResult.text = resources.getString(R.string.all_computations_result)
        }
    }
}
