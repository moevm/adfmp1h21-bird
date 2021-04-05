package com.example.adfmp1h21_bird.ui.settings



import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.adfmp1h21_bird.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.registerOnSharedPreferenceChangeListener(::onSharedPreferenceChanged)
    }

    private fun onSharedPreferenceChanged(prefs: SharedPreferences?, key: String?) {
        // TODO Проверять общие настройки, ключевые параметры и изменять UI
        // или поведение приложения, если потребуется.

        Toast.makeText(context, "changed pref with key: $key", Toast.LENGTH_SHORT).show()
    }
}

