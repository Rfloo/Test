package com.muhamadrafigoweldy.suitmedia.repository

import com.muhamadrafigoweldy.suitmedia.data.ApiService
import com.muhamadrafigoweldy.suitmedia.data.UserResponse
import retrofit2.Call

class UserRepository(private val apiService: ApiService) {

    fun getUsers(page: Int, perPage: Int): Call<UserResponse> {
        return apiService.getUsers(page, perPage)
    }
}