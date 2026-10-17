

Readme · MD
ShopHook
A beginner-to-pro Spring Boot project for learning webhooks: how to receive them safely and how to send them reliably.

What is a webhook?
A webhook is an HTTP POST that another system sends to your URL when something happens (for example, "payment succeeded"). Instead of you asking again and again, they call you.

What this project does
Receiver: accepts webhooks from a payment provider, verifies them, stores them, and processes them in the background.
Sender: notifies customer systems about events, with signing and retries.
Progress
 Step 0: Project setup (Spring Web, Spring Data JPA, H2, Validation)
 Step 1: Basic webhook endpoint POST /webhooks/payments
 Step 2: WebhookEvent entity and repository (store every event)
 Step 3: HMAC-SHA256 signature verification
 Step 4: Idempotency (ignore duplicate events)
 Step 5: Respond fast, process asynchronously
 Step 6: Sender with retries, backoff and dead-letter status
 Step 7: Tests (MockMvc, WireMock)
 Step 8: Pro upgrades (replay protection, SSRF protection, queues, metrics)
Tech stack
Java 17
Spring Boot 3.x
Maven
H2 in-memory database
Project structure
src/main/java/com/shophook/
├── ShophookApplication.java      (main class, must be in the top package)
├── controllers/
│   └── WebhookController.java
├── WebhookEvent.java
└── WebhookEventRepository.java
Important: the main class must sit in a package above all other classes, otherwise Spring will not find them (this causes 404 errors).

Configuration
src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:h2:mem:shopdb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
webhook.secret=my-super-secret-123
Note: this secret is for learning only. For real providers, load it from an environment variable and never commit it.

Run
bash
./mvnw spring-boot:run
On Windows PowerShell:

powershell
.\mvnw.cmd spring-boot:run
Or press Run in IntelliJ. The app starts on http://localhost:8080.

Try it
Send a test webhook (Windows PowerShell):

powershell
curl.exe -i -X POST "http://localhost:8080/webhooks/payments" -H "Content-Type: application/json" --data-raw "{\"id\":\"evt_1001\",\"type\":\"payment.success\",\"orderId\":\"ORD-1\",\"amount\":499}"
Expected: HTTP/1.1 200 and a "Got Webhook" line in the app console.

View the database
Open http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:shopdb
User: sa, password: empty
Click Connect, then run SELECT * FROM WEBHOOK_EVENT;
The database is in memory, so data is cleared each time the app stops.

Golden rules of webhooks
Verify the signature on the raw body.
Reply 2xx quickly and do heavy work in the background.
Be idempotent: the same event can arrive more than once.
Do not trust event order.
Store the raw payload so you can replay it.
License
For learning purposes.

Claude finished the response
