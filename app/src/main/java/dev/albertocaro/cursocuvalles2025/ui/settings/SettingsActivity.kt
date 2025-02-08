package dev.albertocaro.cursocuvalles2025.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.databinding.ActivitySettingsBinding
import dev.albertocaro.cursocuvalles2025.databinding.SettingsSwitchItemBinding

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySettingsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appbar.toolbar)

        title = "Configuración"

        val listItems = listOf("Reproducir sonidos")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return when (position) {
                    0 -> {
                        val switchItemBinding = SettingsSwitchItemBinding.inflate(layoutInflater)

                        switchItemBinding.optionTitle.text = listItems[position]

                        switchItemBinding.playSoundsSwitch.setOnCheckedChangeListener { _, isChecked ->
                            viewModel.togglePlaySounds(isChecked)
                        }

                        viewModel.settings.observe(this@SettingsActivity) { setting ->
                            switchItemBinding.playSoundsSwitch.isChecked = setting.soundOnAction
                        }

                        switchItemBinding.root
                    }

                    else -> super.getView(position, convertView, parent)
                }
            }

            override fun getCount(): Int = listItems.size
        }

        binding.settingsList.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.fetchSettings()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}