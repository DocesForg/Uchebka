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

## Запуск через Docker (Windows)

Требуется установленный и запущенный Docker Desktop с поддержкой Docker Compose.

1. Откройте файл `.env.example` в редакторе и вставьте свой Yandex AI Studio API key вместо `replace-me` в строке `YANDEX_API_KEY`.
2. В корне проекта создайте рабочий файл `.env`:

   PowerShell:
   ```powershell
   Copy-Item .env.example .env
   ```

   CMD:
   ```cmd
   copy .env.example .env
   ```

3. Соберите и запустите приложение:

   ```powershell
   docker compose up --build -d
   ```

Docker Compose автоматически читает параметры из файла `.env` в корне проекта. Не редактируйте и не коммитьте `.env`: он содержит секретный API key. Файл `.env` уже добавлен в `.gitignore`; шаблон `.env.example` остаётся в репозитории.

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

Для повторной сборки после изменения кода:

```powershell
docker compose up --build -d
```

По умолчанию frontend доступен на порту 80. Если порт занят, измените `APP_PORT` в `.env`, например на `8081`, и откройте http://localhost:8081.

Docker Compose запускает frontend через Nginx и проксирует API, Swagger и Actuator в backend. Внутренний порт backend (8080) наружу не публикуется. Yandex API key передаётся только backend-контейнеру как runtime-переменная окружения; он не встраивается во frontend-образ.

## Локальный запуск без Docker

Для локального запуска backend также можно создать `.env` из `.env.example` и заполнить параметры Yandex.

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

Никогда не публикуйте API key и не добавляйте файл `.env` в Git.
