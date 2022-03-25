package com.android.weathersimple.dispatchers

import com.android.weathersimple.FileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return if (request.requestUrl?.encodedPath?.startsWith("/group") == true) {
            MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_response.json"))
        } else {
            MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_response.json"))
        }
    }
}
