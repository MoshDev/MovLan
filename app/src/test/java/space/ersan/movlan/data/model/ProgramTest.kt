package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgramTest {
    private lateinit var response: String
    private lateinit var collection: Program

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("program.json")
        collection = Gson().fromJson(response, Program::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun program_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_program() {
        Assert.assertEquals(244608, collection.id)
        Assert.assertEquals("ساعة من مصر", collection.title)
        Assert.assertEquals("http://alghad.startappzdev.com/wp-content/uploads/2018/02/sa3a-mn-masr.png", collection.imageURL)
    }
}