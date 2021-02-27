import org.junit.Assert
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import kotlin.system.measureTimeMillis

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class TextEditorTest {

    companion object {
        const val HELLO_WORLD = "hello world"
    }

    lateinit var textEditor: TextEditor

    @Before
    fun setup() {
        textEditor = GapBufferTextEditor()
    }

    @Test
    fun test_0_assert_building() {
        textEditor.insert(HELLO_WORLD)
        textEditor.content() shouldEqual "hello world"

        textEditor.moveCursor(6)
        textEditor.insert("beautiful ")
        textEditor.content() shouldEqual "hello beautiful world"

        textEditor.delete(6)
        textEditor.content() shouldEqual "hello beauworld"

        textEditor.moveCursor(15)
        textEditor.insert(" of kotlin")
        textEditor.content() shouldEqual "hello beauworld of kotlin"
    }


    /**
     * -------------------------
     * INSERTION + REMOVAL TESTS
     * -------------------------
     */

    @Test
    fun test_1_insertion_removal_small() = measure {
        repeat(100) {
            textEditor.insert(HELLO_WORLD)
            textEditor.delete(5)
        }
    }

    @Test
    fun test_2_insertion_removal_medium() = measure {
        repeat(10_000) {
            textEditor.insert(HELLO_WORLD)
        }
        textEditor.delete(50_000)
    }

    @Test
    fun test_3_insertion_removal_huge() = measure {
        repeat(100_000) {
            textEditor.insert(HELLO_WORLD)
        }
        textEditor.delete(5_000_000)
    }


    /**
     * -----------------
     * MOVE CURSOR TESTS
     * -----------------
     */


    @Test
    fun test_4_cursor_movement_small() {
        textEditor.insert(HELLO_WORLD)
        measure {
            textEditor.moveCursorToStart()
            textEditor.moveCursorToEnd()
            textEditor.moveCursorToStart()
        }
    }

    @Test
    fun test_5_cursor_movement_medium() {
        repeat(10_000) {
            textEditor.insert(HELLO_WORLD)
        }
        measure {
            textEditor.moveCursorToStart()
            textEditor.moveCursorToEnd()
            textEditor.moveCursorToStart()
        }
    }

    @Test
    fun test_6_cursor_movement_huge() {
        repeat(100_000) {
            textEditor.insert(HELLO_WORLD)
        }
        measure {
            textEditor.moveCursorToStart()
            textEditor.moveCursorToEnd()
            textEditor.moveCursorToStart()
        }
    }


    /**
     * ---------------------------------
     * INSERT, MOVE CURSOR, INSERT TESTS
     * ---------------------------------
     */


    @Test
    fun test_7_move_cursor_and_insert_small() {
        textEditor.insert(HELLO_WORLD)
        measure {
            textEditor.moveCursor(position = 0)
            textEditor.insert(HELLO_WORLD)
            textEditor.moveCursor(position = HELLO_WORLD.length)
            textEditor.insert(HELLO_WORLD)
        }
    }

    @Test
    fun test_8_move_cursor_and_insert_medium() {
        repeat(10_000) {
            textEditor.insert(HELLO_WORLD)
        }
        measure {
            textEditor.moveCursorToStart()
            textEditor.insert(HELLO_WORLD)
            textEditor.moveCursorToEnd()
            textEditor.insert(HELLO_WORLD)
            textEditor.moveCursor(HELLO_WORLD.length * 5_000) // half
            textEditor.insert(HELLO_WORLD)
        }
    }

    @Test
    fun test_9_move_cursor_and_insert_huge() {
        repeat(100_000) {
            textEditor.insert(HELLO_WORLD)
        }
        measure {
            textEditor.moveCursor(0) // start
            textEditor.insert(HELLO_WORLD)
            textEditor.moveCursor(HELLO_WORLD.length * 100_000) // end
            textEditor.insert(HELLO_WORLD)
            textEditor.moveCursor(HELLO_WORLD.length * 50_000) // half
            textEditor.insert(HELLO_WORLD)
        }
    }

    private fun measure(block: ()->Unit) {
        val time = measureTimeMillis(block)
        println("Execution took: ${time}ms")
    }

    private infix fun Any?.shouldEqual(expected: Any?) {
        Assert.assertEquals(expected, this)
    }

}