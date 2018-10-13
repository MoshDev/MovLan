package space.ersan.movlan.data.model

import com.google.gson.Gson
import space.ersan.movlan.JsonResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GeoLocationTest {
    private lateinit var response: String
    private lateinit var collection: GeoLocation

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("geolocation.json")
        collection = Gson().fromJson(response, GeoLocation::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun geolocation_should_not_null() {
        Assert.assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_geolocation() {
        Assert.assertEquals("أذربيجان", collection.name)
        Assert.assertEquals("AZ", collection.shortCode)
        Assert.assertEquals(40.5.toFloat(), collection.coordinate.lat)
        Assert.assertEquals(47.5.toFloat(), collection.coordinate.lng)
    }
}