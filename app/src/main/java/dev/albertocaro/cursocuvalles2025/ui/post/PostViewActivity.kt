package dev.albertocaro.cursocuvalles2025.ui.post

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.R
import dev.albertocaro.cursocuvalles2025.databinding.ActivityPostViewBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostViewBinding

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPostViewBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appbar.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewUiState.collect { state ->
                    when(state) {
                        is PostViewUiState.Error -> {
                        }
                        PostViewUiState.Loading -> {
                        }
                        is PostViewUiState.Success -> {
                            title = state.post.title
                            binding.content.setText(state.post.content)
                        }
                    }
                }
            }
        }

//        viewModel.post.observe(this) { post ->
//            title = post.title
//            binding.content.text = post.content
//        }

        val id = intent.getIntExtra("postId", 1)

        viewModel.findPost(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}