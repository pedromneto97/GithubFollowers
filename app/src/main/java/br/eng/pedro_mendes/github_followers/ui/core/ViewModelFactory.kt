package br.eng.pedro_mendes.github_followers.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.eng.pedro_mendes.github_followers.data.provider.ApiHelper
import br.eng.pedro_mendes.github_followers.data.repository.UsersRepository
import br.eng.pedro_mendes.github_followers.ui.main.view_model.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(UsersRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}