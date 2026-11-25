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

## Запуск через Docker (Windows / PowerShell)

Требуется установленный и запущенный Docker Desktop с поддержкой Docker Compose.

В корне проекта выполните:

```powershell
.\start.ps1
```

Скрипт запросит `YANDEX_API_KEY` (ввод скрыт), после чего автоматически передаст ключ контейнеру и выполнит сборку и запуск:

```powershell
docker compose up --build -d
```

Создавать или копировать `.env` вручную не нужно. API-ключ не сохраняется в файлах проекта и не попадает в Git. После запуска скрипт очищает переменную окружения текущего процесса.

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

Для повторной сборки запустите `.\start.ps1` ещё раз и введите ключ.

По умолчанию frontend доступен на порту 80. Если порт занят, можно задать `APP_PORT` в окружении перед запуском скрипта, например `$env:APP_PORT=8081`.

Docker Compose запускает frontend через Nginx и проксирует API, Swagger и Actuator в backend. Внутренний порт backend (8080) наружу не публикуется. Yandex API key передаётся backend-контейнеру только как runtime-переменная окружения; он не встраивается во frontend-образ.

## Локальный запуск без Docker

Для локального запуска backend можно использовать `.env.example`: создайте `.env` вручную и заполните параметры Yandex.

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