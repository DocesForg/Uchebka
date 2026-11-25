# Uchebka

Образовательная платформа для создания и прохождения тестов по учебным материалам с помощью Yandex AI Studio Agent.

## Стек
- Frontend: React, TypeScript, Vite
- Backend: Java 21, Spring Boot 3, Maven
- AI: Yandex AI Studio Responses API
- Resilience: Resilience4j
- API docs: Springdoc OpenAPI
- Observability: Actuator + Micrometer

## Быстрый запуск

Скопируйте `.env.example` в `.env`, заполните `YANDEX_API_KEY`, затем запустите backend и frontend.

### Backend (PowerShell)
```powershell
cd backend
$env:YANDEX_API_KEY="ваш-api-ключ"
$env:YANDEX_PROJECT_ID="идентификатор-каталога"
$env:YANDEX_PROMPT_ID="fvt526o3mhjmd9f6l2te"
mvn spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui.html

### Frontend
```powershell
cd frontend
npm install
npm run dev
```
Приложение: http://localhost:5173

Все секреты задаются через переменные окружения. Не коммитьте API-ключ.
