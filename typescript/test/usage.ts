// Compile-time checks that the generated types fit together the way consumers use them.
import type { Meeting, Event, paths as MeetingPaths } from '../src/meeting';
import type { Start, Heat, paths as StartPaths } from '../src/start';

// list responses are nullable: Go sends null for an empty list
type Json<R> = R extends { content: { 'application/json': infer T } } ? NonNullable<T> : never;
type Assert<T extends true> = T;

type MeetingList = Json<MeetingPaths['/meeting']['get']['responses'][200]>;
type EventList = Json<MeetingPaths['/event/meet/{meet_id}']['get']['responses'][200]>;
type CurrentStarts = Json<StartPaths['/start/meet/{meet_id}/current']['get']['responses'][200]>;

export type _1 = Assert<MeetingList extends Meeting[] ? true : false>;
export type _2 = Assert<EventList extends Event[] ? true : false>;
export type _3 = Assert<CurrentStarts extends Start[] ? true : false>;
export type _4 = Assert<Heat['_id'] extends string ? true : false>;
