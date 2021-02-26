/**
* Text editor implementation that uses a Gap Buffer behind the scenes.
* This is a very simple implementation - lacks a lot of optimizations.
*
* For details about how to implement a Gap Buffer for Text Editors, see:
* http://www.finseth.com/craft/#c6.4.3
*
*
* To watch the recording of the session (in Spanish) in which we explain
* this data structure, see:
* https://youtu.be/qhFW4BSiRFM?t=1142
*/
class GapBufferTextEditor : TextEditor {

    companion object {
        const val SIZE = 10_000_000
    }

    val data = CharArray(SIZE)
    var cursor = 0
    var gapStart = 0
    var gapEnd = SIZE
    var content_size = 0

    override fun moveCursorToStart() {
        moveCursor(0)
    }

    override fun moveCursorToEnd() {
        moveCursor(content_size)
    }

    override fun moveCursor(position: Int) {
        // position uses a user-coordinate position system.
        // we need to transform it to a gap-coordinate position
        cursor = if(position <= gapStart) {
            position
        } else {
            gapEnd - gapStart + position
        }
    }

    override fun insert(content: String) {
        moveGapToCursor()
        content.forEachIndexed { i, value ->
            data[cursor + i] = value
        }
        cursor += content.length
        gapStart+= content.length
        content_size+= content.length
    }

    override fun delete(n: Int) {
        moveGapToCursor()
        val removeFrom = (cursor - n).coerceAtLeast(0)
        for(i in removeFrom..removeFrom+n) {
            data[i] = '\u0000'
        }
        gapStart = removeFrom
        cursor = removeFrom
        content_size -= n
    }

    override fun content(): String {
        return data.filter { it != '\u0000' }.joinToString(separator = "")
    }

    private fun moveGapToCursor() {
        when(cursor) {
            gapStart -> Unit
            gapEnd -> cursor = gapEnd
            in 0..gapStart -> { // move gap towards left
                moveData(IntRange(cursor, gapStart), offset = gapEnd - gapStart)
                gapEnd -=  (gapStart - cursor)
                gapStart = cursor
            }
            else -> { // move gap towards right
                moveData(IntRange(gapEnd, cursor), offset = gapStart - gapEnd)
                gapStart += cursor - gapEnd
                gapEnd = cursor
                cursor = gapStart
            }
        }
    }

    private fun moveData(range: IntRange, offset: Int) {
        for(i in range.first until range.last) {
            val value = data[i]
            data[i] = '\u0000'
            data[i + offset] = value
        }
    }
}