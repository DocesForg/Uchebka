# Uchebka

Образовательная платформа для создания и прохождения тестов по учебным материалам с помощью Yandex AI Studio Agent.

## Стек

- Frontend: React, TypeScript, Vite, Nginx
- Backend: Java 21, Spring Boot 3, Maven
- AI: Yandex AI Studio Responses API
- Resilience: Resilience4j
- API docs: Springdoc OpenAPI
- Observability: Actuator + Micrometer
- Deployment: Docker, Docker Compose

## Запуск через Docker (рекомендуется)

Требуется установленный Docker Desktop с включённым Docker Compose.

1. Скопируйте `.env.docker.example` в `.env`.
2. Откройте `.env` и укажите действительный `YANDEX_API_KEY`. При необходимости проверьте `YANDEX_PROJECT_ID` и `YANDEX_PROMPT_ID`.
3. В корне проекта выполните:

```powershell
docker compose up --build -d
```

После успешного запуска:

- Приложение: http://localhost
- Swagger UI: http://localhost/swagger-ui.html
- OpenAPI JSON: http://localhost/v3/api-docs
- Backend Actuator: http://localhost/actuator/health

Остановка:

```powershell
docker compose down
```

Просмотр логов:

```powershell
docker compose logs -f
```

Пересборка после изменения исходного кода:

```powershell
docker compose up --build -d
```

По умолчанию frontend доступен на порту 80. Если порт занят, задайте в `.env`, например, `APP_PORT=8081`; приложение будет доступно по адресу http://localhost:8081.

Docker Compose запускает frontend через Nginx и проксирует API, Swagger и Actuator в backend. Внутренний порт backend (8080) наружу не публикуется. Ключ Yandex передаётся контейнеру через переменную окружения и не встраивается в frontend-образ.

## Локальный запуск без Docker

Скопируйте `.env.example` в `.env` и заполните параметры Yandex.

### Backend (PowerShell)

```powershell
cd backend
mvn spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html

### Frontend

В другом терминале:

```powershell
cd frontend
npm install
npm run dev
```

Приложение: http://localhost:5173

Не коммитьте файл `.env` и не публикуйте API-ключ.