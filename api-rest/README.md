FORMAT: 1A
HOST: http://www.google.com

# streetlife-mmecp
This specifies the REST API provided by WP4.

# Group Subscription Service
Specification of an ATOM feed for the WP4 subscribers.

## Notification Collection [/atom/{subscriberId}]

+ Parameters
    + subscriberId (required, number, `1`) ... Numeric `subscriberId` of the subscriber to perform action with.

### List all notifications for given subscriber [GET]
+ Response 200 (application/atom+xml)

        <?xml version="1.0" encoding="utf-8"?>
        <feed xmlns="http://www.w3.org/2005/Atom">
          <author>
            <name>Autor des Weblogs</name>
          </author>
          <title>Titel des Weblogs</title>
          <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
          <updated>2003-12-14T10:20:09Z</updated>
         
          <entry>
            <title>Titel des Weblog-Eintrags</title>
            <link href="http://example.org/2003/12/13/atom-beispiel"/>
            <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
            <updated>2003-12-13T18:30:02Z</updated>
            <summary>Zusammenfassung des Weblog-Eintrags</summary>
            <content>Volltext des Weblog-Eintrags</content>
          </entry>
        </feed>

### Create a notification for given subscriber [POST]
+ Request (application/atom+xml)

        <?xml version="1.0" encoding="utf-8"?>
        <feed xmlns="http://www.w3.org/2005/Atom">
          <author>
            <name>Autor des Weblogs</name>
          </author>
          <title>Titel des Weblogs</title>
          <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
          <updated>2003-12-14T10:20:09Z</updated>
         
          <entry>
            <title>Titel des Weblog-Eintrags</title>
            <link href="http://example.org/2003/12/13/atom-beispiel"/>
            <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
            <updated>2003-12-13T18:30:02Z</updated>
            <summary>Zusammenfassung des Weblog-Eintrags</summary>
            <content>Volltext des Weblog-Eintrags</content>
          </entry>
        </feed>

+ Response 201 (application/atom+xml)

        <?xml version="1.0" encoding="utf-8"?>
        <feed xmlns="http://www.w3.org/2005/Atom">
          <author>
            <name>Autor des Weblogs</name>
          </author>
          <title>Titel des Weblogs</title>
          <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
          <updated>2003-12-14T10:20:09Z</updated>
         
          <entry>
            <title>Titel des Weblog-Eintrags</title>
            <link href="http://example.org/2003/12/13/atom-beispiel"/>
            <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
            <updated>2003-12-13T18:30:02Z</updated>
            <summary>Zusammenfassung des Weblog-Eintrags</summary>
            <content>Volltext des Weblog-Eintrags</content>
          </entry>
        </feed>

## Specific notification [/atom/{subscriberId}/{notificationId}]
A single notification object with all its details

+ Parameters
    + subscriberId (required, number, `1`) ... Numeric `subscriberId` of the subscriber to perform action with.
    + notificationId (required, number, `1`)... Numeric `notificationId` of the notification to perform action with.

### Retrieve a specific notification [GET]
+ Response 200 (application/atom+xml)

        <?xml version="1.0" encoding="utf-8"?>
        <feed xmlns="http://www.w3.org/2005/Atom">
          <author>
            <name>Autor des Weblogs</name>
          </author>
          <title>Titel des Weblogs</title>
          <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
          <updated>2003-12-14T10:20:09Z</updated>
         
          <entry>
            <title>Titel des Weblog-Eintrags</title>
            <link href="http://example.org/2003/12/13/atom-beispiel"/>
            <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
            <updated>2003-12-13T18:30:02Z</updated>
            <summary>Zusammenfassung des Weblog-Eintrags</summary>
            <content>Volltext des Weblog-Eintrags</content>
          </entry>
        </feed>

### Remove a specific notification [DELETE]
+ Response 204

