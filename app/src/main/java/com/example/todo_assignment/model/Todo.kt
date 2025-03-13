package com.example.todo_assignment.model
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface TodosApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo> // Function to fetch the list of items from the API

    companion object {
        var todosService: TodosApi? = null

        fun getInstance(): TodosApi {
            if (todosService === null) { // Initialize the API service only once
                todosService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TodosApi::class.java) // Create the API service instance
            }
            return todosService!! // Return the existing or newly created service
        }
    }

}