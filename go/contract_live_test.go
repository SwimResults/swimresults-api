package contract_test

// Live contract smoke test: calls public, read-only endpoints of a running SwimResults environment and
// decodes the responses STRICTLY into the generated types (unknown fields and wrong types fail).
// It catches fields the services send that are missing from the specs, and type mismatches.
//
//	SR_CONTRACT_LIVE=https://api-dev.swimresults.de go test ./go/ -run Live -v
//
// Skipped unless SR_CONTRACT_LIVE is set (CI does not depend on a live environment).

import (
	"bytes"
	"context"
	"encoding/json"
	"io"
	"net/http"
	"os"
	"strconv"
	"strings"
	"testing"
	"time"

	"github.com/swimresults/swimresults-api/go/athleteapi"
	"github.com/swimresults/swimresults-api/go/meetingapi"
	"github.com/swimresults/swimresults-api/go/startapi"
)

func strictDecode(t *testing.T, name string, body []byte, into any) {
	t.Helper()
	dec := json.NewDecoder(bytes.NewReader(body))
	dec.DisallowUnknownFields()
	if err := dec.Decode(into); err != nil {
		t.Errorf("%s: response does not match spec: %v", name, err)
	}
}

func get(t *testing.T, url string) []byte {
	t.Helper()
	ctx, cancel := context.WithTimeout(context.Background(), 20*time.Second)
	defer cancel()
	req, _ := http.NewRequestWithContext(ctx, http.MethodGet, url, nil)
	res, err := http.DefaultClient.Do(req)
	if err != nil {
		t.Fatalf("GET %s: %v", url, err)
	}
	defer res.Body.Close()
	b, _ := io.ReadAll(res.Body)
	if res.StatusCode != http.StatusOK {
		t.Fatalf("GET %s: status %d: %.200s", url, res.StatusCode, b)
	}
	return b
}

func TestLiveContract(t *testing.T) {
	base := strings.TrimRight(os.Getenv("SR_CONTRACT_LIVE"), "/")
	if base == "" {
		t.Skip("set SR_CONTRACT_LIVE=https://api.swimresults.de (or api-dev) to run")
	}
	ctx := context.Background()

	// generated client end-to-end (request building + response parsing)
	mc, err := meetingapi.NewClientWithResponses(base + "/meeting/v1")
	if err != nil {
		t.Fatal(err)
	}
	mr, err := mc.GetMeetingsWithResponse(ctx, &meetingapi.GetMeetingsParams{})
	if err != nil || mr.JSON200 == nil || len(*mr.JSON200) == 0 {
		t.Fatalf("GetMeetings via generated client: err=%v status=%d", err, mr.StatusCode())
	}
	var meetings []meetingapi.Meeting
	strictDecode(t, "GET meeting/v1/meeting", mr.Body, &meetings)

	// pick a meeting that has events
	var meetID string
	var events []meetingapi.Event
	for i := len(meetings) - 1; i >= 0 && meetID == ""; i-- {
		if meetings[i].MeetId == nil {
			continue
		}
		b := get(t, base+"/meeting/v1/event/meet/"+*meetings[i].MeetId)
		var evs []meetingapi.Event
		strictDecode(t, "GET meeting/v1/event/meet/{id}", b, &evs)
		if len(evs) > 0 && evs[0].Number != nil {
			meetID, events = *meetings[i].MeetId, evs
		}
	}
	if meetID == "" {
		t.Skip("no meeting with events found")
	}
	t.Logf("using meeting %s (%d events)", meetID, len(events))

	var amount int64
	strictDecode(t, "GET athlete/v1/athlete/amount", get(t, base+"/athlete/v1/athlete/amount"), &amount)

	var athletes []athleteapi.Athlete
	strictDecode(t, "GET athlete/v1/athlete/meet/{id}", get(t, base+"/athlete/v1/athlete/meet/"+meetID+"?limit=20"), &athletes)

	var teams []athleteapi.Team
	strictDecode(t, "GET athlete/v1/team/meet/{id}", get(t, base+"/athlete/v1/team/meet/"+meetID+"?limit=20"), &teams)

	var starts []startapi.Start
	strictDecode(t, "GET start/v1/start/meet/{id}/event/{n}",
		get(t, base+"/start/v1/start/meet/"+meetID+"/event/"+strconv.FormatInt(*events[0].Number, 10)), &starts)

	var heats []startapi.Heat
	strictDecode(t, "GET start/v1/heat/meet/{id}", get(t, base+"/start/v1/heat/meet/"+meetID), &heats)
}
