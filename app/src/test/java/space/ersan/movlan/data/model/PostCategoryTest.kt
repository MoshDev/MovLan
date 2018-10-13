package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostCategoryTest {
    private lateinit var response: String
    private lateinit var collection: PostCategory

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("category.json")
        collection = Gson().fromJson(response, PostCategory::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun category_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_category() {
        Assert.assertEquals(227, collection.id)
        Assert.assertEquals("العالم", collection.title)
    }
}