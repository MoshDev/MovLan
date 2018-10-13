package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgramCategoryTest {
    private lateinit var response: String
    private lateinit var collection: ProgramCategory

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("programCategory.json")
        collection = Gson().fromJson(response, ProgramCategory::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun category_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_category() {
        Assert.assertEquals(134966, collection.id)
        Assert.assertEquals("أخبار", collection.title)
        Assert.assertEquals(10, collection.programs.size)
    }
}
