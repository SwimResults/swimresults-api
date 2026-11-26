package de.swimresults.api

import de.swimresults.api.meeting.infrastructure.Serializer
import de.swimresults.api.meeting.models.Event
import java.time.OffsetDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class DecodingTest {
    // Real payload shape from GET /meeting/v1/event/meet/IESC19, plus a Go zero time.
    private val json = """
        [{"_id":"65090ef59c01934b665a9bd3","number":1,"distance":100,"meeting":"IESC19","gender":"FEMALE",
          "style":{"_id":"64f0926ebdaab70007122eb5","name":"BUTTERFLY","aliases":["Schmetterling","FLY"]},
          "final":{},"part":{"number":1},"ordering":1,
          "added_at":"2023-09-19T03:01:09.904Z","updated_at":"0001-01-01T00:00:00Z"}]
    """.trimIndent()

    @Test
    fun decodesEventsWithGoTimestamps() {
        val events: Array<Event> = Serializer.gson.fromJson(json, Array<Event>::class.java)
        assertEquals(1, events.size)
        assertEquals(1L, events[0].number)
        assertEquals("BUTTERFLY", events[0].style.name)
        assertEquals(OffsetDateTime.parse("2023-09-19T03:01:09.904Z"), events[0].addedAt)
        assertEquals(1, events[0].updatedAt.year)
    }
}
