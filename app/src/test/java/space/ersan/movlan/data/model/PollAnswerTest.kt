package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PollAnswerTest {
    private lateinit var response: String
    private lateinit var collection: PollAnswer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("pollAnswer.json")
        collection = Gson().fromJson(response, PollAnswer::class.java)
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
        Assert.assertEquals("نعم", collection.name)
        Assert.assertEquals("16", collection.totalVotes)
    }
}