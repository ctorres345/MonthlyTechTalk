interface TextEditor {
    fun moveCursorToStart()
    fun moveCursorToEnd()
    fun moveCursor(position: Int)
    fun insert(content: String)
    fun delete(n: Int)
    fun content(): String
}