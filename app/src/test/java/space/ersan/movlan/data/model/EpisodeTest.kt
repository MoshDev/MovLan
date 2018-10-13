package space.ersan.movlan.data.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import space.ersan.movlan.JsonResource
import space.ersan.movlan.app.builder.GsonModule
import space.ersan.movlan.ext.asString
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EpisodeTest {
    private lateinit var response: String
    private lateinit var collection: Episode
    private lateinit var gson: Gson

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gson = GsonModule().provideGson()
        response = JsonResource.fromResource("episode.json")
        collection = gson.fromJson(response, Episode::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun episode_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_episode() {
        Assert.assertEquals("برنامج ساعة من مصر | الاقتصاد المصري سيحتل المرتبة 15 عالميا عام 2050 | حلقة 2018.02.26", collection.title)
        Assert.assertEquals("2018-02-26 09:37:42", collection.date.asString())
        Assert.assertEquals("https://i.ytimg.com/vi/qlQPQCbJAVc/mqdefault.jpg", collection.imageURL)
        Assert.assertEquals("youtube", collection.video.type)
        Assert.assertEquals("qlQPQCbJAVc", collection.video.id)
    }
}
