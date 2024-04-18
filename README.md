# Spring AI RAG Example
---
Simple example to load the entire text of a document into a vector store and 
then expose an API through which questions can be asked about the document's 
content.

```
docker compose up -d
docker exec -it ollama ollama run mistral
```

You'll also need a document for it to load. Set the `app.resource` property 
in src/main/resources/application.properties to the resource URL of the
document. For example:

```
app.resource=file:///Users/someuser/Spring_in_Action_SixthIEdition.pdf
```

The resource URL can be a file, classpath, or even an HTTP URL. The file
itself can be any document type supported by Apache Tika, including PDF,
Word, HTML, and more.

Then run the application as you would any Spring Boot application. For
example, using Maven:

```
./mvnw spring-boot:run
```

The first time you run it, it will take a little while to load the document into
the vector store (which will be persisted at /tmp/vectorstore.json). Subsequent
runs will just use the persisted vector store and not try to load the document again.
(This means that if you change the document resource, you'll need to delete
/tmp/vectorstore.json so that it will be reloaded.)