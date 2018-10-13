package space.ersan.movlan.data.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import space.ersan.movlan.JsonResource
import space.ersan.movlan.app.builder.GsonModule
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LiveStreamProgramTest {
    private lateinit var response: String
    private lateinit var collection: LiveStreamProgram
    private lateinit var gson: Gson

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gson =  GsonModule().provideGson()

        response = JsonResource.fromResource("liveStreamProgram.json")
        collection = gson.fromJson(response, LiveStreamProgram::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun live_stream_program_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_live_stream_program_episodes() {
        Assert.assertEquals(244668, collection.id)
        Assert.assertEquals("النشرات الأخبارية", collection.title)
        Assert.assertEquals(10, collection.episodes.size)
    }
}