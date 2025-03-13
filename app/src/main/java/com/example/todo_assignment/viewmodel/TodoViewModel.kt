package com.example.todo_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.todo_assignment.model.Todo
import com.example.todo_assignment.model.TodosApi
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    // Use `mutableStateOf` instead so Jetpack Compose can track changes to this list.
    // If we use mutableListOf, the UI won't update when the list changes.


    var todos by mutableStateOf<List<Todo>>(emptyList())
        private set // `private set` prevents modification from outside the ViewModel.

    init {
        getTodosList() // Load the data when the ViewModel is created.
    }

    private fun getTodosList() {
        viewModelScope.launch { // Launch a coroutine in ViewModelScope to avoid blocking the UI.
            try {
                val todosApi = TodosApi.getInstance() // Get the API service instance.
                todos = todosApi.getTodos() // Update the list. Jetpack Compose will detect changes and refresh the UI.
            } catch (e: Exception) {
                Log.d("Error", e.message.toString()) // Log any errors if the request fails.
            }
        }
    }
}
