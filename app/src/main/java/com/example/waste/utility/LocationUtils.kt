package com.example.waste.utility

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.LocationBias
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.Place.Field
import com.google.android.libraries.places.api.model.RectangularBounds
import java.util.*

class LocationUtils(private val context: Context) {
    private lateinit var placesClient: PlacesClient

    init {
        // Initialize the Places API client with your API key
        Places.initialize(context, "YOUR_API_KEY")
        placesClient = Places.createClient(context)
    }

    /*fun getAddressFromLatLng(latLng: LatLng, onAddressFetched: (String) -> Unit) {
        val placeId = "YOUR_PLACE_ID" // Replace with your actual place ID

        val placeFields = listOf(Field.ADDRESS)

        val request = FetchPlaceRequest.builder(placeId)
            .setLocationBias(LocationBias.DEFAULT)
            .setPlaceFields(placeFields)
            .build()


        placesClient.fetchPlace(request).addOnSuccessListener { response ->
            val place = response.place
            val address = place.address
            onAddressFetched(address ?: "Address not found")
        }.addOnFailureListener {
            // Handle any errors that occurred while fetching the address
            onAddressFetched("Error fetching address")
        }
    }*/
}
