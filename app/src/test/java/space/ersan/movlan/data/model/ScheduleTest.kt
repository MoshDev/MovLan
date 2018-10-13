package space.ersan.movlan.data.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import space.ersan.movlan.JsonResource
import space.ersan.movlan.app.builder.GsonModule
import space.ersan.movlan.ext.asString
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ScheduleTest {
    private lateinit var response: String
    private lateinit var collection: Schedule
    private lateinit var gson: Gson


    @Before
    @Throws(Exception::class)
    fun setUp() {
        gson =  GsonModule().provideGson()

        response = JsonResource.fromResource("schedule.json")
        collection = gson.fromJson(response, Schedule::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun schedule_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_schedule() {
        Assert.assertEquals("فرصه", collection.title)
        Assert.assertEquals("2018-02-28 15:30:00", collection.start.asString())
        Assert.assertEquals(0, collection.order)
    }
}
