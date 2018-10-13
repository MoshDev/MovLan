package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PollTest {
    private lateinit var response: String
    private lateinit var collection: Poll

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("poll.json")
        collection = Gson().fromJson(response, Poll::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun poll_answer_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_poll_answer() {
        Assert.assertEquals("1", collection.id)
        Assert.assertEquals("هل تتوقع تراجع الإرهاب في المنطقة؟", collection.title)
        Assert.assertEquals("49", collection.totalVotes)
        Assert.assertEquals("49", collection.totalVoters)
        Assert.assertEquals(3, collection.answers.size)
    }
}