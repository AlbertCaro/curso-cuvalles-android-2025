package dev.albertocaro.cursocuvalles2025.ui.post

import dev.albertocaro.cursocuvalles2025.databinding.PostFormBinding
import javax.inject.Inject

class PostFormValidateHelper @Inject constructor() {

    operator fun invoke(form: PostFormBinding, action: (String, String) -> Unit) {
        form.saveButton.setOnClickListener {
            val title = form.title.text
            val content = form.content.text

            var hasError = false

            if (title == null || title.toString() == "") {
                hasError = true
                form.titleLayout.error = "Este campo no puede quedar vacío"
            }

            if (content == null || content.toString() == "") {
                hasError = true
                form.contentLayout.error = "Este campo no puede quedar vacío"
            }

            if (hasError) return@setOnClickListener

            action(title.toString(), content.toString())

            form.title.setText("")
            form.content.setText("")
        }

    }
}