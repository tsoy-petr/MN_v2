package com.android.hootr.hogwartslibrary.ui.scenes.hat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.helpers.Keys
import com.android.hootr.hogwartslibrary.ui.scenes.main.MainActivity
import kotlinx.android.synthetic.main.activity_hat.*

class HatActivity : AppCompatActivity() {

    private lateinit var hatViewModel: HatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hat)

        hatViewModel = ViewModelProvider(this)[HatViewModel::class.java]

        textWelcomeUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hatViewModel.applayUserName(s.toString())
            }

        })

        btnWelomeSelected.setOnClickListener {
            if (btnWelomeSelected.text == getString(R.string.welcome_next)) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                hatViewModel.getFacultyName()
            }
        }

        setupFaculty(hatViewModel)
        setupIsLoading(hatViewModel)
    }

    fun setupIsLoading(viewModel: HatViewModel) {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            textWelcomeUsername.isEnabled = !isLoading
            btnWelomeSelected.isEnabled = !isLoading
            if (isLoading) {
                btnWelomeSelected.text = getString(R.string.welcome_selcted)
            }
        })
    }

    fun setupFaculty(viewModel: HatViewModel) {
        viewModel.facultyName.observe(this, Observer { facultyName ->
            if (facultyName.isNotEmpty()) {

                val sharedPreferences = getSharedPreferences(getString(R.string.app_name), 0)
                sharedPreferences.edit()
                    .putString(Keys.Username.value, textWelcomeUsername.text.toString())
                    .putString(Keys.Faculty.value, facultyName).apply()

                textWelcomeSelected.visibility = View.VISIBLE
                textWelcomeUsername.isEnabled = false
                textWelcomeSelected.text =
                    getString(R.string.welcome_selcted).replace("[faculty_name]", facultyName)
                btnWelomeSelected.text = getString(R.string.welcome_next)
            }
        })
    }
}


