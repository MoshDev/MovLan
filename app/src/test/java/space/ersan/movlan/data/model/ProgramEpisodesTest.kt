package space.ersan.movlan.data.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import space.ersan.movlan.JsonResource
import space.ersan.movlan.app.builder.GsonModule
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgramEpisodesTest {
    private lateinit var response: String
    private lateinit var collection: ProgramEpisodes
    private lateinit var gson: Gson

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gson =  GsonModule().provideGson()

        response = JsonResource.fromResource("programEpisodes.json")
        collection = gson.fromJson(response, ProgramEpisodes::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun program_episodes_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_program_episodes() {
        Assert.assertEquals("CAoQAA", collection.nextPage)
        Assert.assertEquals(10, collection.episodes.size)
    }
}