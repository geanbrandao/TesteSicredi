package com.geanbrandao.testesicredi.ui.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.geanbrandao.testesicredi.R

class PreferencesFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.theme_preferences)
    }

    private fun createPrefsProg() {
        val changeListener = object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                preference?.let { pref ->
                    if (pref.key == KEY_THEME_PREFERENCE) {
                        newValue?.let {
                            when (it) {
                                KEY_DEFAULT -> {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                                }
                                KEY_LIGHT -> {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                                }
                                KEY_DARK -> {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                                }
                            }
                            return true
                        }
                    }
                }
                return false
            }
        }

        val themePreference = ListPreference(requireContext()).apply {
            key = KEY_THEME_PREFERENCE
            title = getString(R.string.preference_text_title)
            setDefaultValue(KEY_DEFAULT)
            entries = arrayOf("Padrão", "Claro", "Escuro")
            entryValues = arrayOf(KEY_DEFAULT, KEY_LIGHT, KEY_DARK)
            onPreferenceChangeListener = changeListener
            isIconSpaceReserved = false
        }


        val screen = preferenceManager.createPreferenceScreen(requireContext())
        screen.addPreference(themePreference)

        preferenceScreen = screen
    }

    companion object {
        const val KEY_THEME_PREFERENCE = "THEME_PREFERENCE"
        const val KEY_DEFAULT = "KEY_DEFAULT"
        const val KEY_LIGHT = "KEY_LIGHT"
        const val KEY_DARK = "KEY_DARK"
    }
}