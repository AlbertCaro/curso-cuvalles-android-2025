package dev.albertocaro.cursocuvalles2025.ui.counter

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.databinding.ActivityCounterBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CounterActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()

    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCounterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appbar.toolbar)

        title = "Ejemplo contador"


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CounterUiState.Error -> {
                            binding.counter.text = state.message
                        }
                        CounterUiState.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }
                        is CounterUiState.Success -> {
                            binding.loading.visibility = View.GONE
                            binding.counter.visibility = View.VISIBLE
                            binding.counter.text = "Conteo: ${state.counter}"
                        }
                    }
                }
            }
        }

        viewModel.startToCount()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}