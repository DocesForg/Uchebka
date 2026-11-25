$ErrorActionPreference = 'Stop'

Write-Host ''
Write-Host 'Uchebka - запуск через Docker' -ForegroundColor Cyan
Write-Host 'Введите Yandex AI Studio API key.'
$secureKey = Read-Host 'YANDEX_API_KEY' -AsSecureString
$ptr = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($secureKey)
try {
    $apiKey = [Runtime.InteropServices.Marshal]::PtrToStringBSTR($ptr)
}
finally {
    [Runtime.InteropServices.Marshal]::ZeroFreeBSTR($ptr)
}

if ([string]::IsNullOrWhiteSpace($apiKey)) {
    Write-Error 'API key не может быть пустым.'
    exit 1
}

$env:YANDEX_API_KEY = $apiKey
$env:YANDEX_PROJECT_ID = 'b1gfj3v8keh3qobuh6rv'
$env:YANDEX_PROMPT_ID = 'fvt526o3mhjmd9f6l2te'
$env:YANDEX_BASE_URL = 'https://ai.api.cloud.yandex/v1'
$env:FRONTEND_ORIGIN = 'http://localhost'

try {
    docker compose up --build -d
    if ($LASTEXITCODE -ne 0) {
        throw 'Docker Compose завершился с ошибкой.'
    }
    Write-Host ''
    Write-Host 'Uchebka запущена: http://localhost' -ForegroundColor Green
    Write-Host 'Swagger: http://localhost/swagger-ui.html'
}
finally {
    Remove-Item Env:YANDEX_API_KEY -ErrorAction SilentlyContinue
}