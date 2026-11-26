# @swimresults/api

TypeScript types for the SwimResults APIs, generated from the OpenAPI specs in
[SwimResults/swimresults-api](https://github.com/SwimResults/swimresults-api). Types only, no runtime code.

```ts
import type { Meeting, Event } from '@swimresults/api/meeting';
import type { Start, Heat, paths as StartPaths } from '@swimresults/api/start';

// response type of an operation
type CurrentStarts =
  StartPaths['/start/meet/{meet_id}/current']['get']['responses'][200]['content']['application/json'];
```

Modules: `meeting`, `athlete`, `start`, `user`, `import`. Each exports `paths`, `components`, `operations`
and one alias per schema.

Base URLs: `https://api.swimresults.de/<module>/v1` (production) and `https://api-dev.swimresults.de/<module>/v1`
(development).
