package dev.albertocaro.cursocuvalles2025.ui.post

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.R
import dev.albertocaro.cursocuvalles2025.databinding.FragmentCreatePostBinding
import dev.albertocaro.cursocuvalles2025.ui.quotes.QuotesViewModel

@AndroidEntryPoint
class CreatePostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels()

    private lateinit var binding: FragmentCreatePostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)

        binding.form.saveButton.setOnClickListener {
            val title = binding.form.title.text
            val content = binding.form.content.text

            var hasError = false

            if (title == null || title.toString() == "") {
                hasError = true
                binding.form.titleLayout.error = "Este campo no puede quedar vacío"
            }

            if (content == null || content.toString() == "") {
                hasError = true
                binding.form.contentLayout.error = "Este campo no puede quedar vacío"
            }

            if (hasError) return@setOnClickListener

            viewModel.createPost(title.toString(), content.toString())

            Snackbar.make(binding.root, "Post guardado con éxito", 2000).show()
            binding.form.title.setText("")
            binding.form.content.setText("")
//            Toast.makeText(requireContext(), "Post guardado con éxito!", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}