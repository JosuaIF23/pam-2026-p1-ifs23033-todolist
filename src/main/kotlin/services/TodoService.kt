package services

import entities.Todo
import repositories.ITodoRepository

class TodoService(private val todoRepository: ITodoRepository) : ITodoService {

    override fun showTodos() {
        // PERUBAHAN: Memanggil getAllTodos() bukan getAll()
        val todos = todoRepository.getAllTodos()

        println("Daftar Todo:")
        if (todos.isEmpty()) {
            println("- Data todo belum tersedia!")
        } else {
            for (todo in todos) {
                val status = if (todo.isFinished) "Selesai" else "Belum Selesai"
                println("${todo.id} | ${todo.title} | $status")
            }
        }
    }

// src/main/kotlin/services/TodoService.kt

    override fun addTodo(todo: String) {
        // HAPUS logika manual ini:
        // val todos = todoRepository.getAllTodos()
        // val nextId = if (todos.isEmpty()) 1 else todos.maxOf { it.id } + 1

        // GANTI dengan langsung membuat Todo.
        // ID akan otomatis digenerate oleh companion object di class Todo (counter++)
        val newTodo = Todo(title = todo)

        todoRepository.addTodo(newTodo)
    }

    override fun removeTodo(id: Int): Boolean {
        // PERUBAHAN: Memanggil removeTodo() bukan remove()
        return todoRepository.removeTodo(id)
    }

    override fun updateTodo(id: Int, title: String?, isFinished: Boolean?): Boolean {
        // PERUBAHAN: Memanggil updateTodo() bukan update()
        return todoRepository.updateTodo(id, title, isFinished)
    }

    override fun searchTodo(keyword: String) {
        // Menggunakan method searchTodos dari repository
        val matchTodos = todoRepository.searchTodos(keyword)

        if (matchTodos.isEmpty()) {
            println("- Data todo tidak ditemukan!")
            println()
        } else {
            // PERBAIKAN: Menghapus println("Hasil Pencarian:")
            // Langsung loop dan cetak data
            for (todo in matchTodos) {
                val status = if (todo.isFinished) "Selesai" else "Belum Selesai"
                println("${todo.id} | ${todo.title} | $status")

            }
            println()
        }
    }

    override fun sortTodo(sortBy: String, isAscending: Boolean) {
        // PERUBAHAN: Memanggil sortTodos()
        todoRepository.sortTodos(sortBy, isAscending)
    }
}