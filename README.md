# LearnDaily — Topic Service

AI-powered question generation and answer evaluation microservice for **LearnDaily**.

The service integrates with the OpenAI API to generate practice questions based on a topic and difficulty level, and to evaluate a user's answer.

## Responsibilities

- Generate topic-specific practice questions
- Support different difficulty levels
- Evaluate user answers using an LLM
- Generate tutor-mode questions
- Communicate with the OpenAI Chat Completions API
- Keep the LLM integration isolated from the rest of the application

## Architecture

```
Client / Session Service
          |
          v
   Topic Service :8088
          |
          | WebClient
          v
      OpenAI API
          |
          v
 Question / Evaluation
```

The Session Service communicates with this service through HTTP, while this service communicates with OpenAI using Spring WebFlux's `WebClient`.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/llm/generate` | Generate a question for a topic and difficulty |
| POST | `/llm/evaluate` | Evaluate a user's answer |
| POST | `/llm/generateTutor` | Generate a tutor-mode question |
| POST | `/llm/evaluateForTutor` | Evaluate a tutor-mode answer |

### Generate a Question

```http
POST /llm/generate
Content-Type: application/json
```

The request contains a topic and difficulty. The service builds an LLM prompt and returns the generated question.

Example:

```json
{
  "topic": "Java Collections",
  "difficulty": "medium"
}
```

### Evaluate an Answer

```http
POST /llm/evaluate
Content-Type: application/json
```

The request contains the topic, question, and user's answer. The service sends these details to the LLM and returns the generated feedback.

## OpenAI Integration

The service uses `WebClient` to call the OpenAI Chat Completions API.

The model is configurable and is currently set to:

```text
gpt-4o-mini
```

The OpenAI API key is supplied through an environment variable and is never stored directly in the source code.

## Configuration

Set the following environment variables:

```text
OPENAI_API_KEY
LLM_PROMPT_QUESTION
LLM_PROMPT_FEEDBACK
```

The service runs on:

```text
http://localhost:8088
```

The OpenAI endpoint and model are configured in `application.properties`.

> Never commit your OpenAI API key to source control.

## Tech Stack

- Java 17
- Spring Boot 3.5.0
- Spring Web
- Spring WebFlux / WebClient
- OpenAI API
- Lombok
- Maven

## Running Locally

Clone the repository:

```bash
git clone https://github.com/annubelgaonkar/QnATopicService.git
cd QnATopicService
```

Set the required environment variables and run:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Project Structure

```text
src/main/java/dev/qna/qna_topic_service
├── controller    # LLM REST endpoints
├── dto           # Request/response models
├── llm           # OpenAI client integration
└── service       # LLM service abstraction and implementation
```

## LearnDaily Microservices

Topic Service is the AI/LLM component of LearnDaily.

- **Auth Service** — authentication and JWT security
- **Topic Service** — AI-powered question generation and answer evaluation
- **Session Service** — learning session management and session history

This separation keeps the external LLM integration isolated behind a dedicated microservice.
