This is the backend application for oknoboken-fe.
The purpose of this application is mainly to serve images to the front end application, and to handle messages submitted on the front end.

Prerequisities to run this application:

A properties file called application.properties, which should include following properties:

```properties
frontend.url=<url to the frontend web application>
image.dir=<path to a directory for images>
images.json=<path to a json file where images filenames and image descriptions are specified>
message.dir=<path to a directory where messages will be stored>
subscription.dir=<path to a json file that hold subscribed users> (property name will be changed to reflect that it is a file and not directory)
```

Example content of application.properties:
```properties
frontend.url=https://lizettavonsmil.se
image.dir=/opt/oknoboken/oknoboken-be/images
images.json=/opt/oknoboken/oknoboken-be/images.json
message.dir=/opt/oknoboken/oknoboken-be/messages/
subscription.dir=/opt/oknoboken/oknoboken-be/subscriptions.json
```

Example content of images.json:
```json
[
  {
    "filename": "image1.jpg",
    "description": "A beautiful sunrise over the mountains."
  },
  {
    "filename": "image2.jpg",
    "description": "A serene beach with crystal-clear water."
  },
  {
    "filename": "image3.jpg",
    "description": "A bustling cityscape at night."
  }
]
```



