package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import space.ersan.movlan.app.builder.GsonModule
import space.ersan.movlan.ext.asString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PostTest {
    private lateinit var response: String
    private lateinit var postNoVideo: Post
    private lateinit var postWithVideo: Post
    private lateinit var gson: Gson

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gson =  GsonModule().provideGson()

        // No Video Object
        response = JsonResource.fromResource("post.json")
        postNoVideo = gson.fromJson(response, Post::class.java)

        // With Video Object
        response = JsonResource.fromResource("postWithVideo.json")
        postWithVideo = gson.fromJson(response, Post::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun postNoVideo_should_not_null() {
        assertNotNull(postNoVideo)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_post() {
        assertEquals(101829, postNoVideo.id)
        assertEquals("This is some cool title", postNoVideo.title)
        assertEquals("Some long arabic content", postNoVideo.content)
        assertEquals("2018-01-24 16:21:20", postNoVideo.date.asString())
        assertEquals("http://alghad.tv/wp-content/uploads/2018/01/2018-01-24T052255Z_1_LYNXMPEE0N08C_RTROPTP_4_CRISIS-SYRIA-TURKEY-AR5.jpg", postNoVideo.imageURL)
        assertEquals("image", postNoVideo.type)
        assertEquals(null, postNoVideo.video)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_post_with_video() {
        assertEquals(101829, postWithVideo.id)
        assertEquals("video", postWithVideo.type)
        assertEquals("Z9dW2y4lEKQ", postWithVideo.video?.id)
        assertEquals("youtube", postWithVideo.video?.type)
    }
}