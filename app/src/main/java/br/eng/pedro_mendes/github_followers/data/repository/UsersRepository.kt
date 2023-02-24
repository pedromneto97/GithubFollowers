package br.eng.pedro_mendes.github_followers.data.repository

import br.eng.pedro_mendes.github_followers.data.provider.ApiHelper

class UsersRepository(private val apiHelper: ApiHelper) {
    fun getFollowers() = apiHelper.getFollowers()
}