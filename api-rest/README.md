AML 0.8
title: STREETLIFE WP4 Control Panel API
version: v1.0
baseUri: http://193.175.133.251/api/rest

/event:
  description: Services for handling events
  get:
    description: Subscription service for customers
    responses:
      200:
        description: If everything is fine
        body:
          application/xml:
            schema: http://www.kbcafe.com/rss/atom.xsd.xml
  /{eventID}:
    get:
      responses:
        200:
          body:
            application/json:
              schema: very cool streetlife schema
  post:
    description: Push new events to the control panel
    body:
      application/json:
        schema: |
          {
            "$schema":
            {
              "spatial": {
                "description": "Spatial position of an event, derived from geoJSON (http://geojson.org/geojson-spec.html)",
                "type": "object",
                "properties": {
                  "type": {
                    "type": "string",
                    "description": "A single position for now, more types of spatials are possible (e.g. polygon)",
                    "enum": ["point"]
                  }
                }
              }
            },
            "coordinates": {
              "type": "array",
              "description": "Coordinates with x,y position",
              "minItems": 2,
              "maxItems": 2,
              "items": {
                "type": "number",
                "description": "x or y position"
              }
            }
          }
    responses:
      200:
        body:
          application/json:
            schema: http://panel.streetlife-project.eu/streetlife.panel.post.response.json
