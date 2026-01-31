package services

interface ITodoService {
    fun showTodos()
    fun addTodo(todo: String)
    fun removeTodo(id: Int): Boolean // Ubah return type jadi Boolean agar View tahu sukses/gagal
    fun updateTodo(id: Int, title: String?, isFinished: Boolean?): Boolean // Return Boolean
    fun searchTodo(keyword: String)
    fun sortTodo(sortBy: String, isAscending: Boolean)

}