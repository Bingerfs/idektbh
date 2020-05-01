package com.example.examn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var signUpActivity: SignUpActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val signUpViewModel = SignUpViewModel(SignUpRepository())
        signUpViewModel.model.observe(this, Observer(::updateUi))
        signup_btn.setOnClickListener{
            signUpViewModel.doSignUp(name_edit_text.text.toString(), lastname_edit_text.text.toString(), email_edit_text.text.toString())
        }
    }

    private fun updateUi(model: SignUpViewModel.UiModel?) {
        loading_progress_bar.visibility = if ( model is SignUpViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is SignUpViewModel.UiModel.signup -> validarSignUp(model.success)
        }
    }

    private fun validarSignUp( resp: Boolean) {
        if( resp) {
            Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }

    }
}
