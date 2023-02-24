package br.eng.pedro_mendes.github_followers.ui.main.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eng.pedro_mendes.github_followers.data.models.User
import br.eng.pedro_mendes.github_followers.data.repository.UsersRepository
import br.eng.pedro_mendes.github_followers.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    private val users = MutableLiveData<Resource<List<User>>>()
    private var getUsersCall: Call<List<User>>? = null

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(Resource.loading(null))

        getUsersCall = usersRepository.getFollowers().apply {
            enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    users.postValue(
                        if (response.isSuccessful) Resource.success(
                            response.body()
                        )
                        else Resource.error(
                            response.errorBody().toString(),
                            null
                        )
                    )
                    getUsersCall = null
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    users.postValue(
                        Resource.error(
                            msg = t.message ?: "Unknown error",
                            data = null,
                        )
                    )
                    getUsersCall = null
                }
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        getUsersCall?.cancel()
    }

    fun getUsers(): LiveData<Resource<List<User>>> = users
}