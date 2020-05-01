package com.example.examn

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel(val signUpRepository: SignUpRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()

    sealed class UiModel {
        class signup(val success: Boolean): UiModel()
        object Loading: UiModel()
    }

    fun doSignUp(name: String, lastname:String, email:String) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.signup( signUpRepository.saveUSer(name, lastname, email))
        }
        Handler().postDelayed(runnable, 3000)

    }
}