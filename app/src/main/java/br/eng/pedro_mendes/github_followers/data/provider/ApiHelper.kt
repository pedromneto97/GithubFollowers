package br.eng.pedro_mendes.github_followers.data.provider

class ApiHelper(private val apiService: ApiService) {
    fun getFollowers() = apiService.getFollowers()
}