/**
* Text editor interface with defines simple Text Editor operations.
*
* To watch the recording of the session (in Spanish) in which we explain
* a couple of implementations for this interface, see:
* https://youtu.be/qhFW4BSiRFM?t=107
*/
interface TextEditor {
    fun moveCursorToStart()
    fun moveCursorToEnd()
    fun moveCursor(position: Int)
    fun insert(content: String)
    fun delete(n: Int)
    fun content(): String
}