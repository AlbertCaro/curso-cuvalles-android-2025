package dev.albertocaro.cursocuvalles2025.ui.post

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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
import dev.albertocaro.cursocuvalles2025.databinding.ActivityPostEditBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostEditBinding

    private val viewModel: PostViewModel by viewModels()

    @Inject
    lateinit var validateForm: PostFormValidateHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPostEditBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appbar.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "Editar post"

        val id = intent.getIntExtra("postId", 1)

        viewModel.findPost(id)

        validateForm(binding.form) { title, content ->
            viewModel.editPost(id, title, content)

            Toast.makeText(this, "Post actualizado correctamente", Toast.LENGTH_SHORT)
                .show()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewUiState.collect { state ->
                    when(state) {
                        is PostViewUiState.Error -> {
                        }
                        PostViewUiState.Loading -> {

                        }
                        is PostViewUiState.Success -> {
                            binding.form.title.setText(state.post.title)
                            binding.form.content.setText(state.post.content)
                        }
                    }
                }
            }
        }

//        viewModel.post.observe(this) { post ->
//            binding.form.title.setText(post.title)
//            binding.form.content.setText(post.content)
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}